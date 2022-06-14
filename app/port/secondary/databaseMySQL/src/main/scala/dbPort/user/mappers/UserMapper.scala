package dbPort.user.mappers

import bbs.user.models.{User, UserID}
import dbPort.user.UserRecord
import org.joda.time.DateTime

object UserMapper {

  def toEntities(records: Seq[UserRecord]): Seq[User] = {
    records.map(this.toEntity)
  }

  def toEntity(record: UserRecord): User = {
    User(
      user_id = Some(UserID(record.user_id)),
      username =record.username,
      address =record.address,
      phone_number = record.phone_number,
      gender =record.gender,
      email = record.email,
      password = record.password,
      role =record.role,
      image = record.image,
      position = record.position,
      created_at = record.created_at,
      updated_at = record.updated_at,
      doctorid = record.doctorid,
      full_name = record.full_name
    )
  }

  def toRecords(users: Seq[User]): Seq[UserRecord] = {
    users.map(this.toRecord)
  }

  def toRecord(user: User): UserRecord = {
    UserRecord(
      user_id = user.user_id.get.value,
      username= (user.username),
      address = user.address,
      phone_number=user.phone_number,
      gender = user.gender,
      email = user.email,
      password = user.password,
      role = user.role,
      image = user.image,
      position = user.position,

      created_at = Some(DateTime.now()),
      updated_at = Some(DateTime.now()),
      doctorid = user.doctorid,
      full_name = user.full_name,
    )
  }
}
