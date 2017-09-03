import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mac on 17/6/29.
  */
object DataProcess{
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Logistic Regression")
    val sc = new SparkContext(conf)

    // Load and parse the data
    val data = sc.textFile("/Users/mac/Desktop/finance_analysis/2/bankChurn.csv")
    val parsedData = data.map { line => line.split(',')}

    // 两重循环遍历打印数据
    parsedData.foreach(element => element.foreach(println))

  }

}
