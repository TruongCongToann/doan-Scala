package dbPort.user

import org.joda.time.DateTime

case class UserRecord(
                       user_id: Long,
                       username: Option[String],
                       address: Option[String],
                       phone_number: Option[String],
                       gender: Option[String],
                       email: Option[String],
                       password: Option[String],
                       role: Option[String],
                       image: Option[String],
                       position: Option[String],
                       created_at: Option[DateTime],
                       updated_at: Option[DateTime],
                       doctorid: Option[Int],
                       full_name: Option[String]

                     )
