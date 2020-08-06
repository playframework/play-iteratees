# End of Life

The active Playframework contributors consider this repository has reached End of Life and archived it.

This repository is not being used anymore and won't get any further updates.

Thank you to all contributors that worked on this repository!

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
