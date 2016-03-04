package vs.spray.example

import scala.collection.mutable

case class Entry(val text: String, val timestamp: Long)

trait DataRepository {

  private val dataSource = mutable.HashMap[Int, Entry]()

  def findAllEntries: Iterator[(Int, Entry)] = {
    dataSource.iterator
  }

  def addEntry(text: String): Unit = {
    val max = if (dataSource.isEmpty) 0 else dataSource.maxBy(_._1)._1
    dataSource += (max + 1 -> Entry(text, System.currentTimeMillis()))
  }

  def removeEntry(id: Int): Unit = {
    dataSource.remove(id)
  }

}
