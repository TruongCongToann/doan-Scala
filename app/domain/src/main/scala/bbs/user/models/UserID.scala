package bbs.user.models

import models.Identifier
import play.api.libs.json.Reads

import scala.language.implicitConversions

/**
 * Identifier of [[User]]
 *
 * @param value actual id number in database
 */
case class UserID(value: Long) extends Identifier[Long] {

   implicit def convertToPostID(value: Long): UserID = UserID(value)

   implicit def convertToIdValue(id: UserID): Long = id.value
}

object UserID {

   implicit val readUserID: Reads[UserID] = Reads[UserID] {
      jsValue => jsValue.validate[Long].map(value => UserID(value))
   }

//   implicit val format: Format[UserID] = Json.format[UserID]

//   implicit val customWrites: OWrites[UserID] = Json.writes[UserID]
}
