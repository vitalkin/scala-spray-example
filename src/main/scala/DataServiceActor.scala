import akka.actor.{ActorRefFactory, Actor}
import spray.http.MediaTypes._
import spray.http.StatusCodes
import spray.routing.HttpService

class DataServiceActor extends Actor with HttpService with TaskRepository with TemplateRenderer {

  override implicit def actorRefFactory: ActorRefFactory = context

  implicit val system = context.system

  override def receive: Receive = runRoute(taskRoutes)

  val taskRoutes = {
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            renderTasks(findAllTasks)
          }
        }
      }
    } ~
    path("add") {
      post {
        formFields('description) { description =>
          addTask(description)
          redirect("/", StatusCodes.Found)
        }
      }
    } ~
    path("remove" / IntNumber) { id =>
      get {
        removeTask(id)
        redirect("/", StatusCodes.Found)
      }
    }
  }

}
