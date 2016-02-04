# Chapter 1: Basics
There is no ++ operator in scala, instead there is +=1. Scala has functions and methods, a function is not attached to a class, for example math functions (add import math._ to use them).
In scala, the method apply is like an overload of the method (), for example: `"hello"(4)` is equivalent to `"hello".apply(4)`

# Chapter 2: Control Structures and Functions
**FUNCTIONS**
Scala has methods, which operates on an object, and function which doesn't, as similarity, you can think of it as an static method in java.
A function definition example:
`def abs(n : Int) = { sqrt( n * n) }`
Recursive functions definition requires to provide a return type:
`def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)`
Named parameters with default values
`def decorate(left : String="[", text: String, right: String ="]") = left + text + right`
Function with variable number of arguments:
```
def sum(n : Int*)={
    var result = 0
    for (arg <- args.elements) result += arg result
    }
```
**PROCEDURES**
A procedure does not return anything, so we can omit the equal symbol in the declaration:
`def greeting(name: String){ println ("Hello " + name)}`
**Lazy value initializations**
We can defer the initialization of a value until it is accessed the first time by defining it as lazy:
`lazy val words = io.Source.fromFile("/usr/share/dict/words").mkString`
**EXCEPTIONS**
There is no checked exceptions in Scala, but you can throw and catch exceptions. An exception has a type of `Nothing`.
The syntax for catching exceptions is modeled after the pattern-matching syntax
```
try {
    process(new FileReader(filename))
} catch {
    case _: FileNotFoundException => println(filename + " not found")
    case ex: IOException => ex.printStackTrace()
}
```
it is also possible to use a `try{...}finally{...}` construction.

#Chapter 3: Arrays
An Array is used when the size of the collection won't change
`val a = new Array[String](10)` or `var a = new Array("Hello","World")`
For arrays that grow and shrink use ArrayBuffer
`val a = new ArrayBuffer[Int]()`
You can add elements `a += (1,3)`, insert elements `a.insert(2, 4)`, trim elements (remove them from the end) `a.trimEnd(2)` or remove them at an specific location `a.remove(2)` or to remove 4 elements at position 2 `a.remove(2, 4)`. Inserting at specific locations is not as performant as deleting or appending as part of the array has to be moved.
Use `a.toArray` to convert an arraybuffer to an array and `a.toBuffer` from Array to buffer.
Traversing an array is done with for (i <- Range):
`for(i <- 1 until a.size) //i goes from 0 to a.size - 1. Check RichInt until method`
**Common Methods**
Sum: `Array(1,4,3).sum //yields 8`
OrderWith `Array(1,4,3).sortWith(_<_) //yields Array(1, 3, 4) does not modify the original array`
mkString: `Array(1,5,2).mkString(" $ ") / yields '1 & 5 & 2'`
**Multidimensional Arrays**
`val matrix = Array.ofDim[type](n_rows, n_columns)`
**Interoperatibility with Java**
To transform scala collections to java ones, use the `import collection.JavaConversions` for example: `val commands = collection.mutable.ArrayBuffer("ls", "-al", "/home/cay")`
To convert java to scala use the scala Buffer conversions `val cmds : collection.mutable.Buffer[String] = pb.commands() // pb.commands returns a List<?>`

#Chapter 4: Maps and Tuples
**Constructing Maps**
Immutable Map: `val map = Map("a"=>1, "b"=>2)` or `val map = Map(("a",1), ("b",2))`
Mutable map: `var map = collections.mutable.HashMap("a"=>1, "b"=>2)`
**Accessing values**
Retrieving values:`val bobsScore = scores("Bob")`
Check for existance: `val bobsScore = scores.contains("Bob")`
Get and if not exists set default: `val bobsScore = scores.getOrElse("Bob", 0)`
**Updating Values**
Update a single value: `scores("bob")=4`
Adding multiple associations: `scores += ("Bob" -> 10, "Fred" -> 7)`
Removing: `scores -= "bob"`
**Iteration**
Traversing the map: `for((k,v)<-map) ...`
**Java interoperation**
From Java to Scala:
```
import collection.JavaConversions.mapAsScalaMap
import collection.JavaConversions.propertiesAsScalaMap
val scores: collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
val props: collection.Map[String, String] = System.getProperties()
```
From Scala to Java:
```
import collection.JavaConversions.mapAsJavaMap
import java.awt.font.TextAttribute._ // Import keys for map below
val attrs = Map(FAMILY -> "Serif", SIZE -> 12) // A Scala map
val font = new java.awt.Font(attrs) // Expects a Java map
```
**Tuples**
Tuples are aggregates of values of different types, for example `(1, 3.14, "Fred")` is a tuple of type `Tuple3[Int, Double, java.lang.String]`.
You can access the elements of a tuple like this: `t._1 //Returns 1 in the example above, tuples index starts with 1`, it is also possible to name the elements `val (a1, a2, a3) = t //a3 be set to "Fred"`
**Zipping**
Tuples can be processed together:
```
val symbols = Array("<", "-", ">");
val counts = Array(2, 10, 2)
val pairs = symbols.zip(counts) //yields an array of pairs Array(("<", 2), ("-", 10), (">", 2))
```

