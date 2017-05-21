/**
  * Created by mac on 17/5/21.
  */

object Paramters{
  // 不指定参数数量。
  def echo(args: String*): Unit ={
    for(x <- args)
      println(x)
  }

  def main(args: Array[String]): Unit = {
    echo("hello","word")
    val arr = List("how","are","u")
    // 相当于先拆分在传入。
    echo(arr:_*)
  }
}
