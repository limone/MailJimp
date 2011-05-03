package mc4j.dom;

/**
* Author: Eike Hirsch (me at eike-hirsch dot net)
* Date: 27.04.11
* Time: 11:44
*/
public class BatchError implements IParsableProperty {
	String email;
	int code;
	String message;

	@Override
	public String toString() {
		return "BatchError{" +
				"email='" + email + '\'' +
				", code='" + code + '\'' +
				", message='" + message + '\'' +
				'}';
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
