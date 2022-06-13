package security

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json._

/**
 * Annotate an object to be a payload and can be used
 * for claiming role in [[JWTUtility]]
 */
case class Payload(
                    userId: Long,
                    role: Role
                  ) {
   override def toString: String = s"""{"id":$userId, "role":"$role"}"""
}

object Payload {
   
   implicit val payloadWrite: Writes[Payload] =
      (p: Payload) => Json.obj(
         "id" -> p.userId,
         "role" -> p.role.value
      )
   
   implicit val payloadRead: Reads[Payload] = ((JsPath \ "id").read[Long] and
     (JsPath \ "role").read[Role]) (Payload.apply _)
   
   /**
    * A helper function, similar to [[payloadRead]] but can be called statically when needed
    *
    * @param jsValue the payload part in JWT token, serialized.<br>
    *                e.g.: {{{
    *    {
    *        "exp": 1643610799,
    *        "nbf": 1643610499,
    *        "email": "daoanhthanh.work@gmail.com",
    *        "role": "ADMIN"
    *     }
    * }}}
    * @return A [[Payload]] parsed from a json.<br>
    *         e.g. This is an object deserialized from the above example
    *         {{{Payload("daoanhthanh.work@gmail.com", Role("ADMIN").get)}}}
    */
   def parse(jsValue: JsValue): Payload = {
      
      val userId = (jsValue \ "id").get.as[Long]
      val role = (jsValue \ "role").get.as[String]

      Payload(userId, Role(role).get)
   }
}
