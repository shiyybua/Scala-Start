/**
  * Created by mac on 17/5/20.
  */

object Filter5{
  def main(args: Array[String]): Unit = {
    val elements = Array("Hello.scala","World.scala","How","Are","you")
    // 这个if又点想python的连写。
    for(e <- elements if !e.endsWith(".scala"))
      println(e)
  }
}