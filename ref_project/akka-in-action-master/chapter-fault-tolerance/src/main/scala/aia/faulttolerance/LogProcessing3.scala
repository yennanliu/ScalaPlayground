package aia.faulttolerance

import akka.actor._
import java.io.File
import akka.actor.SupervisorStrategy.{ Stop, Resume, Restart }
import akka.actor.OneForOneStrategy
import scala.concurrent.duration._
import language.postfixOps

package dbstrategy3 {

  object LogProcessingApp extends App {
    val sources = Vector("file:///source1/", "file:///source2/")
    val system = ActorSystem("logprocessing")
    // create the props and dependencies
    val databaseUrl = "http://mydatabase"
    
    val writerProps = Props(new DbWriter(databaseUrl))
    val dbSuperProps = Props(new DbSupervisor(writerProps))
    val logProcSuperProps = Props(
      new LogProcSupervisor(dbSuperProps))
    val topLevelProps = Props(new FileWatcherSupervisor(
      sources,
      logProcSuperProps))
    system.actorOf(topLevelProps)
  }



  class FileWatcherSupervisor(sources: Vector[String],
                               logProcSuperProps: Props)
    extends Actor {

    var fileWatchers: Vector[ActorRef] = sources.map { source =>
      val logProcSupervisor = context.actorOf(logProcSuperProps)
      val fileWatcher = context.actorOf(Props(
        new FileWatcher(source, logProcSupervisor)))
      context.watch(fileWatcher)
      fileWatcher
    }

    override def supervisorStrategy = AllForOneStrategy() {
      case _: DiskError => Stop
    }

    def receive = {
      case Terminated(fileWatcher) =>
        fileWatchers = fileWatchers.filterNot(w => w == fileWatcher)
        if (fileWatchers.isEmpty) self ! PoisonPill
    }
  }



  class FileWatcher(sourceUri: String,
                    logProcSupervisor: ActorRef)
    extends Actor with FileWatchingAbilities {
    register(sourceUri)

    import FileWatcherProtocol._
    import LogProcessingProtocol._

    def receive = {
      case NewFile(file, _) =>
        logProcSupervisor ! LogFile(file)
      case SourceAbandoned(uri) if uri == sourceUri =>
        self ! PoisonPill
    }
  }



  class LogProcSupervisor(dbSupervisorProps: Props)
    extends Actor {
    override def supervisorStrategy = OneForOneStrategy() {
      case _: CorruptedFileException => Resume
    }
    val dbSupervisor = context.actorOf(dbSupervisorProps)
    val logProcProps = Props(new LogProcessor(dbSupervisor))
    val logProcessor = context.actorOf(logProcProps)

    def receive = {
      case m => logProcessor forward (m)
    }
  }



  class LogProcessor(dbSupervisor: ActorRef)
    extends Actor with LogParsing {
    import LogProcessingProtocol._
    def receive = {
      case LogFile(file) =>
        val lines = parse(file)
        lines.foreach(dbSupervisor ! _)
    }
  }


  class DbImpatientSupervisor(writerProps: Props) extends Actor {
    override def supervisorStrategy = OneForOneStrategy(
      maxNrOfRetries = 5,
      withinTimeRange = 60 seconds) {
        case _: DbBrokenConnectionException => Restart
      }
    val writer = context.actorOf(writerProps)
    def receive = {
      case m => writer forward (m)
    }
  }



  class DbSupervisor(writerProps: Props) extends Actor {
    override def supervisorStrategy = OneForOneStrategy() {
      case _: DbBrokenConnectionException => Restart
    }
    val writer = context.actorOf(writerProps)
    def receive = {
      case m => writer forward (m)
    }
  }



  class DbWriter(databaseUrl: String) extends Actor {
    val connection = new DbCon(databaseUrl)

    import LogProcessingProtocol._
    def receive = {
      case Line(time, message, messageType) =>
        connection.write(Map('time -> time,
          'message -> message,
          'messageType -> messageType))
    }
  }

  class DbCon(url: String) {
    /**
     * Writes a map to a database.
     * @param map the map to write to the database.
     * @throws DbBrokenConnectionException when the connection is broken. It might be back later
     * @throws DbNodeDownException when the database Node has been removed from the database cluster. It will never work again.
     */
    def write(map: Map[Symbol, Any]):Unit = {
      //
    }
    def close(): Unit = {
      //
    }
  }

  @SerialVersionUID(1L)
  class DiskError(msg: String)
    extends Error(msg) with Serializable

  @SerialVersionUID(1L)
  class CorruptedFileException(msg: String, val file: File)
    extends Exception(msg) with Serializable

  @SerialVersionUID(1L)
  class DbBrokenConnectionException(msg: String)
    extends Exception(msg) with Serializable


  trait LogParsing {
    import LogProcessingProtocol._
    // Parses log files. creates line objects from the lines in the log file.
    // If the file is corrupt a CorruptedFileException is thrown
    def parse(file: File): Vector[Line] = {
      // implement parser here, now just return dummy value
      Vector.empty[Line]
    }
  }
  object FileWatcherProtocol {
    case class NewFile(file: File, timeAdded: Long)
    case class SourceAbandoned(uri: String)
  }
  trait FileWatchingAbilities {
    def register(uri: String): Unit = {

    }
  }


  object LogProcessingProtocol {
    // represents a new log file
    case class LogFile(file: File)
    // A line in the log file parsed by the LogProcessor Actor
    case class Line(time: Long, message: String, messageType: String)
  }


}
