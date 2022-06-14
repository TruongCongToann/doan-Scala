package bbs.controllers.user

import bbs.user.models.{User, UserID}
import org.joda.time.DateTime
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Json, Reads, Writes}
import models.ImplicitsUsed.jodaDateRead
/**
 * Provides implicit methods to serialize and deserialize [[User]]
 */
object UserConverter {
   // Read: JsValue => A
   // Write: A => JsValue

   implicit val userRead: Reads[User] =
      (
         (JsPath \ "user_id").readNullable[UserID] and
           (JsPath \ "username").readNullable[String] and
           (JsPath \ "address").readNullable[String] and
           (JsPath \ "phone_number").readNullable[String] and
           (JsPath \ "gender").readNullable[String] and
            (JsPath \ "email").readNullable[String] and
            (JsPath \ "password").readNullable[String] and
           (JsPath \ "role").readNullable[String] and
           (JsPath \ "image").readNullable[String] and
           (JsPath \ "position").readNullable[String] and
           (JsPath \ "created_at").readNullable[DateTime] and
           (JsPath \ "updated_at").readNullable[DateTime] and
           (JsPath \ "doctorid").readNullable[Int] and
           (JsPath \ "full_name").readNullable[String]
        ) (User.apply _)

   implicit val userWrite: Writes[User] = (user: User) =>
      Json.obj(
         ("user_id",user.user_id.get.value),
         ("username",user.username),
         ("address",user.address),
         ("phone_number",user.phone_number),
         ("gender",user.gender),
         ("email", user.email),
         ("password", "************"),
         ("role",user.role),
         ("image",user.image),
         ("position",user.position),
         ("created_at", user.created_at.get.toString),
         ("updated_at", user.updated_at.get.toString),
         ("doctorid", user.doctorid),
         ("full_name", user.full_name),
      )


}
