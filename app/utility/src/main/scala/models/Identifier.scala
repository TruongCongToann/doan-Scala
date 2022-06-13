package models

/**
 * Indicate a subclasses are acting as the primary key
 * @tparam A Type of id value. eg: Long, UUID
 */
trait Identifier[+A] extends Serializable