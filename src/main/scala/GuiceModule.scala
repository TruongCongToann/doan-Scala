import bbs.repository.user.UserRepository
import com.google.inject.AbstractModule
import dbPort.user.UserRepositoryImpl

class GuiceModule extends AbstractModule {
   lazy val userRepository: UserRepository = new UserRepositoryImpl

   override def configure(): Unit = {
      bind(classOf[UserRepository]).toInstance(userRepository)

   }
}
