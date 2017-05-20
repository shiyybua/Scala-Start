/**
  * 单例对象的概念：在JAVA中有static关键字，而Scala里面没有静态成员，代替品是单例对象: object.
  * 当单例对象与 某个类共享同一个名称时,他被称作是这个类的伴生对象.
  * 类和它的伴生对象可以互相访问其私有成员。
  *
  * Scala中如果什么都不写，则默认是public.
  */
object ClassDemo {
  def main(args: Array[String]): Unit = {
    // 看这里，虽然ScalaClass被初始化了2次，但是在他们的成员变量值没有被改变之前，指向的是同一个变量
    // 但是如果有改变了，则就新增一个变量。
    val acc = new ScalaClass
    val csa = new ScalaClass

    accessValue

    val x = new ClassDemo(1)
    x.set(1)
    x.set("aaa")

    // acc = new ScalaClass 这个语句是错误的。因为acc是个val，不可变的。
  }

  // 伴生对象可以直接访问私有变量。
  def accessValue : Unit = {
    val c = new ClassDemo(88)
    println(c.accessable)
    println(c)
  }
}

// 重写了toString方法。
class ClassDemo (n: Int) {
  private val accessable = 10
  private val change = n
  override def toString = "I am ClassDemo"
  // this 这个是Scala的构造函数。
  //  def this(x : Int) = this(10)

  // 方法重载
  def set(x : Int) = {
    println("Int " + x)
  }
  def set(x : String) = {
    println("String " + x)
  }
  def set(x : Any, y : Any) = {
    println(x + " " + y)
  }
}

