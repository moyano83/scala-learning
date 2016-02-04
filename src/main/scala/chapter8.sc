import scala.collection.mutable.ArrayBuffer

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}
// ex 1
class CheckingAccount (initialBalance : Double) extends BankAccount(initialBalance){
  protected var balance = initialBalance
  private val charges = 1
  override def deposit(amount:Double): Double = {
    if (balance > 1) balance = balance - charges + amount
    balance
  }
  override def withdraw(amount : Double): Double ={
    val result = balance - amount - charges
    if(result > 0){
      balance = result
      amount
    }else{
      0
    }
  }
}
// ex 2
class SavingsAccount(initialBalance: Double) extends CheckingAccount(initialBalance){
  private val interest = .05 // 5% interest
  def earnMonthlyInterest(): Unit ={
    balance+=(balance*interest)
  }
}
val saving = new SavingsAccount(20)
println(saving.withdraw(10))
println(saving.withdraw(10))
saving.earnMonthlyInterest()
println(saving.deposit(10))

// ex 4
abstract class Item {
  def price : Double
  def description : String
}

class SimpleItem(val price : Double, val description : String)
  extends Item{}

class Bundle extends Item{
  var bundleList = new ArrayBuffer[Item]
  def price = bundleList.map(f => f.price).reduce((a,b) => a+b)
  def description = bundleList.map(f => f.description).mkString(",")
  def addItem(a: Item) = bundleList.+=(a)
}

// ex 6
class Point(x:Double, y : Double){}
class LabeledPint(label:String, x:Double, y : Double) extends Point(x,y){}

