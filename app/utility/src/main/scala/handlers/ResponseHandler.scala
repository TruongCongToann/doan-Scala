package handlers

import handlers.error_list.ErrorList
import models.Utils
import org.slf4j.Logger
import play.api.libs.json.{JsObject, JsValue, Json}
import play.api.mvc.{AnyContent, Request, Result}
import security.{JWTUtility, Payload}

import scala.util.Try

/**
 * Catch and handle exception (if any), beside it writes a log of that
 * exception to console. If there ain't any exception, the result of
 * the method declared as the param of [[loggingAction]] will be returned.
 */
trait ResponseHandler {

   def logger: Logger

   def loggingAction(handler: => JsValue): Result = {
      Try {
         val rs = handler
         rs
      }.fold(error => {

         logger.error("Hey due, there is an error(s):", error)

         error match {
            case e: BBSException => BBSResponse.badRequest(e)
            case _ => BBSResponse.badRequest(error.toString)
         }

      }, value => BBSResponse.ok(value))
   }

   /**
    * Used as return value for POST method, only use for <br><strong>non-user-creation</strong> requests
    * @param id id of entity which just has been persisted
    * @return Json
    */
   def ok(id: Long): JsObject = {
      Json.obj(
         "id" -> id
      )
   }

   def okCreateUser(email: String): JsObject = {
      Json.obj(
         "email" -> email
      )
   }
   /**
    * Validate whether user request comes with valid token. If does, return claims extracted from the request header
    * @param request User request
    * @return JWT's claims
    */
   def authorityInterceptor(request: Request[AnyContent]): Payload = {

      val authHeader: Seq[String] = request.headers.headers.collect[String] {
         case ("Authorization", bearer) => bearer
      }

      if (!JWTUtility.validate(authHeader))
         throw new BBSException(ErrorList.UNAUTHORIZED)

      JWTUtility.decode(Utils.extractJWT(authHeader.head))
   }

}
