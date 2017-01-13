/**
  * Created by mac on 17/1/13.
  */
object ClassDemo {
  def main(args: Array[String]): Unit = {
    // 看这里，虽然ScalaClass被初始化了2次，但是在他们的成员变量值没有被改变之前，指向的是同一个变量
    // 但是如果有改变了，则就新增一个变量。
    val acc = new ScalaClass
    val csa = new ScalaClass

    // acc = new ScalaClass 这个语句是错误的。因为acc是个val，不可变的。
  }
}

