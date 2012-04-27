package mailjimp.dom.response.campaign;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignBounceMessageResponse implements Serializable{
	
	@JsonProperty
	int total;
	
	@JsonProperty("data")
	List<CampaignBounceMessageResponseItem> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CampaignBounceMessageResponseItem> getItems() {
		return items;
	}

	public void setItems(List<CampaignBounceMessageResponseItem> items) {
		this.items = items;
	}	
	
	@Override
	public String toString()
	{
		return "CampaignBounceMessageResponse: [total=" + total+"];";
	}
}
