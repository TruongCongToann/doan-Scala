package dbPort.user

import bbs.repository.user.UserRepository
import bbs.user.models.{User, UserID}

import dbPort.user.mappers.UserMapper
import handlers.BBSException
import handlers.error_list.ErrorList
import org.joda.time.DateTime
import security.PasswordUtils

import scala.util.{Failure, Success, Try}

class UserRepositoryImpl extends UserRepository {

   override def register(user: User): Try[UserID] = Try {
//      println(
//         "gia tri nhan duoc la "+
//           user
//       )
      var res = UserDAO.createWithAttributes(

         (Symbol("username"), user.username),
         (Symbol("address"), user.address),
         (Symbol("phone_number"), user.phone_number),
         (Symbol("gender"), user.gender),
         (Symbol("email"), user.email),
         (Symbol("password"), PasswordUtils.encryptPassword(user.password.get)),
         (Symbol("role"), user.role),
         (Symbol("image"), user.image),
         (Symbol("position"), user.position),
         (Symbol("created_at"), DateTime.now),
         (Symbol("updated_at"), DateTime.now),
         (Symbol("doctorid"), user.doctorid),
         (Symbol("full_name"), user.full_name),

      )
      UserID(res)
//      UserID(
//         UserDAO.createWithAttributes(
//
//            (Symbol("username"), user.username.get),
//            (Symbol("address"), user.address.get),
//            (Symbol("phone_number"), user.phone_number.get),
//            (Symbol("gender"), user.gender.get),
//            (Symbol("email"), user.email.get),
//            (Symbol("password"), PasswordUtils.encryptPassword(user.password.get)),
//            (Symbol("role"), user.role.get),
//            (Symbol("image"), user.image.get),
//            (Symbol("position"), user.position.get),
//            (Symbol("created_at"), DateTime.now),
//            (Symbol("updated_at"), DateTime.now),
//            (Symbol("doctorid"), user.doctorid.get),
//            (Symbol("full_name"), user.full_name.get),
//
//         )
//      )
   }

   override def existById(id: Long): Try[Boolean] = UserDAO.existsById(id)

   override def existsByEmail(email: String): Try[Boolean] = UserDAO.existsByEmail(email)

   override def findByEmail(email: String): Try[User] = UserDAO.findByEmail(email).map(UserMapper.toEntity)

   override def findByID(id: Long): Try[User] = {
      UserDAO.findById(id) match {
         case Some(userRecord: UserRecord) => Success(UserMapper.toEntity(userRecord))
         case None => Failure(new BBSException(ErrorList.USER_NOT_FOUND))
      }
   }


}
