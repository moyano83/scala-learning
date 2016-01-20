## KEY CONCEPTS

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
Traversing an array:
`for(i <- 1 until a.size)`
