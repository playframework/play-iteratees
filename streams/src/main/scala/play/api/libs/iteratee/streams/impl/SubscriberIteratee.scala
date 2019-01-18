/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.iteratee.streams.impl

import org.reactivestreams.{ Subscriber, Subscription }
import play.api.libs.iteratee.concurrent.StateMachine
import play.api.libs.iteratee._

import scala.concurrent.{ ExecutionContext, Future, Promise }

private[streams] object SubscriberIteratee {

  sealed trait State

  /**
   * The iteratees fold method has not been called, and so it hasn't
   * subscribed to the subscriber yet.
   */
  case object NotSubscribed extends State

  /**
   * There is currently no demand, and the iteratee isn't interested in any
   * demand. The stream is effectively idle.
   */
  case object NoDemand extends State

  /**
   * The iteratee fold method has been invoked, so the iteratee is able to
   * consume elements, but there is no demand.
   *
   * @param demand A callback to be executed when there is demand
   * @param cancelled A callback to be executed if the subscription is cancelled
   */
  case class AwaitingDemand(demand: () => Unit, cancelled: () => Unit) extends State

  /**
   * There is demand, but the iteratee is no elements to supply.
   *
   * @param n The number of elements demanded.
   */
  case class Demand(n: Long) extends State

  /**
   * The subscription has been cancelled, the iteratee is done.
   */
  case object Cancelled extends State

}

import streams.impl.SubscriberIteratee._

private[streams] class SubscriberIteratee[T](subscriber: Subscriber[T]) extends StateMachine[State](NotSubscribed)
    with Subscription with Iteratee[T, Unit] { self =>

  def fold[B](folder: (Step[T, Unit]) => Future[B])(implicit ec: ExecutionContext): Future[B] = {
    val promise = Promise[B]()
    exclusive {
      case NotSubscribed =>
        state = awaitDemand(promise, folder, ec)
        subscriber.onSubscribe(this)
      case NoDemand =>
        state = awaitDemand(promise, folder, ec)
      case AwaitingDemand(_, _) =>
        throw new IllegalStateException("fold invoked while already waiting for demand")
      case Demand(n) =>
        if (n == 1) {
          state = NoDemand
        } else {
          state = Demand(n - 1)
        }
        demand(promise, folder, ec)
      case Cancelled =>
        cancelled(promise, folder, ec)
    }

    promise.future
  }

  private def awaitDemand[B](promise: Promise[B], folder: (Step[T, Unit]) => Future[B], ec: ExecutionContext): AwaitingDemand = {
    AwaitingDemand(() => demand(promise, folder, ec), () => cancelled(promise, folder, ec))
  }

  private def demand[B](promise: Promise[B], folder: (Step[T, Unit]) => Future[B], ec: ExecutionContext): Unit = {
    Future {
      promise.completeWith(folder(Step.Cont[T, Unit] {
        case Input.EOF =>
          subscriber.onComplete()
          Done(())
        case Input.El(t) =>
          subscriber.onNext(t)
          self
        case Input.Empty =>
          self
      }))
    }(ec)
  }

  private def cancelled[B](promise: Promise[B], folder: (Step[T, Unit]) => Future[B], ec: ExecutionContext): Unit = {
    Future {
      promise.completeWith(folder(Step.Done((), Input.Empty)))
    }(ec)
  }

  def cancel() = exclusive {
    case AwaitingDemand(_, cancelled) =>
      cancelled()
      state = Cancelled
    case _ =>
      state = Cancelled
  }

  def request(n: Long) = exclusive {
    case NoDemand =>
      state = Demand(n)
    case AwaitingDemand(demand, _) =>
      demand()
      if (n == 1) {
        state = NoDemand
      } else {
        state = Demand(n - 1)
      }
    case Demand(old) =>
      state = Demand(old + n)
    case Cancelled =>
    // nop, 3.6 of reactive streams spec
    case NotSubscribed =>
      throw new IllegalStateException("Demand requested before subscription made")
  }
}
