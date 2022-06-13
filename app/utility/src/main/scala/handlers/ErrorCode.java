package handlers;

/**
 * return error code to client, print error message in log
 */
public class ErrorCode {
	private final int code;
	private final String message;

	public ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}
