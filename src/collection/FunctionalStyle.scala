package collection


/**
  * 函数式编程风格！
  */
object FunctionalStyle {
  def main(args: Array[String]): Unit = {
    emuDemo
    filter

    println()
    val layes=Array[Int](4,5,4,3)
    for (x <- layes){
      print(x + " ")
    }

  }

  def cmpTwoStyles(s : String) = {

    // JAVA风格
    var filename = "default.txt"
    if (!s.isEmpty)
      filename = s

    // 函数式编程风格
    // 可以这么写的原因还是，Scala默认返回最后一个可执行的值。
    val filename_ =
      if (!s.isEmpty) s else "default.txt"

    // Scala中的while和do..while循环 和 JAVA几乎一样。
  }

  def emuDemo = {
    // 包涵了4
    for (i <- 1 to 4)
      print(i + " ")
    println()

    // 没有包涵4
    for (i <- 1 until 4)
      print(i + " ")
    println()
  }

  def filter = {
    // 过滤器
    for (i <- 1 to 30 if i % 2 != 0)
      print(i + " ")

    println()
    // 多个过滤器
    for (i <- 1 to 30 if i % 2 != 0;if i % 5 != 0)
      print(i + " ")
  }
}
