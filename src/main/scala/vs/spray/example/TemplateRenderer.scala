package vs.spray.example

import java.text.SimpleDateFormat
import java.util.Date

trait TemplateRenderer {

  def renderTasks(tasks: Iterator[(Int, Entry)]) = {
    <html>
      <body>
        <form action="add" method="post">
          <input name="description"></input><button type="submit">Add</button>
        </form>
        <ul>
          {
            tasks map { case (id, task) =>
              val date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(task.timestamp))
              <li>{ task.text } - { date }  <a href={ "remove/" + id }>x</a></li>
            }
          }
        </ul>
      </body>
    </html>
  }

}
