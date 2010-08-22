package mc4j.dom;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="")
@XStreamAlias("")
public class ApiKey {
	@XmlElement(name="apikey")
	@XStreamAlias("apikey")
	private String apiKey;
	
	@XmlElement(name="created_at")
	@XStreamAlias("created_at")
	private Date createdAt;
	
	@XmlElement(name="expired_at")
	@XStreamAlias("expired_at")
	private Date expiredAt;
	
	public ApiKey() {
		// empty
	}

	public ApiKey(String apiKey, Date createdAt, Date expiredAt) {
		this.apiKey = apiKey;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
	}

	@Override
	public String toString() {
		return "ApiKey [apiKey=" + apiKey + ", createdAt=" + createdAt + ", expiredAt=" + expiredAt + "]";
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

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
}