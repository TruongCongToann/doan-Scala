import sbt._
import play.sbt.PlayImport._

object Dependencies {
   lazy val defaultDependencies = Seq(jdbc, ws, specs2 % Test, guice,ehcache, evolutions)

   lazy val addedDependencies = Seq(
      "mysql" % "mysql-connector-java" % "8.0.29",
      "com.h2database" % "h2" % "2.1.210",
      "org.skinny-framework" %% "skinny-orm" % "3.1.0",
      "org.skinny-framework" %% "skinny-task" % "3.1.0",
      "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",
      "joda-time" % "joda-time" % "2.10.13",
      "com.typesafe.play" %% "play-json-joda" % "2.9.2"
   )

   lazy val mockito = "org.mockito" % "mockito-core" % "4.2.0" % Test

   lazy val bcryptDependency = "org.mindrot" % "jbcrypt" % "0.4"

   lazy val jwtDependency = "com.github.jwt-scala" %% "jwt-core" % "9.0.3"

   lazy val gguice = "com.google.inject" % "guice" % "5.0.1"

   lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.8" % Test

   lazy val liftweb = "net.liftweb" % "lift-json" % "2.0"
   val makeCsv = "au.com.bytecode" % "opencsv" % "2.4"
   val iteratees = "com.typesafe.play" %% "play-iteratees" % "2.6.1"


   // lazy val redis = "com.github.karelcemus" %% "play-redis" % "2.7.0"
}