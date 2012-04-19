package mailjimp.dom.request.campaign;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignDeleteRequest extends MailJimpRequest {
	
	
	@JsonProperty("cid")
	private String campaignId;	

	public CampaignDeleteRequest(String apikey, String campaignId) {
		super(apikey);
		this.campaignId = campaignId;

	}

	public String getCampaignId() {
		return campaignId;
	}
	
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

}