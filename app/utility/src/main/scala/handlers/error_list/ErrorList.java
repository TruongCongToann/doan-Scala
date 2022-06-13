package handlers.error_list;

import handlers.ErrorCode;



public final class ErrorList {
	private ErrorList() {
	}

	// 1000-1999: User
	public static final ErrorCode USER_NOT_FOUND = new ErrorCode(1000, "User not found");

	public static final ErrorCode UNAUTHORIZED = new ErrorCode(1001, "Unauthorized");

	public static final ErrorCode WRONG_ID_OR_PASSWORD = new ErrorCode(1002, "Wrong email or password");

	public static final ErrorCode INVALID_EMAIL = new ErrorCode(1003, "Invalid email");

	public static final ErrorCode EMAIL_EXISTED = new ErrorCode(1004, "Existing email");

	public static final ErrorCode PASSWORD_INSECURE = new ErrorCode(1005, "Password minimum length violated");
	public static final ErrorCode PASSWORD_TYPE = new ErrorCode(1006, "Password must containing at least 8 characters, 1 number, 1 upper and 1 lowercase");

	public static final ErrorCode UNDEFINED_CODE = new ErrorCode(9999, "Internal Server Error");

	// 2000-2999: Post
	public static final ErrorCode POST_NOT_FOUND = new ErrorCode(2000, "Post not found");

	public static final ErrorCode NEGATIVE_PARAMS = new ErrorCode(2001, "Negative params");

	public static final ErrorCode CANNOT_CREATE_POST = new ErrorCode(2002, "Cannot create post");

	public static final ErrorCode CANNOT_UPDATE_POST = new ErrorCode(2003, "Cannot update post");

	public static final ErrorCode CANNOT_DELETE_POST = new ErrorCode(2004, "Cannot delete post");

	public static final ErrorCode POST_UNAUTHORIZED = new ErrorCode(2005, "Modification privilege denied");

	public static final ErrorCode POST_TITLE_LENGTH_EXCEEDS = new ErrorCode(2006, "Post title length exceeds");

	public static final ErrorCode POST_CONTENT_LENGTH_EXCEEDS = new ErrorCode(2007, "Post content length exceeds");

}
