package mailjimp.dom.response.campaign;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignMembersResponse implements Serializable{
	
	@JsonProperty
	int total;
	
	@JsonProperty("data")
	List<CampaignMembersResponseItem> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CampaignMembersResponseItem> getItems() {
		return items;
	}

	public void setItems(List<CampaignMembersResponseItem> items) {
		this.items = items;
	}	
	
	@Override
	public String toString()
	{
		return "CampaignBounceMessageResponse: [total=" + total+"];";
	}
}
