package vs.spray.example

import org.scalatest.{BeforeAndAfter, FunSuite}

class DataRepositorySuite extends FunSuite with BeforeAndAfter {

  var repository: DataRepository = _

  before {
    repository = new DataRepository {}
  }

  test("test add new entry") {
    val text = "this is new entry"
    repository.addEntry(text)
    val result = repository.findAllEntries.toList

    assert(result.size == 1)
    assert(result.head._2.text == text)
  }

  test("test remove entry") {
    repository.addEntry("this is new entry")
    val addedEntry = repository.findAllEntries.next
    repository.removeEntry(addedEntry._1)

    val result = repository.findAllEntries.toList

    assert(result.isEmpty)
  }

}
