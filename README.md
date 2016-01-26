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
As in java, Application Objects must define a `def main(args: Array[String])` method. This can also be achieved by extending the App trait, and placing the code in the constructor body. `object Hello extends App { println("Hello, World!")}`