#Chapter 5: Classes
**Simple classes and parameterless methods**
Scala classes are not declared public, a source file can contain multiple classes (all public).
```
class Person{
    private val age:Int = 0//Must initialize the variable
    def absoluteAge = age//Parameterless methods need not to include parenteses
}
```
**Properties with getters and setters**
Scala provides getters/setters for all the fields: `class Person { var age: Int = 0} //Scala generates a class for the JVM with a private age field and getter and setter methods`
The getter and setter methods are called `<class_name>.<property_name>` and `<class_name>.<property_name>_=(<value>)` which can be overriden.

**Properties with only getters**
If a property of a class is defined as val, no setter method is provided, and the field is marked as final.
If you want to protect the property but have it mutable, then you can declare it private and provide the getter method
```
class Counter {
    private var value: Int = 0
    def increment() { value += 1 }
    def current = value // No () in declaration, calling current() would result in error
}
```
**Object private fields**
Private fields in objects can be accessed by instances of the same class. However it is possible to restrict the access to such properties by declaring them as:
`private[this] age: Int` in this case, no getter or setter methods are generated at all.
**Bean properties**
If a property is annotated with `@reflect.BeanProperty`, then the standard getter and setter methods are generated in addition to the scala getters and setters:
**Auxiliary Constructors**
Auxiliary constructors in scala are called this (as in oposition to Java where the constructor has the same name than the class).
```
class Person{
private var age: Int =0
private var name: String = ""
    def this(ageC:Int){
        this()
        this.age = ageC
    }
    def this(ageC:Int, nameC:String){
        this(ageC)
        this.name=nameC
    }
}
```
**The primary constructor**
A primary constructor it is not defined with the this method, the constructor is interwoven whith the class definition.
  - The parameters of the primary constructor are placed after the class name `class Person(val name: String, val age: Int) {...}`
  - Parameters of the primary constructor turn into fields that are initialized with the construction parameters
  - The primary constructor executes all statements in the class definition (useful when you need to configure a field during construction)
    - If a parameter without val or var is used inside at least one method, it becomes a field:
    `class Person(name: String, age: Int) {def description = name + " is " + age + " years old"}`
    - Otherwise, the parameter is not saved as a field. It’s just a regular parameter that can be accessed in the code of the primary constructor.
 **Nested Classes**
 The inner classes of two instances of the same type are effectively like if they were different classes.

#Chapter 6: Objects
**Singleton Objects**
To define a singleton instance use the object construct `object Account{...}` The constructor of an object is executed when the object is first used.
Constructor parameters are not allowed in Objects.
**Companion Objects**
To provide the equivalent of having static methods in a Java class, in scala we use a 'companion object'. Both the class and the companion object should
reside in the same source file. The class and it's companion object can access each other private features.
**Class extending class or traits**
An object can extend a class and/or one or more traits. One useful application is to specify default objects that can be shared.
**The apply method**
Typically an apply method returns an instance of the companion class.
```
Array("el1", "el2") //The companion class yields a new array with two values on it
```
**Application Objects**
As in java, Application Objects must define a `def main(args: Array[String])` method. This can also be achieved by extending the App trait, and placing the code in the constructor body. `object Hello extends App { println("Hello, World!")}`. To invoke a program in the command line, simply text:
`scala -Dscala.time Hello`
**Enumerations**
There is no enumeration type in scala, but you can extend the _Enumeration_ class. Enumerations hold values, and Ids, you can pass both or one in the definition of the enumeration. The Id of a value starts with 1 and continues adding one unless specified:
```
object TrafficLightColor extends Enumeration {
val Red = Value(0, "Stop")
val Yellow = Value(10) // Name "Yellow" 
val Green = Value("Go") // ID 11
}
```
The method `.id` of the Enumerator return the ID value of the enumerator. To get a value in an enumerator you can get it like `<enumerator>.(<id>)` or by value like `<enumerator>.withName(<value>)`

