package mc4j.dom;

import java.io.Serializable;
import java.util.Date;

public class ApiKey implements Serializable {
	private String apiKey;
	private Date createdAt;
	private Date expiredOn;
	
	public ApiKey() {
		// empty
	}

	public ApiKey(String apiKey, Date createdAt, Date expiredOn) {
		this.apiKey = apiKey;
		this.createdAt = createdAt;
		this.expiredOn = expiredOn;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}
}