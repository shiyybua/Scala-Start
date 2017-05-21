/**
  * 行动类，异步。
  */
object Functions {
  def main(args: Array[String]): Unit = {
    println(getLength("abc"))

    // 简单的函数定义。
    var increase = (x: Int) => x + 1
    println(increase(10))

    increase = (x: Int) => {
      println("We")
      println("are")
      println("here!")
      x + 1
    }
    increase(20)

  }

  def getLength (x : String) : Int = {
    return x.length
  }
}
