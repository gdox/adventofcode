import scala.io.Source

object main {
  def first(s : String, size : Int) = {
    s.sliding(size).indexWhere(x => x.toSet.size == size) + size
  }
  def main(args : Array[String]) = {
    val input = Source.fromFile("input.txt").getLines().next
    println(first(input, 4))
    println(first(input, 14))
  }
}
