[<img src="https://img.shields.io/travis/playframework/play-iteratees.svg"/>](https://travis-ci.org/playframework/play-iteratees) [![Maven](https://img.shields.io/maven-central/v/com.typesafe.play/play-iteratees_2.12.svg)](http://mvnrepository.com/artifact/com.typesafe.play/play-iteratees_2.12)

# Play Iteratees Library

This is the Play iteratees library.  It has two modules, `play-iteratees`, which provides the iteratees themselves, as well as `play-iteratees-reactive-streams`, which provides a reactive streams implementation on top of iteratees.

## Library Dependencies

To add play-iteratees to your project:

```scala
libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"
```

To add play-iteratees reactive streams to your project:

```scala
libraryDependencies += "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1"
```

## Documentation

The Play Iteratees implementation is described in the Play 2.5.x documentation on Iteratees.  If you are looking to migrate from iteratees to streams, the migration guide has a section:

* https://www.playframework.com/documentation/2.4.x/Iteratees
* https://www.playframework.com/documentation/2.5.x/StreamsMigration25

If you are using Play 2.6.x or higher and are looking for `Streams` class, you can find it under:

* https://github.com/playframework/play-iteratees/blob/master/streams/src/main/scala/play/api/libs/iteratee/streams/IterateeStreams.scala

## Support

The Play iteratees library is *[Community Driven][]*.

[Community Driven]: https://developer.lightbend.com/docs/reactive-platform/2.0/support-terminology/index.html#community-driven
