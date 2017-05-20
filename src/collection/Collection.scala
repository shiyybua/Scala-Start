package collection

/**
  * Tuple Set... 查看返回值类型。
  */
object Collection {
  def main(args: Array[String]): Unit = {
    tupleTest
    setTest
    mapTest
  }

  def tupleTest : Unit = {
    // Tuple 中，可以包涵不同类型的值。
    val pair = (99,"hello world",10.1)
    println(pair._1)
    println(pair._2)
    println(pair._3)
    // 遍历 tuple 1
    pair.productIterator.foreach{ i =>println("Value = " + i )}

    // 遍历 tuple 2
    for (x <- pair.productIterator) {
      println(x)
    }
    // 可以观察出 pair.productIterator 返回的是scala.collection.Iterator[Any]
    // pair 不能直接遍历，原因也是里面包涵值的类型不一样
    println(manOf(pair.productIterator))
  }

  // 查看返回值类型。
  def manOf[T: Manifest](t: T): Manifest[T] = manifest[T]


  // 例子很简单，但是值得注意的是Set它继承（trait）的是scala.collection.immutable.也就是说它是不可变的。
  // 虽然2，3中 + 都用的是加号的形式来做append，但是内部实现原理不同。 如果是mutable的情况，添加的数据是往
  // 原来的变量上加的。而对于这里的HashSet来说，我们用的是重新申请一个原理。这个和java中的String的追加很相似。
  def setTest : Unit = {
      // 1
      println("\nIt is setTest function:")
      var jetSet = Set("Boeing","Airbus")
      jetSet += "Lear"
      println(jetSet.contains("Cessna"))

      // 2
      import scala.collection.mutable.Set
      val movieSet = Set("Hitch", "Poltergeist")
      movieSet += "Shrek"
      println(movieSet)

      // 3
      import scala.collection.immutable.HashSet
      val hashSet = HashSet("Tomatoes", "Chilies")
      println(hashSet + "Coriander")

  }

  // 同样Map也分mutable和immutable. 道理和上面的一样。
  def mapTest : Unit = {
    println("\nIt is mapTest function:")
    import scala.collection.mutable.Map
    val treasureMap = Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))


  }


}
