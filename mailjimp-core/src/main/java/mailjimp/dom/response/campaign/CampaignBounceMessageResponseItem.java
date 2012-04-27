package mailjimp.dom.response.campaign;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignBounceMessageResponseItem implements Serializable{

	@JsonProperty
	String date;
	
	@JsonProperty
	String email;
	
	@JsonProperty
	String message;
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString()
	{
		return "CampaignBoucneMessageResponseItem [date=" + date + ", email=" + email + ", message=" + message + "];";
	}
	
	
	}
