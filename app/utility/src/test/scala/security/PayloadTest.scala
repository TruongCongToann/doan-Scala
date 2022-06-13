//package security
//
//import org.scalatest.FunSuite
//import play.api.libs.json.{JsValue, Json}
//
//
//class PayloadTest extends FunSuite {
//
//   val payloadAdminJSON: JsValue = Json.parse(
//      """
//        |{
//        |"email":"long_nt@flinters.vn",
//        |"role":"admin"
//        |}
//        |""".stripMargin)
//
//   val payloadUserJSON: JsValue = Json.parse(
//      """
//        {
//            "email":"daoanhthanh@outlook.com",
//            "role":"user"
//        }
//        """)
//
//   val payloadGuestJSON: JsValue = Json.parse(
//      """
//        {
//            "email":"someone@domain.com",
//            "role":"someone I don't give a F"
//        }
//        """)
//
//
//   val payloadAdmin: Payload = Payload("long_nt@flinters.vn", Role.ADMIN)
//
//   val payloadUser: Payload = Payload("daoanhthanh@outlook.com", Role.USER)
//
//   val payloadGuest: Payload = Payload("someone@domain.com", Role.GUEST)
//
//   //ADMIN
//   test("JsValue ADMIN should be converted to Payload ADMIN") {
//      assert(Payload.parse(payloadAdminJSON) == payloadAdmin)
//   }
//
//   test("Payload ADMIN should be serialized to JSON ADMIN") {
//      assert(Json.toJson(payloadAdmin) == payloadAdminJSON)
//   }
//
//   //USER
//   test("JsValue USER should be converted to Payload USER") {
//      assert(Payload.parse(payloadUserJSON) == payloadUser)
//   }
//
//   test("Payload USER should be serialized to JSON USER") {
//      assert(Json.toJson(payloadUser) == payloadUserJSON)
//   }
//
//   //GUEST
//   test("JsValue GUEST should be converted to Payload GUEST") {
//      assert(Payload.parse(payloadGuestJSON) == payloadGuest)
//   }
//
//
//}
