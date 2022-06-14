package dbPort.user

import scalikejdbc.{DBSession, WrappedResultSet, sqls}
import skinny.orm.{Alias, SkinnyCRUDMapper}

import scala.util.Try

object UserDAO extends SkinnyCRUDMapper[UserRecord] {

   override lazy val tableName = "users"

   override def defaultAlias: Alias[UserRecord] = createAlias("u")

   def u: Alias[UserRecord] = defaultAlias


   override def primaryKeyFieldName: String = "user_id"

   override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[UserRecord]): UserRecord = {
      UserRecord(
         user_id = rs.get(n.user_id),
         username = rs.get(n.username),
         address = rs.get(n.address),
         phone_number = rs.get(n.phone_number),
         gender = rs.get(n.gender),
         email = rs.get(n.email),
         password = rs.get(n.password),
         role = rs.get(n.role),
         image = rs.get(n.image),
         position = rs.get(n.position),
         created_at = rs.get(n.created_at),
         updated_at = rs.get(n.updated_at),
         doctorid = rs.get(n.doctorid),
         full_name = rs.get(n.full_name),

      )
   }

   def findByEmail(email: String)(implicit s: DBSession = autoSession): Try[UserRecord] = Try {
      val record = findBy(sqls.eq(defaultAlias.email, email))
      record match {
         case Some(userRecord: UserRecord) => Some(userRecord).get
         case None => throw new Exception(s"Email $email does not exist.")
      }
   }

   def existsByEmail(email: String): Try[Boolean] = Try {
      countBy(sqls.eq(defaultAlias.email, email)) > 0
   }

   def existsById(id: Long): Try[Boolean] = Try(countBy(sqls.eq(defaultAlias.user_id, id)) == 1)

//   def updateByEmail(email: String, user: UserRecord) = Try{
//      updateBy(sqls.eq(u.mail, email)).withNamedValues(
//         column.user_id -> user.user_id,
//         column.username -> user.username,
//         column.address -> user.address,
//
//      )
//   }
}
