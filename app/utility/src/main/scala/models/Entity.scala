package models

trait Entity[ID <: Identifier[_]] extends Timestamp {
   val id: ID
}
