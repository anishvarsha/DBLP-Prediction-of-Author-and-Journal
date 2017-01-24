// Databricks notebook source exported at Wed, 20 Apr 2016 06:24:09 UTC
// Databricks notebook source exported at Wed, 20 Apr 2016 06:20:40 UTC
case class myProb(author: String, journal: String, rating: Double)

import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD

val rawData = sc.textFile("author_journals.txt")

val data: RDD[myProb] = rawData.map(line => line.split('|').take(3)).map(array => myProb(array(0), array(1), array(2).toDouble))

val authorToInt: RDD[(String, Long)] = data.map(_.author).distinct().zipWithUniqueId()
val reverseMapping: RDD[(Long, String)] = authorToInt.map { case (l, r) => (r, l) }

val journalToInt: RDD[(String, Long)] = data.map(_.journal).distinct().zipWithUniqueId()
val reverseMappingjournal: RDD[(Long, String)] = journalToInt.map { case (l, r) => (r, l) }

val dataWithUniqueauthorId: RDD[(String, Long, String, Double)] = data.keyBy(_.author).join(authorToInt).map(r => (r._2._1.author,r._2._2,r._2._1.journal,r._2._1.rating))

val rawProb: RDD[(Long, Long, Double)] = dataWithUniqueauthorId.keyBy(_._3).join(journalToInt).map(r => (r._2._1._2,r._2._2, r._2._1._4))
val ratings = rawProb.map (r => Rating(r._1.toInt, r._2.toInt, r._3))

val model = ALS.train(ratings, 50, 10, 0.01)

val pauthor = authorToInt.lookup("Juha Honkala").mkString.toInt
val pjournal = journalToInt.lookup("ystems Journal").mkString.toInt

val predictedRating = model.predict(pauthor,pjournal)

val authorId = pauthor
val K = 5
val topKRecs = model.recommendProducts(authorId, K)
println(topKRecs.mkString("\n"))




// COMMAND ----------


