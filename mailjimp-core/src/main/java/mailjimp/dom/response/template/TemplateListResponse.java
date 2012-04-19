package mailjimp.dom.response.template;

import java.util.List;

import mailjimp.dom.response.MailJimpResponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateListResponse extends MailJimpResponse {

	@JsonProperty("user")
	List<TemplateListItemResponse> listItems;

	public List<TemplateListItemResponse> getListItems() {
		return listItems;
	}

	public void setListItems(List<TemplateListItemResponse> listItems) {
		this.listItems = listItems;
	}
	
}
