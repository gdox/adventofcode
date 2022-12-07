import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

object main {
  def main(args : Array[String]) = {
    var stack = new ArrayBuffer[String]
    stack += ""
    var acc = new HashMap[String, Int]
    for (line <- Source.fromFile("input.txt").getLines()) {
      if (line.startsWith("$ cd")) {
        val linebutnocommand = line.drop(5)
        if (linebutnocommand == "/") {
          stack.clear
          stack += ""
        } else if (linebutnocommand == "..") {
          stack = stack.dropRight(1)
        } else {
          stack += linebutnocommand
        }
      } else if (line.startsWith("dir")) {
        // Directory, skip
      } else if (line.startsWith("$ ls")) {
        // List, skip
      } else {
        var stack2 = new ArrayBuffer[String]
        val size = line.split(' ')(0).toInt
        for (a <- stack) {
          stack2 += a
          acc.updateWith(stack2.mkString("/"))(x => Some(x.getOrElse(0) + size))
        }
      }
    }
    println(acc.values.filter(_ <= 100000).sum)
    val sizeToDelete =  acc("") - 40000000
    println(acc.values.filter(_ >= sizeToDelete).min)
  }
}
