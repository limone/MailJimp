package mc4j.service;

public class MailChimpException extends Exception {
	private final int statusCode;
	
	public MailChimpException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public MailChimpException(int statusCode, String message, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}