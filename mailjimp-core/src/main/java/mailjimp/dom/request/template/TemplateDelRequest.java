package mailjimp.dom.request.template;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class TemplateDelRequest extends MailJimpRequest {
	
	@JsonProperty
	private int id;
	
	public TemplateDelRequest(String apikey, int id) {
		super(apikey);		
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
