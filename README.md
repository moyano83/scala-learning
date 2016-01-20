## KEY CONCEPTS
- 


# Chapter 1
There is no ++ operator in scala, instead there is +=1. Scala has functions and methods, a function is not attached to a class, for example math functions (add import math._ to use them). 
In scala, the method apply is like an overload of the method (), for example: `"hello"(4)` is equivalent to `"hello".apply(4)`

# Chapter 2
Scala has methods, which operates on an object, and function which doesn't, as similarity, you can think of it as an static method in java.
A function definition example: `def abs(n : Int) = { sqrt( n * n) }` recursive functions definition requires to provide a return type:
`def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)`

