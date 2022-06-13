package LogTesting

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import play.api.libs.json.Json
import security.Payload

object TestTTT {
   def main(args: Array[String]): Unit = {
      val jsString = """{
         "exp": 1643610799,
         "nbf": 1643610499,
         "email": "truongcongtoan.work@gmail.com",
         "role": "USER"
      }"""
      val datetime = "2022-01-29 23:25:14.23"
      val d = DateTime.parse(datetime, DateTimeFormat.forPattern("yyyy-MM-DD HH:mm:ss.SSS"))
      println(d.toString)
   }
}
