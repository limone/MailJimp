package mailjimp.dom.response.campaign;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignListResponse implements Serializable{
	
	@JsonProperty
	int total;
	
	@JsonProperty("data")
	List<CampaignListResponseItem> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CampaignListResponseItem> getItems() {
		return items;
	}

	public void setItems(List<CampaignListResponseItem> items) {
		this.items = items;
	}	

	

}
