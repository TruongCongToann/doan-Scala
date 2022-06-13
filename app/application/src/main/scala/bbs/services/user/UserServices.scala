package bbs.services.user

import bbs.repository.user.UserRepository
import bbs.user.models.{User, UserID}
import handlers.BBSException
import handlers.error_list.ErrorList
import security.{PasswordUtils, PasswordValidation}

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

//    if (userRepository.existsByEmail(userForm.email.get) != true) {
//
//
//    }
//    else {
//      Failure(new BBSException(ErrorList.EMAIL_EXISTED))
//    }
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
}