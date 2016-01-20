//This are the coding examples of chapter 2
// in Scala for the impatient book


//example of while expression
var s = 1
while (s < 10){
  s+=1
}
println(s)

//example of for loop
for (i <-1 to s)
  println(i)

//example of various loop definitions (not it is similar
// to a cartesian product of the defined inner loops
// yield construct a collection of values returned
for (i <- 1 to 3; x = 4 - i; j <- x to 3)
  yield((10 * i + j))

import scala.math._
def abs(n : Int) = { sqrt( n * n) }

