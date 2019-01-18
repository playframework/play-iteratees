/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.iteratee

import scala.concurrent.ExecutionContext

object TestExecutionContext {

  /**
   * Create a `TestExecutionContext` that delegates to the iteratee package's default `ExecutionContext`.
   */
  def apply(): TestExecutionContext = new TestExecutionContext(Execution.trampoline)

}

/**
 * An `ExecutionContext` that counts its executions.
 *
 * @param delegate The underlying `ExecutionContext` to delegate execution to.
 */
class TestExecutionContext(delegate: ExecutionContext) extends ExecutionContext {

  val count = new java.util.concurrent.atomic.AtomicInteger()

  def execute(runnable: Runnable): Unit = {
    count.getAndIncrement()
    delegate.execute(runnable)
  }

  def reportFailure(t: Throwable): Unit = {
    println(t)
  }

  def executionCount: Int = count.get()

}

