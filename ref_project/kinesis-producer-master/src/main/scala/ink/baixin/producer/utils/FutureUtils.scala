package ink.baixin.producer.utils

import com.google.common.util.concurrent.{FutureCallback, Futures, ListenableFuture}

import scala.concurrent.{Future, Promise}


object FutureUtils {

  /**
    * Provides implicit extensions to improve usability of Java Futures in Scala.
    */
  implicit class ScalaListenableFuture[T](lf: ListenableFuture[T]) {

    /**
      * Converts a Guava ListenableFuture to a Scala Future.
      */
    def asScalaFuture: Future[T] = {
      val p = Promise[T]()
      Futures.addCallback(lf, new FutureCallback[T] {
        def onSuccess(result: T): Unit = p success result

        def onFailure(t: Throwable): Unit = p failure t
      })
      p.future
    }
  }

}
