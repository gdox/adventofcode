import scala.io.Source
import scala.collection.immutable.HashMap
import scala.collection.immutable.HashSet
import scala.collection.immutable.Range


object main {
  def takeWhile(a : Range, f : Int => Boolean) : Int = {
    val r = a.takeWhile(f).size
    r + (if (r < a.size) 1 else 0)
  }
  def main(args : Array[String]) = {
    val heightmap = new HashMap[(Int, Int), Int].concat(Source.fromFile("input.txt").getLines().zipWithIndex
      .map({case (line, y) => {
        line.zipWithIndex.map({case (heightChar, x) => ((x, y), heightChar - '0')})
      }}).flatten)
    val xmin = 0
    val xmax = heightmap.keys.map(_._1).max + 1
    val ymin = 0
    val ymax = heightmap.keys.map(_._2).max + 1
    var visible = new HashSet[(Int, Int)]
    visible = visible ++ heightmap.keys.filter({ case (x, y) => (xmin until x).map(i => heightmap((i, y)) < heightmap(x, y)).reduceOption(_ && _).getOrElse(true)})
    visible = visible ++ heightmap.keys.filter({ case (x, y) => (x + 1 until xmax).map(i => heightmap((i, y)) < heightmap(x, y)).reduceOption(_ && _).getOrElse(true)})
    visible = visible ++ heightmap.keys.filter({ case (x, y) => (ymin until y).map(i => heightmap((x, i)) < heightmap(x, y)).reduceOption(_ && _).getOrElse(true)})
    visible = visible ++ heightmap.keys.filter({ case (x, y) => (y + 1 until ymax).map(i => heightmap((x, i)) < heightmap(x, y)).reduceOption(_ && _).getOrElse(true)})
    println(visible.size)
    
    println(heightmap.keys.map({ case (x, y) => ((x, y), takeWhile((x - 1 to xmin by -1), i => heightmap((i, y)) < heightmap((x, y))))}).toList.concat(
    heightmap.keys.map({ case (x, y) => ((x, y), takeWhile((x + 1 until xmax by 1), i => heightmap((i, y)) < heightmap((x, y))))})).concat(
    heightmap.keys.map({ case (x, y) => ((x, y), takeWhile((y - 1 to ymin by -1), i => heightmap((x, i)) < heightmap((x, y))))})).concat(
    heightmap.keys.map({ case (x, y) => ((x, y), takeWhile((y + 1 until ymax by 1), i => heightmap((x, i)) < heightmap((x, y))))}))
      .groupMapReduce(_._1)(t => List[Int](t._2))(_ ++ _).map(_._2.reduce(_ * _)).max
    )
  }
}
