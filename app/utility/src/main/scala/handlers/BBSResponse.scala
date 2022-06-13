package handlers

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import play.api.mvc.Results._

/**
 * Define response for BBS system
 */
object BBSResponse {
   
   def ok(message: String): Result = {
      val response = Json.obj(("message", message))
      Ok(Json.toJson(response))
   }
   
   def ok(js: JsValue): Result = Ok(Json.toJson(js))
   
   /**
    * Used for <strong>non-user-creation</strong> requests
    *
    * @param id Entity ID (except User)
    * @return response
    */
   def created(id: Long): Result = {
      val response = Json.obj(("id", id))
      Created(Json.toJson(response))
   }
   
   def badRequest: Result = BadRequest
   
   def badRequest(message: String): Result = {
      val response = Json.obj(("message", message))
      BadRequest(Json.toJson(response))
   }
   
   def badRequest(cause: BBSException): Result = {
      val response = Json.obj(
         ("code", cause.getCode),
         ("message", cause.getMessage)
      )
      BadRequest(response)
   }
   
   def internalServerError: Result = InternalServerError
   
   def internalServerError(cause: BBSException): Result = InternalServerError(cause.getMessage)
}
