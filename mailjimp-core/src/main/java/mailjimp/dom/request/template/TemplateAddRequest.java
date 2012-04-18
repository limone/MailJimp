package mailjimp.dom.request.template;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;


public class TemplateAddRequest extends MailJimpRequest {
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String html;
	
	public TemplateAddRequest(String apikey, String name, String html) {
		super(apikey);
		this.name =name;
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}	

}
