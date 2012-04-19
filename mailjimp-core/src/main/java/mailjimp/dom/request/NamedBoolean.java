package mailjimp.dom.request;

import org.codehaus.jackson.annotate.JsonProperty;

public class NamedBoolean {
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private Boolean value;

	public NamedBoolean(String name, Boolean value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
	
	
}