#Chapter 7: Packages and Imports
*Packages*
You can include package declarations inside package declarations. This is a valid declaration of the class foo.bar.test
```
package foo{
	package bar{
		class test{...}
	}
}
```
You can declare multiple packages and classes in the same file.
*Scope Rules*
Scala package scope is nested from the enclosing packages. A class can access another in a parent package, but you can break classes if you declare a package declaration with the same name that other that is already in use in the class. i.e:
```
package com { 
	package horstmann {
		package impatient { 
			class Manager {
			val subordinates = new scala.collection.mutable.ArrayBuffer[Employee]... 
			}
		} 
	}
}
//And then in another file you declare:
package com { 
		package horstmann {
			package scala {... }
		}
}
```
To avoid this, you can use the absolute package path `_root_.com.scala.collection.mutable.Arraybuffer`
*Chained Package Clauses*
If the pckage declaration is chained `package com.horstmann.impatient{ class Manager{...}}`, makes the objects in parent packages not visible. `com.horstmann.scala` won't be visible to `Manager`.
*Top of File Notation*
You can declare packages without braces, the same visibility rules apply:
```
package com.horstmann.impatient
package manager
```
The content of the package impatient will be visible to the classes in the package manager
*Package Objects*
You can define one package object per package to enclose utility methods:
```
package object people {
    val defaultName = "John Q. Public"
}
package people { 
    class Person {
        var name = defaultName // A constant from the package 
    }
}
```
Package objects are compiled into classes with static methods and fields (in the example `people/package.class`)
*Package Visibility*
To achieve the java's _default_ visibility in the package <name> you can define method that is accessible to all members of the package like this:
`private[<name>] def method...`
*Imports*
Use imports to use the short names instead of the long ones. You can import all the classes in a package with `<package>._`, which is equivalent to the * in java. Imports can be anywhere, even inside method declarations. To import a few members of a package, enclose them in brackets `import <package>.{member1, member2}`. 
You can also rename an imported class with the selector operand `import <package>.{member1 => renamed}` after this you can refer to the class as `<package>.renamed`. To hide a member of a package, use the selector `=>_` like this `<package>.{member=>_,_}`, after this the _member_ class is hidden.
Every Scala program starts with:
```
import java.lang._ 
import scala._ 
import Predef._
```
This means that you don't need to prepend imports with _scala._

#Chapter 8: Inheritance
*Extending a Class*
To extend a class use the _extends_ keyword. _final_ classes can't be extended, _final_ fieds can't be overrided.
*Overriding Methods*
In scala, you must use the _override_ modifier. To invoke the parents methods, use _super_ like in java.
*Type Check and Casts*
_instanceOf_ can be used to check class membership `<object>.instanceOf[<Class>]` if _<objec>_ is not an instance of _<Class>_ the method returns null. 
If you want to test wether a class is instance of another, but is not a subclass of another, use `p.getClass == classOf[<otherClass>]`. The classOf method is defined in the scala.Predef object that is always imported. 
*Protected Fields and methods*
A _protected_ field is accessible from any subclass, but not from other locations, not even from classes in the same package. There is a `protected[this]` variant to make an object protected.
*Superclass Construction*
An auxiliary constructor can never invoke a superclass constructor because they have to call either another auxiliary constructor or the primary constructor of the class.
The primary constructor can call a superclass constructor:
```
class Employee(name: String, age: Int, val salary : Double) extends Person(name, age)
```
*Overriding Fields*
You can override a _val_ or parameterless def with another val of the same name. The typical use case is to override an abstract def with a val.
```
abstract class Person { 
	def id: Int // Each person has an ID that is computed in some way ...
}
class Student(override val id: Int) extends Person
```
Restrictions:
- A def can only override another def.
- A val can only override another val or a parameterless def.
- A var can only override an abstract var 
*Anonymous subclases*
You can override a method and provide an anonymous implementation like this:
`new Person("name"){override val toString = "Name is "+ name}`
This creates a new structural type. Anonymous classes with the same structure are of the same type.
*Abstract Classes*
As in java, abstract classes can not be instantiated. methods with no body, for example `def val():Int` are abstract. Subclasses that implements an abstract method needs not to use the _override_ modifier.
*Abstract fields*
An abstract field is a field with no initial value. Subclasses need to provide an initial value, but no _override_ keyword is required.
*Construction Order and early definitions*
The constructor of a superclass is called before any other call in the class constructor, the initialization of the variables can be cumbersome, therefore don't rely on the value of a _val_ in the body of a constructor. However, to avoid surprises:
- Declare the val as final (not flexible) or lazy
- Use the early definition.
The early definition allows you to initialize a field in a class before its superclass sets it.
```
class Child extends { 
  override val inherit = 3
} with Parent
```
*Scala Inheritance hierarchy*
Classes that correspond to Java primitive types and the _Unit_ class, extends from _AnyVal_, all other classes from _AnyRef_ (equivalent to java's _Object_). _AnyRef_ and _AnyVal_ extends from the _Any_ class, the root of the hierarchy. All scala objects implements the interface _ScalaObject_ which has no methods (it is only a marker).
_Null_ type has a sole instance which value is _null_. It can't be assigned to the type _AnyVal_. There is a _Nothing_ type that has no instances, but it is used sometimes in generic constructs.
*Object Equality*
The _eq_ method of the _AnyRef_ object checks if two instances are the same object, the _equal_ methods calls _eq_ in _AnyRef_, it is advised to override this method `final override def equals(other: Any) = {...}`. The type of the parameter passed should be _Any_.

# Chapter 9: Files and Regular Expressions

