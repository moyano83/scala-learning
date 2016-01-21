import scala.collection.mutable.ArrayBuffer

var a = Array[Int](2, 5, 8, -2, 1)
for(i<- a if i%2==0) //The if condition is call guard
  yield i *2

var check = true
var count=0
val result = new ArrayBuffer[Int]();
for (i <- a if check ) {
 if (i < 0)
    check = false; a.toBuffer.take(count)
  count+=1
}

Array(1,3,4).sum
Array(1,4,3).sortWith(_<_)

//Exercises
//1
val ex1 = Array(1 to 10)
//2
var ex2 = Array(2, -1, -4, 3, 5)
for (i<-1 until (ex2.length, 2)){
  if (i!=0 && ex2.length>1) {
    val aux = ex2( i - 1)
    ex2(i -1) = ex2(i)
    ex2(i ) = aux
  }
}
println("res: 2" + ex2.mkString(","))
//4
var ex4 = ex2.filter((i1:Int)=> i1>=0)
ex4 ++= ex2.filter((i1:Int)=>i1<0)
println("res 4:" + ex4.mkString(","))
//5
val ex5 = Array[Double](3.0, 5.2, 7.9, 1.0)
println("res 5:" + ex5.sum/ex5.length)
//6
val ex6p1 = Array(1,2,3)
val ex6p2 = ArrayBuffer(1,2,3)
println("res 6:" + ex6p1.reverse.mkString(",") +
  " -->" + ex6p2.reverse.mkString(","))
//7
val ex7 = Array(1,6,6,2,5)
println("res 7: " + ex7.distinct.mkString(","))
//9
import java.util.TimeZone
val timezones = TimeZone.getAvailableIDs
println("res 9:"+timezones.filter((p:String)=>
  p.contains("America")).map((p:String)=>p.replace("America/", "")).mkString(","))