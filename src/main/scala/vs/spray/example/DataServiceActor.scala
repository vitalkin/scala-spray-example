package vs.spray.example

import akka.actor.{ActorRefFactory, Actor}
import spray.http.MediaTypes._
import spray.http.StatusCodes
import spray.routing.HttpService

class DataServiceActor extends Actor with HttpService with DataRepository with TemplateRenderer {

  override implicit def actorRefFactory: ActorRefFactory = context

  implicit val system = context.system

  override def receive: Receive = runRoute(defaultRoute)

  val defaultRoute = {
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            renderData(findAllEntries)
          }
        }
      }
    } ~
    path("add") {
      post {
        formFields('description) { description =>
          addEntry(description)
          redirect("/", StatusCodes.Found)
        }
      }
    } ~
    path("remove" / IntNumber) { id =>
      get {
        removeEntry(id)
        redirect("/", StatusCodes.Found)
      }
    }
  }

}
