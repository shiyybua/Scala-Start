/**
  * Created by mac on 17/5/20.
  */

//Class 不能直接Run。
class Rational(n: Int, d: Int) {
  //和python中的assert很像
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  def this(n: Int) = this(n, 1)

  // 加法重载
  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  // 乘法重载
  def *(that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  override def toString = numer+"/"+denom

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  // 这个方法没有写在函数里面，没报错，可以执行。
  // print("hello")
}

object Rational{

  def main(args: Array[String]): Unit = {
    val x = new Rational(1, 2)
    val y = new Rational(2, 3)
    println(x + y)
  }
}
