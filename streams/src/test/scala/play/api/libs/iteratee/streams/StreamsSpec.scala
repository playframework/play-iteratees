/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.iteratee.streams

import org.specs2.mutable.Specification
import play.api.libs.iteratee.streams.impl.FuturePublisher
import scala.concurrent.Future

class StreamsSpec extends Specification {

  "Streams helper interface" should {
    // TODO: Better tests needed, these are only here to ensure Streams compiles
    "create a Publisher from a Future" in {
      val pubr = IterateeStreams.futureToPublisher(Future.successful(1))
      pubr must haveClass[FuturePublisher[Int]]
    }
  }

}
