package mailjimp.dom.response.campaign;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignMembersResponseItem implements Serializable{
	
		
	@JsonProperty
	String email;
	
	@JsonProperty
	String status;
	
	@JsonProperty
	String absplit_group;
	
	@JsonProperty
	String tz_group;
	
	
	@Override
	public String toString()
	{
		return "CampaignBoucneMessageResponseItem [tz_group=" + tz_group + ", email=" + email + ", absplit_group=" + absplit_group + "];";
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAbsplit_group() {
		return absplit_group;
	}


	public void setAbsplit_group(String absplit_group) {
		this.absplit_group = absplit_group;
	}


	public String getTz_group() {
		return tz_group;
	}


	public void setTz_group(String tz_group) {
		this.tz_group = tz_group;
	}
	
	

}
