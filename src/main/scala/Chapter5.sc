

//2
class BankAccount(private var balance: Double) {
  def deposit(amount: Double) = balance += amount

  def withdraw(amount: Double): Double = {
    if (amount > balance) balance -= amount
    balance
  }
}

//3 Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks whether this time comes before the other. A Time object should be constructed as new Time(hrs, min), where hrs is in military time format (between 0 and 23).
class Time(private val hours :Int, private val minutes:Int){
  def before(other: Time): Boolean ={
    if(this.hours == other.hours) this.minutes<other.minutes
    else this.hours<other.hours
  }
}

import scala.beans.BeanProperty
//5 Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?
class Student(@BeanProperty var id: Long){
  @BeanProperty var name :String =""
}

//6
class Person(private var age :Int) {
  if (age<0) age=0
}
