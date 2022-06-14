package bbs.repository.user

import bbs.user.models.{User, UserID}
import handlers.BBSException

import scala.util.Try

trait UserRepository {

   /**
    * Persist new User
    * @param user new User
    * @return id of new User
    */
   def register(user: User): Try[UserID]

   def existsByEmail(email: String): Try[Boolean]

   def existById(id: Long): Try[Boolean]

   def findByEmail(email:String): Try[User]

   def updateUser(email: String, user: User): Try[Boolean]

   def findByID(id: Long): Try[User]
   /**
    * Find post by id
    *
    * @param id the value of UserID
    * @return user which has `id` value as identification
    * @throws BBSException when records is not found
    */
}
