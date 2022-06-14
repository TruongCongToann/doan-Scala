package bbs.services.user

import bbs.repository.user.UserRepository
import bbs.user.models.{User, UserID}
import handlers.BBSException
import handlers.error_list.ErrorList
import security.{PasswordUtils, PasswordValidation, Payload}

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success, Try}

@Singleton
@Inject
class UserServices @Inject()(userRepository: UserRepository) {


  /**
   * Persist new User
   *
   * @param userForm dto
   * @return id of new User
   */
  def register(userForm: User): Try[UserID] = {


    if (userRepository.existsByEmail(userForm.email.get).get) {
      Failure(new BBSException(ErrorList.EMAIL_EXISTED))
    } else {

      val isValidPassword = PasswordValidation(userForm.password.get)

      if (userForm.password.get.length < 6) {
        Failure(new BBSException(ErrorList.PASSWORD_INSECURE))
      }else if (! isValidPassword){
        Failure(new BBSException(ErrorList.PASSWORD_TYPE))
      }else{

        userRepository.register(userForm)
      }
    }
  }

  /**
   * Return UserID if login successfully
   *
   * @param userForm user login form
   * @return user id
   */
  def login(userForm: User): Option[UserID] = {

    val user: Try[User] = userRepository.findByEmail(userForm.email.get)

    user match {

      case Success(user) =>
        val hashedPassword = user.password
        if (PasswordUtils.encryptPassword(userForm.password.get) == hashedPassword.get) {
          user.user_id
        } else None

      case _ => None
    }
  }

  //get user by id
  def getUserByEmail(email: String): Try[User] = userRepository.findByEmail(email)

  private def isRequestSentByAValidAuthor(email: String, payload: Payload): Boolean = {
    // check if userId in payload exists
    val userId = userRepository.existById(payload.userId) match {
      case Success(true) => payload.userId
      case _ => throw new BBSException(ErrorList.USER_NOT_FOUND)
    }

    val actualAuthorID: UserID = userRepository.findByEmail(email) match {
      case Success(value) => value.user_id.get
      case Failure(exception) => throw exception
    }

    userId == actualAuthorID.value
  }
//update user
  def updateUser(email: String, user: User, payload: Payload): Try[Boolean] = {


    if (!isRequestSentByAValidAuthor(email, payload)) throw new BBSException(ErrorList.POST_UNAUTHORIZED)

    userRepository.updateUser(email, user)

  }


}
