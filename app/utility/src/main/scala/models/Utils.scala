package models

/**
 * Helper object that handles duplicated small-scope tasks, like strings validation,
 * string extraction, etc...
 */
object Utils {
   
   def isEmptyOrNull(text: String): Boolean = {
      text == null
   }
   
   /**
    * Extract JWT token from HTTP Authorization Header's value.<br>
    * An authorization used in this BBS Management has the form:
    * <strong>{{{"Bearer _json_web_token_"}}}</strong>
    * @param authHeader the authorization's value in HTTP header
    * @return a json web token
    */
   def extractJWT(authHeader: String): String = authHeader.split("\\s")(1)
}
