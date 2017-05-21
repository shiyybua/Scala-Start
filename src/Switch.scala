/**
  * Created by mac on 17/5/21.
  */

object Switch{
  def main(args: Array[String]): Unit = {
    val s = "apple"
    //match和java的switch类似。
    s match{
      case "apple" => println("苹果")
      case "banana" => println("香蕉")
      case _ => println("什么都不是")
    }

  }
}