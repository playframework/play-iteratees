[<img src="https://img.shields.io/travis/playframework/play-iteratees.svg"/>](https://travis-ci.org/playframework/play-iteratees) [![Maven](https://img.shields.io/maven-central/v/com.typesafe.play/play-iteratees_2.12.svg)](http://mvnrepository.com/artifact/com.typesafe.play/play-iteratees_2.12)

# Play Iteratees Library

This is the Play iteratees library.  It has two modules, `play-iteratees`, which provides the iteratees themselves, as well as `play-iteratees-reactive-streams`, which provides a reactive streams implementation on top of iteratees.

# End of Life

The active Playframework contributors consider this repository has reached End of Life and can be archived. If you want to develop this library further, feel free to reach out.

**We'll archive this repository by End of July 2020.**

Thank you to all contributors that worked on this repository!


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
