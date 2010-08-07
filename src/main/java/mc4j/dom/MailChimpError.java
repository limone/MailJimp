package mc4j.dom;

import java.io.Serializable;

public class MailChimpError implements Serializable {
	private String error;
	private Integer code;
	
	public MailChimpError() {
		// empty
	}

	public MailChimpError(String error, Integer code) {
		this.error = error;
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}