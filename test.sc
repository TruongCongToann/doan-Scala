import handlers.ErrorCode

import scala.util.{Failure, Success, Try}


def isGreaterThanOne(value: Int): Try[Boolean] = Try {
  value > 1
}


if (isGreaterThanOne(1) == true) {

}



object ErrorList {
  final val USER_NOT_FOUND: ErrorCode = new ErrorCode(1000, "User not found");
}


println(isGreaterThanOne(1))

println(isGreaterThanOne(1) == true)
