package security

import org.scalatest.FunSuite

class EmailValidationTest extends FunSuite {
   
   private val invalidEmail   = "abc.xyz@emailc2om"
   private val invalidEmail1  = "abc.xyz.email.vn"
   private val invalidEmail2  = "abc.xyz#&@email.com"
   private val invalidEmail3  = "someone@example.comec"
   
   private val validEmail  = "toan_tc@flin-ters.vn"
   private val validEmail2 = "toan@example.other_domain.com.vn"
   private val validEmail3 = "truongcongtoan@education.inSomeCountry.com"
   private val validEmail4 = "12345@1234.vn"
   private val validEmail5 = "213v4@dfslkfjs.sdafsd_begdfg.vn"
   
   test("Invalid emails should be detected") {
      val result: Boolean = EmailValidation(invalidEmail) || EmailValidation(invalidEmail1) || EmailValidation(invalidEmail2) || EmailValidation(invalidEmail3)
      assert(!result)
   }
   
   test("Valid emails should be detected") {
      val result: Boolean = EmailValidation(validEmail) && EmailValidation(validEmail2) && EmailValidation(validEmail3) && EmailValidation(validEmail4) && EmailValidation(validEmail5)
      assert(result)
   }
}
