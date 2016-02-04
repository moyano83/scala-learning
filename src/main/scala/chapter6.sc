// ex 1
class UnitConversion{
  val inchesToCm = 2.54
  val milesToKm = 1.60934
  val gallonsToLiter = 3.78541
  def inchesToCm (inches: Double): Double = inches * inchesToCm
  def milesToKm (km: Double): Double = km * milesToKm
  def gallonsToLiter (gallons : Double): Double = gallons * gallonsToLiter
}
// ex 2
object Reverse extends App{
  print(args.reverse.mkString(" "))
}
val a = Array("Hello", "World")
Reverse.main(a)

