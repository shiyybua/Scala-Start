/**
  * Created by mac on 17/1/13.
  */
object BasicOperations {
  def main(args: Array[String]): Unit = {

    // 观察结果可以看出加上 | 不是没有意义的。
    println(
      """
        |Hello,
        how are u.
      """.stripMargin)

    setPara('aa,"asd")
    add
    stringMethods
    booleanCmp
    wrap
  }

  // I will be back for the 'Symbol' latter!
  // Any 是传入/接受任何对象
  def setPara (r : Symbol, value : Any): Unit = {
    println(r + " " + value)
  }

  // Scala,一切皆对象。1 + 2 其他调用的是Int的+方法。
  // 可以看出不写Unit 也可以。
  def add = {
    val sum = (1).+(2)
    println(sum)
  }

  def stringMethods = {
    val str = "hello, world!"
    // 查找第一个o的位置
    println(str.indexOf("o"))
    // 从第5位开始查找
    println(str.indexOf("o",5))

    // 8 是Int，本身也是个对象。
    println(8.toString)
  }

  // 和JAVA不同的是 == 可以直接判断对象是否相等。
  // 因为Scala一切皆对象，在 判断 1 == 2时，比较的还是2个对象。
  // 所以下面返回的结果是true.
  /*
    Java 的==比较了参考相等性:reference equality,也就是说这两个变量是否都指向于 JVM 堆里的同一个对象。
    Scala用eq来做这个事情。
   */
  def booleanCmp = {
    val a = List(1,2,3)
    val b = List(1,2,3)
    println(a == b)
  }

  // 富包装器
  def wrap = {
    println(0 max 5)
    println(0 min 5)
    println(-2.7 abs)
    println(1.5 isInfinity)
    println((1.0 / 0) isInfinity)
    println(4 to 6)
    println("bob" capitalize)
    println("robert" drop 2)
  }
}
