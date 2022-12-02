import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.IndexedSeqView.Map
import scala.math.Ordering

object main {
def p1(other : Char, mine : Char) = {
  val a = other match {
    case 'A' => 1
    case 'B' => 2
    case 'C' => 3
  }
  val b = mine match {
    case 'X' => 1
    case 'Y' => 2
    case 'Z' => 3
  }

  //Magic
  b + ((( b + 4 - a) % 3) * 3)
}
def p2(other : Char, mine : Char) = {
  val a = other match {
    case 'A' => 1
    case 'B' => 2
    case 'C' => 3
  }
  val b = mine match {
    case 'X' => 0
    case 'Y' => 1
    case 'Z' => 2
  }
  // Magic
  
  b * 3 + ((a + b + 1) % 3) + 1
}

def main(args: Array[String]) = {
  val res = Source.fromFile("input.txt").getLines().map(x => p1(x(0), x(2))).sum
  println(res)
  val res2 = Source.fromFile("input.txt").getLines().map(x => p2(x(0), x(2))).sum
  println(res2)
}
}
