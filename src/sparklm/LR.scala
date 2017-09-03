/**
  * Created by cai on 5/24/17.
  */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors



object LR{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Logistic Regression")
    val sc = new SparkContext(conf)

    // Load and parse the data
    var data = sc.textFile("/Users/mac/Desktop/finance_analysis/2/bankChurn.csv")
    // 删除第一行。
//    data = data.mapPartitionsWithIndex { (idx, iter) => if (idx == 0) iter.drop(1) else iter }

    // 删除行的第二个方法。
    val header = data.first()
    data = data.filter(row => row != header)
    val parsedData = data.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }
    // Building the model
    val numIterations = 100
    val model = LinearRegressionWithSGD.train(parsedData, numIterations)

    // Evaluate model on training examples and compute training error
    val valuesAndPreds = parsedData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val MSE = valuesAndPreds.map{case(v, p) => math.pow((v - p), 2)}.mean()
    println("training Mean Squared Error = " + MSE)

  }
}
