package bbs.controllers.user

import bbs.controllers.user.UserConverter._
import bbs.services.user.UserServices
import bbs.user.models.{User, UserID}
import handlers.error_list.ErrorList
import handlers.{BBSException, BBSResponse, ResponseHandler}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{Json, __}
import play.api.mvc.{Action, _}
import security.{EmailValidation, JWTUtility, PasswordValidation, Payload, Role}

import javax.inject.Inject
import scala.util.{Failure, Success}

@Inject
class UserController @Inject()(
                                 cc: ControllerComponents,
                                 userServices: UserServices
                              ) extends AbstractController(cc) with ResponseHandler {
   

   override val logger: Logger = LoggerFactory.getLogger(getClass)

   def register: Action[AnyContent] = Action {
      implicit request => {
         val userForm = request.body.asJson.map(_.as[User])

         userForm match {
            case Some(userForm) =>

               if (!EmailValidation(userForm.email.get)) {
                  BBSResponse.badRequest(new BBSException(ErrorList.INVALID_EMAIL))
               }
               else {

                  val newID = userServices.register(userForm)

                  newID match {

                     case Success(value) => {
                        val token: String = JWTUtility.generate(
                           Payload(
                              userId = value.value,
                              role = Role.USER
                           )
                        )
                        val response = Json.obj(
                           ("new_user", value.value),
                           ("token", token)
                        )

                        BBSResponse.ok(response)
                     }

                     case Failure(cause: BBSException) => BBSResponse.badRequest(cause)

                     case Failure(_) => BBSResponse.badRequest
                  }
               }

            case _ => BBSResponse.badRequest
         }
      }
   }

   //get user by id
   def getUserByEmail(email:String) : Action[AnyContent]= Action{
      loggingAction {
         userServices.getUserByEmail(email) match {
            case Failure(cause: BBSException) => throw cause
            case Success(value) => {
               Json.toJson(value)
            }
         }
      }
   }
   //log in
   def login: Action[AnyContent] = Action {
      implicit request =>
         loggingAction {
            val userForm = request.body.asJson.map(_.as[User])

            logger.info(userForm.get.toString)

            userForm match {
               case Some(value) =>

                  val userID = userServices.login(value)

                  if (userID.isDefined) {
                     val token: String = JWTUtility.generate(
                        Payload(
                           userId = userID.get.value,

                           role = Role.USER
                        )
                     )

                     Json.obj(
                        ("token_type", "bearer"),
                        ("access_token", token)
                     )

                  } else {
                     throw new BBSException(ErrorList.WRONG_ID_OR_PASSWORD)
                  }

               case _ => throw new BBSException(ErrorList.UNDEFINED_CODE)
            }
         }
   }

   def updateUser(email: String): Action[AnyContent] = Action {
      request => {
         loggingAction {

            val payload: Payload = authorityInterceptor(request)
            val isUpdated = for {
               user: User <- request.body.asJson.map(_.as[User])
               result <- userServices.updateUser(email, user, payload).toOption

            } yield result



            isUpdated match {
               case Some(true) => okCreateUser(email)
               case _ => throw new BBSException(ErrorList.CANNOT_UPDATE_POST)
            }
         }
      }
   }


}
