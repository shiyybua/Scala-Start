/**
  * Created by mac on 17/1/5.
  */
object ArrayStart {
  def main(args: Array[String]): Unit = {
    ArrayTest
    upDateArray
    ListTest
  }

  // 这里的Array 必须指定长度。
  def ArrayTest : Unit = {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world\n"
    // 0 to 2 返回的是一个数组？
    for (i <- 0 to 2)
      print(greetStrings(i))
  }

  def upDateArray : Unit = {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world\n"
    // 在编译器中，赋值操作会转化成update方法
    greetStrings.update(0, "Hello")
    // 0 to 2 返回的是一个数组？
    for (i <- 0 to 2)
      // 把取值操作转化成apply方法。
      print(greetStrings.apply(i))
  }

  // 一个更为简洁的建立数组的方法。
  def easyArray : Unit = {
    // 这里调用的还是 apply方法。
    val numNames = Array("zero","one","two")
  }

  //Scala中List是不可变的
  def ListTest : Unit = {
    // List 里面的方法很丰富，will come back latter.
    val OntTwoThree = List(1,2,3)
    val FourFive = List(4,5)
    // :::实现的是方法的叠加功能。

    val ontTwoThreeFourFive = OntTwoThree ::: FourFive
    println(ontTwoThreeFourFive)
    // :: 是指吧新的元素加到List的最前端，所以说反过来表示就错了 如：ontTwoThreeFourFive :: 7
    println(7 :: ontTwoThreeFourFive)
  }

  /*
    类 List 没有提供 append 操作,因为随着列表变长 append 的耗时将呈线性增长,而 使用::做前缀则仅花费常量时间。
    如果你想通过添加元素来构造列表,你的选择是把它们 前缀进去,当你完成之后再调用 reverse;或使用 ListBuffer,
    一种提供 append 操作的 可变列表,当你完成之后调用 toList。
   */
  import scala.collection.mutable.ListBuffer
  def ListBufferDemo : Unit = {
    var LBuf = new ListBuffer()
  }


}
