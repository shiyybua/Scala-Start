/**
  * Created by mac on 17/8/27.
  */

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import scala.collection.mutable.ListBuffer

object gbdt {

  def parsePoint(line: String): LabeledPoint = {
    var values = line.split(',')
    values(93) = values(93).last.toString()
    var values2 = values.map(_.toDouble)
    if (values2(93) > 1)
      values2(93) = 1
    else
      values2(93) = 0
    LabeledPoint(values2.last, Vectors.dense(values2.init))
  }

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("logistic regression")
    val sc = new SparkContext(conf)

    // Load training data in csv format.
    val csv_file = sc.textFile("/home/yejiming/desktop/spark/scala/dataset.csv")
    val data = csv_file.map(parsePoint)

    // Split data into training (80%) and test (20%).
    val splits = data.randomSplit(Array(0.8, 0.2), seed = 123)
    val (trainingData, testData) = (splits(0), splits(1))

    // Setting up the hyper-parameters
    val boostingStrategy = BoostingStrategy.defaultParams("Classification")
    boostingStrategy.setNumIterations(50) // Note: Use more iterations in practice.
    boostingStrategy.treeStrategy.setNumClasses(2)
    boostingStrategy.treeStrategy.setMaxDepth(5)
    boostingStrategy.treeStrategy.setCategoricalFeaturesInfo(Map[Int, Int]())

    // Run training algorithm to build the model
    val model = GradientBoostedTrees.train(trainingData, boostingStrategy)

    // Compute raw scores on the test set.
    val predictionAndLabels = testData.map {
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label)
    }

    // Get evaluation metrics.
    val metrics = new MulticlassMetrics(predictionAndLabels)
    val precision = metrics.precision
    println("Precision = " + precision)
  }
}