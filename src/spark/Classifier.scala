import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mac on 17/5/22.
  */

object Classifier{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Law data classification")
    val sc = new SparkContext(conf)
    val content = sc.textFile("/Users/mac/Desktop/data.txt")

    // _表示循环遍历了前面的值， 后面的map里也能这么写。
    // TODO: groupbykey & count by key
    content.flatMap(_.split("\n")).map(line => (line.split("\t").apply(0), line)).
      collect().foreach(println)

    sc.stop()
  }
}