package random {

class test extends App {
  for (a <- 1 to 4)
    print(nextInt)
}
}

package object random{
  var ct = 1
  def nextInt: Int = {
    ct = ct * 1664525 + (1013904223 % 2 ^ 32)
    ct
  }
}



