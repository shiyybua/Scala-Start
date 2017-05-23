import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

/**
  * Created by cai on 5/23/17.
  */

object ReadCSV{
  def main(args: Array[String]): Unit = {
    val sparkSQL = SparkSession.builder().master("local").appName("Read CSV").getOrCreate()
    val df = sparkSQL.read
      .format("com.databricks.spark.csv")
      .option("header", "true") //reading the headers
      .option("mode", "DROPMALFORMED")
      .load("/home/cai/Downloads/data_dic_2/bankChurn.csv");

//    打印csv里面的内容。Only show the top 20 row;
//    df.show()

//    打印出头部信息
//    println(df.printSchema())

//    打印某特定列的信息，show只显示前20行
    df.select("OPEN_ACC_DUR").show()

    /*其他一起方法。
    // Select everybody, but increment the age by 1
        df.select($"name", $"age" + 1).show()
    // Select people older than 21
        df.filter($"age" > 21).show()
    // Count people by age
        df.groupBy("age").count().show()
     */

    df.createGlobalTempView("finance")
    val data = sparkSQL.sql("select OPEN_ACC_DUR from finance")
    data.show()

  }
}