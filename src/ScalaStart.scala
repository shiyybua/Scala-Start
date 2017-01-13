/**
  * Scala 特性：课扩展性强。
  */
object ScalaStart {
  def main(args: Array[String]): Unit = {
    //对于无参的函数，括号可以不加
    _Map

    //print(factorial(30))
    Upper("Hello")
    println(Predicate(3,4))
    whileLoop
    ForLoop
  }

  /*
    Map 是指映射
    这里 += 是指追加映射
   */
  def _Map: Unit = {
    // var 指可变量，val 是指不可变量。
    var capital = Map("US"->"Washington", "France" -> "Paris")
    capital += ("Japan" -> "Tokyo")
    println(capital("Japan"))
  }

  /*
    很方便的一个递归，BigInt 是Scala的一个特殊类型。可以表示很大的整型值。
    之所以这个递归如此简单是基于Scala的一个特性：函数的最后一行的值就是默认的返回值。
    其实BigInt的实现，是调用了 java.math.BigInteger。 Scala可以直接调用Java库！
   */
  def factorial(x: BigInt) : BigInt = {
    if (x == 0) 1 else x * factorial(x - 1)
  }

  /*
    判断字符串中是否含有大写。
   */
  def Upper(name: String): Unit = {
    val nameHasUpperCase = name.exists(_.isUpper)
    println(nameHasUpperCase)
  }

  /*
    函数的最后一个值被默认是返回值，如果没有写return的话。
    此函数还没写成如下形式：
    def Predicate(x: Int, y: Int) = if (x > y) x else y
    这里可以看出，函数名后面跟的Int 是返回值类型。

   */
  def Predicate(x : Int , y : Int) : Int = {
    if (x > y) x else y
  }

  def whileLoop : Unit = {
    var i = 10
    while (i != 0) {
      print(i + " ")
      i -= 1
    }
    println()
  }

  def ForLoop : Unit = {
    val arr = Array("Zara", "Nuha", "Ayan")
    // x 是从arr里面提出的值，然后传给下一个函数print 做处理！相当于lambda.
    arr.foreach(x => print(x + " "))
    //相反，如果没有在外面指定数组，则在内部必须把数组值传给变量！
    for(x <- arr){
      print(x + " ")
    }
  }

}
