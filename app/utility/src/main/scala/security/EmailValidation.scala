package security

/**
 * Helper class to verify email string
 */
object EmailValidation {
//   private val pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$".r

 val emailValid = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$".r
   def apply(email: String): Boolean = {

      return email.matches(emailValid.toString())
   }
}
object PasswordValidation{
  val passwordValid = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".r
  def  apply(password:String) : Boolean ={
    return password.matches(passwordValid.toString())
  }
}