import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.Map

/**
  * Created by mac on 17/9/2.
  */
case class MovieRating(userID: String, movieID: Int, rating: Double) extends scala.Serializable
object Recommend{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("DoubanRecommender")
    val sc = new SparkContext(conf)
    val base = "/Users/mac/IdeaProjects/To/src/resource/movie/"

    //获取RDD
    val rawUserMoviesData = sc.textFile(base + "user_movies.csv")
    val rawHotMoviesData = sc.textFile(base + "hot_movies.csv")
    preparation(rawUserMoviesData, rawHotMoviesData)

    recommend(sc, rawUserMoviesData, rawHotMoviesData,base)

  }

  //分析清理数据
  def preparation( rawUserMoviesData: RDD[String],
                   rawHotMoviesData: RDD[String]) = {
    val userIDStats = rawUserMoviesData.map(_.split(',')(0).trim).distinct().zipWithUniqueId().map(_._2.toDouble).stats()
    val itemIDStats = rawUserMoviesData.map(_.split(',')(1).trim.toDouble).distinct().stats()

    val test = rawUserMoviesData.map(_.split(',')(0).trim).distinct().zipWithUniqueId()

    for (x <- test.collect()){
      println(x)
    }


    println(userIDStats)
    println(itemIDStats)

    val moviesAndName = buildMovies(rawHotMoviesData)

    val (movieID, movieName) = moviesAndName.head
    println(movieID + " -> " + movieName)
  }

  //得到电影名字的RDD
  def buildMovies(rawHotMoviesData: RDD[String]): Map[Int, String] =
    rawHotMoviesData.flatMap { line =>
      val tokens = line.split(',')
      if (tokens(0).isEmpty) {
        None
      } else {
        Some((tokens(0).toInt, tokens(2)))
      }
    }.collectAsMap()

  //
  def buildRatings(rawUserMoviesData: RDD[String]): RDD[MovieRating] = {
    rawUserMoviesData.map { line =>
      val Array(userID, moviesID, countStr) = line.split(',').map(_.trim)
      var count = countStr.toInt
      count = if (count == -1) 3 else count
      MovieRating(userID, moviesID.toInt, count)
    }
  }

  def recommend(sc: SparkContext,
                rawUserMoviesData: RDD[String],
                rawHotMoviesData: RDD[String],
                base:String): Unit = {
    val moviesAndName = buildMovies(rawHotMoviesData)
    val bMoviesAndName = sc.broadcast(moviesAndName)

    val data = buildRatings(rawUserMoviesData)

    val userIdToInt: RDD[(String, Long)] =
      data.map(_.userID).distinct().zipWithUniqueId()
    val reverseUserIDMapping: RDD[(Long, String)] =
      userIdToInt map { case (l, r) => (r, l) }

    val userIDMap: Map[String, Int] =
      userIdToInt.collectAsMap().map { case (n, l) => (n, l.toInt) }

    val bUserIDMap = sc.broadcast(userIDMap)
    val bReverseUserIDMap = sc.broadcast(reverseUserIDMapping.collectAsMap())

    val ratings: RDD[Rating] = data.map { r =>
      Rating(bUserIDMap.value.get(r.userID).get, r.movieID, r.rating)
    }.cache()
    //使用协同过滤算法建模
    //val model = ALS.trainImplicit(ratings, 10, 10, 0.01, 1.0)
    val model = ALS.train(ratings, 50, 10, 0.0001)
    ratings.unpersist()

    model.save(sc, base+"model")
    //val sameModel = MatrixFactorizationModel.load(sc, base + "model")

    val allRecommendations = model.recommendProductsForUsers(5) map {
      case (userID, recommendations) => {
        var recommendationStr = ""
        for (r <- recommendations) {
          recommendationStr += r.product + ":" + bMoviesAndName.value.getOrElse(r.product, "") + ","
        }
        if (recommendationStr.endsWith(","))
          recommendationStr = recommendationStr.substring(0,recommendationStr.length-1)

        (bReverseUserIDMap.value.get(userID).get,recommendationStr)
      }
    }

    //allRecommendations.saveAsTextFile(base + "result.csv")
    allRecommendations.coalesce(1).sortByKey().saveAsTextFile(base + "result.csv")

    unpersist(model)
  }

  def unpersist(model: MatrixFactorizationModel): Unit = {
    // At the moment, it's necessary to manually unpersist the RDDs inside the model
    // when done with it in order to make sure they are promptly uncached
    model.userFeatures.unpersist()
    model.productFeatures.unpersist()
  }
}