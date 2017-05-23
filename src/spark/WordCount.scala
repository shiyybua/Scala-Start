import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
  * 13  * 统计字符出现次数
  * 14  */
object WordCount2 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val line = sc.textFile("/home/cai/software/spark-2.1.0-bin-hadoop2.7/NOTICE")

    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect().foreach(println)

    sc.stop()
  }

}