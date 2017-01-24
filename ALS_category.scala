case class myProb(author: String, category: String, rating: Double)

import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD

val rawData = sc.textFile("author_category.txt")

val data: RDD[myProb] = rawData.map(line => line.split('|').take(3)).map(array => myProb(array(0), array(1), array(2).toDouble))

val authorToInt: RDD[(String, Long)] = data.map(_.author).distinct().zipWithUniqueId()
val reverseMapping: RDD[(Long, String)] = authorToInt.map { case (l, r) => (r, l) }

val categoryToInt: RDD[(String, Long)] = data.map(_.category).distinct().zipWithUniqueId()
val reverseMappingcategory: RDD[(Long, String)] = categoryToInt.map { case (l, r) => (r, l) }

val dataWithUniqueauthorId: RDD[(String, Long, String, Double)] = data.keyBy(_.author).join(authorToInt).map(r => (r._2._1.author,r._2._2,r._2._1.category,r._2._1.rating))

val rawProb: RDD[(Long, Long, Double)] = dataWithUniqueauthorId.keyBy(_._3).join(categoryToInt).map(r => (r._2._1._2,r._2._2, r._2._1._4))
val ratings = rawProb.map (r => Rating(r._1.toInt, r._2.toInt, r._3))

val model = ALS.train(ratings, 50, 10, 0.01)

val pauthor = authorToInt.lookup("Juha Honkala").mkString.toInt
val pcategory = categoryToInt.lookup("Performance").mkString.toInt

val predictedRating = model.predict(pauthor, pcategory)

val authorId = pauthor
val K = 5
val topKRecs = model.recommendProducts(authorId, K)
println(topKRecs.mkString("\n"))




// COMMAND ----------


