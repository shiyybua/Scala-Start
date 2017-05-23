/**
 * Created by dell on 2017/5/19.
 */
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount {
  def main(args: Array[String]) {
//    val conf = new SparkConf().setMaster("spark://cdh-master-slave1:7077").setAppName("App")
//    conf.set("spark.executor.memory","1G")
//    conf.set("spark.driver.memory", "1G")
//    conf.set("spark.executor.cores", "1")
//    conf.set("spark.cores.max","1")
    val conf = new SparkConf().setMaster("local").setAppName("App")
    val sc = new SparkContext(conf)
    val line = sc.textFile("E:\\big_data_install\\spark-1.6.0-bin-hadoop2.6\\CHANGES.txt")

    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).collect().foreach(println)
    sc.stop()
  }
}