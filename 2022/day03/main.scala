import scala.io.Source
import scala.collection.immutable.List
import scala.collection.mutable.ArrayBuffer

object main {
def value(a : Int) : Int = {
  if (a > 'Z') {
    return a + 1 - 'a'
  } else {
    return a + 27 - 'A'
  }
}
def find_common(arg : String) : Int = {
  var first = arg.substring(0, arg.size / 2)
  var second = arg.substring(arg.size / 2)
  for (a <- first) {
    for (b <- second) {
      if (a == b) {
        return value(a)
      }
    }
  }
  0
}

def count(arg : String) : Array[Int] = {
  var result = new Array[Int](256)
  for (i <- arg) {
    result(i) = 1
  }
  return result
}

def join(a : Array[Int], b : Array[Int]) : Array[Int] = {
  a.zip(b).map({case (x, y) => x + y})
}
def find_in_group(arg : Seq[String]) : Int = {
  value(arg.map(count).foldLeft(new Array[Int](256))(join).indexOf(3))
}
def main(args: Array[String]) = {
  
  val res = Source.fromFile("input.txt").getLines().map(find_common).sum
  println(res)
  val res2 = Source.fromFile("input.txt").getLines().grouped(3).map(find_in_group).sum
  println(res2)
}
}
