import Dependencies._

name := """TruongCongToan_BE"""
version := "0.8.3"

lazy val commonSettings = Seq(
   scalaVersion := "2.13.8",
   libraryDependencies ++= defaultDependencies,
   libraryDependencies ++= Seq(
      gguice,
      scalatest,
      liftweb,
     makeCsv,
//     iteratees
   ),
   resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
   resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/",
   Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala",
   Test / scalaSource := baseDirectory.value / "src" / "test" / "scala",
   Compile / resourceDirectory := baseDirectory.value / "src" / "main" / "resources",
   Test / resourceDirectory := baseDirectory.value / "src" / "test" / "resources"
)


resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"


lazy val root = (project in file("."))
   .enablePlugins(PlayScala, SbtWeb,JavaAppPackaging)
   .disablePlugins(PlayLayoutPlugin)
   .aggregate(application, domain, port, utility)
   .dependsOn(application, domain, port, utility)
   .settings(
      commonSettings,
      Global / onChangedBuildSource := ReloadOnSourceChanges,
      PlayKeys.fileWatchService := play.dev.filewatch.FileWatchService.jdk7(play.sbt.run.toLoggerProxy(sLog.value)),
      PlayKeys.playDefaultPort := 9000
   )

lazy val application = (project in file("app/application"))
   .enablePlugins(PlayScala, SbtWeb)
   .disablePlugins(PlayLayoutPlugin)
   .dependsOn(utility, domain)
   .settings(
      commonSettings,
      libraryDependencies ++= Seq(
         mockito
      )
   )


lazy val domain = (project in file("app/domain"))
   .enablePlugins(PlayScala)
   .disablePlugins(PlayLayoutPlugin)
   .dependsOn(utility)
   .settings(commonSettings)

lazy val utility = (project in file("app/utility"))
   .settings(commonSettings)
   .enablePlugins(PlayScala)
   //   .settings(libraryDependencies += bcryptDependency )
   .settings(libraryDependencies += jwtDependency)

lazy val port = (project in file("app/port"))
   .aggregate(
      portWebService,
      mainDatabase,
   )
   .dependsOn(
      portWebService,
      mainDatabase,
   )
   .settings(commonSettings)

lazy val portWebService = (project in file("app/port/primary/webService"))
   .enablePlugins(PlayScala, SbtWeb)
   .disablePlugins(PlayLayoutPlugin)
   .dependsOn(utility, application)
   .settings(
      commonSettings,
      libraryDependencies ++= Seq(
         mockito
         //         "org.scalatest" % "scalatest_native0.4_2.13" % "3.2.10" % Test
      )

   )

lazy val mainDatabase = (project in file("app/port/secondary/databaseMySQL"))
   .dependsOn(utility, application)
   .settings(commonSettings)
   .settings(
      libraryDependencies ++= addedDependencies,

      libraryDependencies ++= Seq(
         mockito
      )
   )

// lazy val cacheDatabase = (project in file("app/port/secondary/databaseRedis"))
//    .dependsOn(utility, application)
//    .settings(commonSettings)
//    .settings(
//       // enable Play cache API
//       libraryDependencies += play.sbt.PlayImport.cacheApi,
//       // include play-redis library
//       libraryDependencies += redis
//    )

//lazy val fileDatabase = (project in file("app/port/secondary/localStorage"))
//   .dependsOn(utility, application)
//   .settings(commonSettings)
//   .settings(libraryDependencies ++= addedDependencies)

// TEST
//lazy val example  = InputKey[Unit]("thanh_da", "Run something.")
//fullRunInputTask( example, Compile, "LogTesting.TestTTT")

// build jar file using command line: sbt assembly
assembly / mainClass := Some("play.core.server.ProdServerStart")
assembly / fullClasspath += Attributed.blank(PlayKeys.playPackageAssets.value)

assembly / assemblyMergeStrategy := {
   case manifest if manifest.contains("MANIFEST.MF") =>
      // We don't need manifest files since sbt-assembly will create
      // one with the given settings
      MergeStrategy.discard
   case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
      // Keep the content for all reference-overrides.conf files
      MergeStrategy.concat
   case x =>
      // For all the other files, use the default sbt-assembly merge strategy
      val oldStrategy = (assembly / assemblyMergeStrategy).value
      oldStrategy(x)
}