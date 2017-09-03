import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
  * 13  * 统计字符出现次数
  * 14  */
object WordCount2 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val line = sc.textFile("/Users/mac/Desktop/data.txt")

    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect().foreach(println)

    sc.stop()
  }

}