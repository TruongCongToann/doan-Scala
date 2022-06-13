package security

import play.api.libs.json.{Format, JsPath, JsString, JsSuccess}


sealed abstract class Role(val value: String)

/**
 * Provides roles that an user of BBS system might be. Available legal roles
 * are <strong>ADMIN</strong>, <strong>USER</strong> and <strong>GUEST</strong>,
 */
object Role {

   def apply(value: String): Option[Role] = {
      value match {
         case "ADMIN" => Some(Role.ADMIN)
         case "USER" => Some(Role.USER)
         case "DOCTOR" => Some(Role.DOCTOR)
         case _ => Some(Role.GUEST)
      }
   }

   def unapply(role: Role): String = role.value

   case object ADMIN extends Role("ADMIN")

   case object USER extends Role("USER")

   case object DOCTOR extends Role("DOCTOR")

   case object GUEST extends Role("GUEST")

   implicit val roleFormat: Format[Role] = Format[Role](
      value => {
         JsSuccess[Role](Role(value.as[String]).get, JsPath \ "role")
      },
      role => JsString(role.value)
   )

}
