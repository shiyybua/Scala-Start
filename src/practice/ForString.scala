package practice

/**
  * Created by mac on 17/5/14.
  */
object ForString {
  def getInput(str : String): Unit ={
    println(str)
    for(s <- str){
      // 返回的s是byte类型，所以要转成string，才能 + " "
      print(s.toString + " ")
    }
    println()
    // 用until 代替 to 就省了 length-1的操作。
    for(i <- 0 until(str.length)){
      print(str.charAt(i) + " ")
    }
    println()
  }

  def main(args: Array[String]): Unit = {
    getInput("howareu")
    // 直接在后面跟了方法，没有加. 这个就是和操作符的方式一样。Scala里很多的方法都可以这么用
    print("asd" indexOf("a"))
  }
}
