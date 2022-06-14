package models

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{Format, JsNull, JsResult, JsString, JsValue, Reads, Writes}

/**
 * Defines all common implicits which is used within project
 */
object ImplicitsUsed {

   // define json converter for joda.DateTime

   implicit val jodaDateRead: Reads[DateTime] = Reads[DateTime](
      jsValue => jsValue.validate[String].map[DateTime](
         dataString => DateTime.parse(dataString, DateTimeFormat.forPattern("yyyy-MM-dd"))
      )
   )

   implicit val jodaDateWrite: Writes[DateTime] = (o: DateTime) => {
      JsString(o.toString())
   }

   implicit def generalFormat[T: Format]: Format[Option[T]] = new Format[Option[T]]{
      override def reads(json: JsValue): JsResult[Option[T]] = json.validateOpt[T]

      override def writes(o: Option[T]): JsValue = o match {
         case Some(t) => implicitly[Writes[T]].writes(t)
         case None => JsNull
      }
   }
}
