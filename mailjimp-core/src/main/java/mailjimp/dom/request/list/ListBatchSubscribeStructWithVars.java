package mailjimp.dom.request.list;

import java.io.Serializable;
import java.util.Map;

import mailjimp.dom.enums.EmailType;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListBatchSubscribeStructWithVars extends ListBatchSubscribeStruct {
	  @JsonProperty("merge_vars")
	  private Map<String,Object> mergeVars;

	 public ListBatchSubscribeStructWithVars(String emailAddress, EmailType emailType, Map<String,Object> mergeVars)
	 {
		 super(emailAddress, emailType);
		 setMergeVars(mergeVars);
	 }
	  
	  
	public Map<String, Object> getMergeVars() {
		return mergeVars;
	}

	public void setMergeVars(Map<String, Object> mergeVars) {
		this.mergeVars = mergeVars;
	}  
	  
}
