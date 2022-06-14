package bbs.user.models

import org.joda.time.DateTime

import java.util.Date

/**
 * A form used for both user login and registration
 *
 * @param user_id       Optional field, used in case wants to perform user statistics
 * @param email    Required field, used when performs login and registration
 * @param password Required field, used when performs login and registration
 * @param full_name Optional field, used when want to display user name at landing page.
 */
case class User(
                 user_id: Option[UserID] = None,
                 username:Option[String]  = None,
                 address:Option[String] = None,
                 phone_number:Option[String] = None,
                 gender:Option[String] = None,
                 email: Option[String] = None,
                 password: Option[String] = None,
                 role:Option[String] = None,
                 image:Option[String] = None,
                 position:Option[String] = None,
                 created_at:Option[DateTime] = None,
                 updated_at:Option[DateTime] = None,
                 doctorid:Option[Int] = None,
                 full_name:Option[String] = None,
               ){
   override def toString: String =
      s"User: id=$user_id, email=$email, full_name=$full_name"
}
