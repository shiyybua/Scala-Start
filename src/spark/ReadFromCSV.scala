import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}


/**
  * Created by cai on 5/23/17.
  */

class ReadCSV(){

  val sparkSQL = SparkSession.builder().master("local").appName("Read CSV").getOrCreate()
  // Read出来的数据被转换成了DataFrame.
  val df = sparkSQL.read
    .format("com.databricks.spark.csv")
    .option("header", "true") //reading the headers
    .option("mode", "DROPMALFORMED")
    .load("hdfs://localhost:8020/usr/kaggle_data/bankChurn.csv"); // bingo,查看core-site
//    .load("/Users/mac/Desktop/finance_analysis/2/bankChurn.csv");


  // 这个只能作用于当前session，要创建全局必须使用createGlobalTempView，查表的时候用global_temp.TABLENAME.
  df.createOrReplaceTempView("finance")

  // 这个是在内部导入的。
  import sparkSQL.implicits._

  //    打印csv里面的内容。Only show the top 20 row;
  def justShow(): Unit ={

    df.show()
  }

  //    打印出头部信息
  def showSchema(): Unit ={
    df.printSchema()
  }

  //    打印某特定列的信息，show只显示前20行
  def showOneColumn(): Unit ={
    df.select("OPEN_ACC_DUR").show()
  }

  def selectFromDataFrame(): Unit ={
    val data = sparkSQL.sql("select OPEN_ACC_DUR from finance")
    data.show()
    /*
        // Select everybody, but increment the age by 1
            df.select($"name", $"age" + 1).show()
        // Select people older than 21
            df.filter($"age" > 21).show()
        // Count people by age
            df.groupBy("age").count().show()
         */
  }

  def selectCount(): Unit ={
    // 除了count等自带的函数之外还可以自定义，具体方法是继承UserDefinedAggregateFunction
    // 详见官网：http://spark.apache.org/docs/latest/sql-programming-guide.html#datasets-and-dataframes
    val count = sparkSQL.sql("select count(*) from finance")
    count.show()
  }

  def getColumnsBy(): Unit ={
    val data = sparkSQL.sql("select AGE, GENDER_CD, LOCAL_CUR_SAV_SLOPE from finance")
    // 从多个从column中拿值。
    for(x <- data.select("AGE").collect()){
      println(x)
    }
  }
}

object ReadCSV{
  def main(args: Array[String]): Unit = {
    val rc = new ReadCSV()
//    rc.justShow()
//    rc.selectFromDataFrame()
    rc.showSchema()
    rc.getColumnsBy()
  }
}
