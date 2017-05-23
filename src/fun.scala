/**
 * 行动类，异步。
 */
object fun {
  def main(args: Array[String]): Unit = {
    println(getLength("abc"))
  }

  def getLength (x : String) : Int = {
    return x.length
  }
}
