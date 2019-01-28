package play.api.libs.iteratee

package object ccompat {
  type TraversableLike[X, Y] = scala.collection.IterableOps[X, Iterable, Y]
}
