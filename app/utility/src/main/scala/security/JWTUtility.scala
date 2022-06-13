package security

import com.typesafe.config.ConfigFactory
import models.Utils
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim, JwtHeader}
import play.api.libs.json.Json

import java.time.Clock
import scala.util.{Failure, Success, Try}

/**
 * Handle encode, decode and validate a security token based on configuration
 * declared in `application.conf`
 */
object JWTUtility {
   
   private val encodeAlgorithm = Seq(JwtAlgorithm.HS256)
   
   private val secretKey: String = ConfigFactory.load().getString("bbs.security.secretKey")
   
   // Time to live - the amount of time in which a token is valid (in minutes)
   private val ttl: Long = ConfigFactory.load().getLong("bbs.security.ttl") * 60
   
   private implicit val anchorTime: Clock = Clock.systemUTC()
   
   /**
    * Create a token with passed in payload. A payload is required to
    * have implicit [[play.api.libs.json.Format]] in order to be serializable
    *
    * @param payload an object that extends [[Payload]]
    * @return security token
    */
   def generate(payload: security.Payload): String = {
      Jwt.encode(
         JwtHeader(
            algorithm = encodeAlgorithm.head,
            typ = "JWT"
         ),
         JwtClaim(
            content = payload.toString
         ).startsNow.expiresIn(ttl),
         secretKey
      )
   }
   
   /**
    * Decode a token to a payload object. The result ignores header and signature,
    * only keeps payload - which is a serialized object.
    *
    * @param token a security string want to decode
    * @return payload object
    */
   def decode(token: String): Payload = {

      val claim: Try[String] = Jwt.decodeRaw(token, secretKey, encodeAlgorithm)
      
      claim match {
         case Success(value) => Payload.parse(Json.parse(value))
         case Failure(exception) => throw exception
      }
   }
   
   /**
    * Check whether a token is valid
    *
    * @param authHeader a HTTP header that <strong>ASSUREDLY</strong> contains bearer
    *                   token (using jwt form)
    * @return
    * <strong>false</strong> :
    *          - if payload data is interfered
    *          - if token is expired<br>
    *            <strong>true</strong>: otherwise
    */
   def validate(authHeader: Seq[String]): Boolean = {
      if (authHeader.isEmpty) return false
      
      Jwt.isValid(Utils.extractJWT(authHeader.head), secretKey, encodeAlgorithm)
   }
   
}
