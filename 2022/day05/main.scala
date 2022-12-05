import scala.io.Source
import scala.collection.mutable.Stack

object main {
  def parse(stack : List[String], op : List[String], update : Boolean) = {
    var stacklist = stack.map(x => x.grouped(4).zip(1 until 10).map({case (y, x) => (x, y(1))})).flatten.filter(_._2 != ' ').toList.groupMapReduce(_._1)(x =>Stack(x._2))(_ ++ _)
    
    for (s <- op) {
      val commands = s.split(' ').toList
      val count = commands(1).toInt
      val from = commands(3).toInt
      val to = commands(5).toInt
      if (update) {
        stacklist(to).prependAll(stacklist(from).take(count))
        for (i <- 0 until count) {
          stacklist(from).pop
        }
      } else {
        for (i <- 0 until count) {
          stacklist(to).push(stacklist(from).pop)
        }
      }
    }
    (1 until 10).map(x => stacklist(x).top).mkString
  }
  def main(args : Array[String]) = {
    val input = Source.fromFile("input.txt").getLines().toList
    println(parse(input.take(8), input.drop(10), false))
    println(parse(input.take(8), input.drop(10), true))
  }
}
