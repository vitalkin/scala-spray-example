package vs.spray.example

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import akka.pattern.ask
import spray.can.Http
import scala.concurrent.duration._

object Application extends App {

  implicit val system = ActorSystem()
  val service = system.actorOf(Props[DataServiceActor])

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

}