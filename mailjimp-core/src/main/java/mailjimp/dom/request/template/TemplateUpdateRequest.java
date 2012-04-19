package mailjimp.dom.request.template;

import java.util.HashMap;
import java.util.Map;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;


public class TemplateUpdateRequest extends MailJimpRequest {
	private static final long serialVersionUID = 1L;

	private int id = -1;
	
	@JsonProperty
	private Map<String, String> values = null;
	
	public TemplateUpdateRequest(String apikey, int id, String name, String html) {
		super(apikey);
		
		if (values == null)
			values = new HashMap<String,String>();
		else
			values.clear();
		
		this.id = id;
		
		if (name != null)
			values.put("name", name);
		
		if (html != null)
			values.put("html", html);
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	
}
