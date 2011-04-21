package mc4j.service;

/**
 * Exception which is thrown every time we encounter an unexpected response. Chances are good that the api version
 * is not supported.
 *
 * User: Eike Hirsch
 * Date: 21.04.11
 * Time: 16:32
 */
public class UnexpectedMailChimpResponseException extends MailChimpException {

	public UnexpectedMailChimpResponseException() {
		super();
	}

	public UnexpectedMailChimpResponseException(String s) {
		super(s);
	}

	public UnexpectedMailChimpResponseException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public UnexpectedMailChimpResponseException(Throwable throwable) {
		super(throwable);
	}
}
