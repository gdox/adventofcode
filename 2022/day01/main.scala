import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.IndexedSeqView.Map
import scala.math.Ordering

object main {
def main(args: Array[String]) = {
  var sum = 0
  var finals = new ArrayBuffer[Int]()
  for(line <- Source.fromFile("input.txt").getLines()) {
    if(line.size == 0) {
      finals += sum
      sum = 0
    } else {
      sum += line.toInt
    }
  }
  if(sum > 0) {
    finals += sum
    sum = 0
  }
  println(finals.toArray.max)
  val orderedfinals = finals.sortWith(_ > _)
  println(orderedfinals.take(3).sum)
}
}
