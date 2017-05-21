
/**
  * Created by mac on 17/5/20.
  */

object Filter5{
  // TODO: for ...yeild 方法, yeild把for循环的中间结果存储起来了。


  def main(args: Array[String]): Unit = {
    val elements = Array("Hello.scala","World.scala","How","Are","you")
    // 这个if又点想python的连写。
    // 如果条件很复杂，可以:
    /*
      for {
        file <- filesHere
        if file.getName.endsWith(".scala")
        line <- fileLines(file)
        if line.trim.matches(pattern)
      } println(file + ": " + line.trim)
    */
    for(e <- elements if !e.endsWith(".scala"))
      println(e)

    // collection的filter函数。
    for(e <- elements.filter((x : String) => x.endsWith(".scala")))
      println(e)

    for(e <- elements.filter(_.endsWith(".scala")))
      println(e)

  }
}