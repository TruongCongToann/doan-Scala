package dbPort.user

import bbs.repository.user.UserRepository
import bbs.user.models.{User, UserID}
import dbPort.user.mappers.UserMapper
import handlers.BBSException
import handlers.error_list.ErrorList
import org.joda.time.DateTime
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, sqls}
import security.PasswordUtils

import scala.util.{Failure, Success, Try}

class UserRepositoryImpl extends UserRepository {

  override def register(user: User): Try[UserID] = Try {

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
  }

  override def existById(id: Long): Try[Boolean] = UserDAO.existsById(id)

  override def existsByEmail(email: String): Try[Boolean] = UserDAO.existsByEmail(email)

  override def findByEmail(email: String): Try[User] = UserDAO.findByEmail(email).map(UserMapper.toEntity)

  //get user by id
  override def findByID(id: Long): Try[User] = {
    UserDAO.findById(id) match {
      case Some(userRecord: UserRecord) => Success(UserMapper.toEntity(userRecord))
      case None => Failure(new BBSException(ErrorList.USER_NOT_FOUND))
    }
  }

  //update user info
  override def updateUser(email: String, user: User): Try[Boolean] = Try {
    //    val userRecord: UserRecord = userMapper(user)


    UserDAO.updateBy(sqls"email = $email")
      .withAttributes(
        (Symbol("username"), user.username),
        (Symbol("address"), user.address),
        (Symbol("phone_number"), user.phone_number),
        (Symbol("gender"), user.gender),
        (Symbol("email"), user.email),
        (Symbol("password"), user.password),
        (Symbol("role"), user.role),
        (Symbol("image"), user.image),
        (Symbol("position"), user.position),
        (Symbol("created_at"), DateTime.now),
        (Symbol("updated_at"), DateTime.now),
        (Symbol("doctorid"), user.doctorid),
        (Symbol("full_name"), user.full_name)
      ) >
      0

  }

  private def userMapper(user: User): UserRecord = UserRecord(user_id = user.user_id.get.value,
    username = user.username,
    address = user.address,
    phone_number = user.phone_number,
    gender = user.gender,
    email = user.email,
    password = user.password,
    role = user.role,
    image = user.image,
    position = user.position,
    created_at = user.created_at,
    updated_at = user.updated_at,
    doctorid = user.doctorid,
    full_name = user.full_name

  )
}
