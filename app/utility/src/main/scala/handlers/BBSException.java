package handlers;

/**
 * return code for client, print message in log
 */
public class BBSException extends RuntimeException {

	private final ErrorCode errorCode;

	public BBSException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BBSException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Return error code of the BBSException
	 * @return error code
	 */
	public int getCode() {
		return this.errorCode.getCode();
	}
}
