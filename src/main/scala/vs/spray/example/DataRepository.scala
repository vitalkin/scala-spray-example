package vs.spray.example

import scala.collection.mutable.HashMap

case class Entry(val text: String, val timestamp: Long)

trait TaskRepository {

  val dataSource = HashMap[Int, Entry]()

  def findAllTasks: Iterator[(Int, Entry)] = {
    dataSource.iterator
  }

  def addTask(text: String): Unit = {
    dataSource += (dataSource.size + 1 -> Entry(text, System.currentTimeMillis()))
  }

  def removeTask(id: Int): Unit = {
    dataSource.remove(id)
  }

}
