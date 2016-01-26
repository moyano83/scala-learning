//Exercises
//1
val ex1p1 = Map[String, Double]("item1"->100, "item2"->200)
var ex1p2 = new collection.mutable.HashMap[String, Double]
for((k,v)<- ex1p1){
  ex1p2 += (k -> v * 0.9)
}
println("res 1:" + ex1p2.mkString(","))
//2
var text = "this is a test to check a word count program and it count word"
var arrayOfTuple = text.split(" ").map((p: String) => (p,1))
var map = collection.mutable.HashMap[String, Int]()
for(a<-arrayOfTuple){
  map(a._1) = a._2 + map.getOrElse(a._1, 0)
}
println(map.mkString("-"))

//7
import collection.JavaConversions.propertiesAsScalaMap
import scala.collection.immutable.StringOps
val prop : collection.Map[String, String] = System.getProperties()
val spaces = prop.maxBy(_._2.length)._2.length + 1
for((k,v)<-prop) {
  val format = " ".*(spaces - k.length)
  println (k + format + "|" + v)
}
//10
"Hello".zip("World")
//res5: scala.collection.immutable.IndexedSeq[(Char, Char)]
// = Vector((H,W), (e,o), (l,r), (l,l), (o,d))