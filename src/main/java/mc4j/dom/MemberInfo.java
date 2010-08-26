package mc4j.dom;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class MemberInfo implements Serializable {
	private String id;
	private String email;
	private String emailType;
	private Map<String,String> merges;
	private MemberStatus status;
	private String ipOpt;
	private String ipSignup;
	private Integer memberRating;
	private String campaignId;
	private Date timestamp;
	private Date infoChanged;
	private Integer webId;
	
	public MemberInfo() {
		// empty
	}

	public MemberInfo(String id, String email, String emailType, Map<String,String> merges, MemberStatus status, String ipOpt, String ipSignup, Integer memberRating, String campaignId, Date timestamp, Date infoChanged, Integer webId) {
		this.id = id;
		this.email = email;
		this.emailType = emailType;
		this.merges = merges;
		this.status = status;
		this.ipOpt = ipOpt;
		this.ipSignup = ipSignup;
		this.memberRating = memberRating;
		this.campaignId = campaignId;
		this.timestamp = timestamp;
		this.infoChanged = infoChanged;
		this.webId = webId;
	}

	@Override
	public String toString() {
		return "MemberInfo [campaignId=" + campaignId + ", email=" + email + ", emailType=" + emailType + ", id=" + id + ", infoChanged=" + infoChanged + ", ipOpt=" + ipOpt + ", ipSignup=" + ipSignup + ", memberRating=" + memberRating + ", merges=" + merges + ", status=" + status + ", timestamp=" + timestamp + ", webId=" + webId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public Map<String,String> getMerges() {
		return merges;
	}

	public void setMerges(Map<String,String> merges) {
		this.merges = merges;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

	public String getIpOpt() {
		return ipOpt;
	}

	public void setIpOpt(String ipOpt) {
		this.ipOpt = ipOpt;
	}

	public String getIpSignup() {
		return ipSignup;
	}

	public void setIpSignup(String ipSignup) {
		this.ipSignup = ipSignup;
	}

	public Integer getMemberRating() {
		return memberRating;
	}

	public void setMemberRating(Integer memberRating) {
		this.memberRating = memberRating;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getInfoChanged() {
		return infoChanged;
	}

	public void setInfoChanged(Date infoChanged) {
		this.infoChanged = infoChanged;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}
}