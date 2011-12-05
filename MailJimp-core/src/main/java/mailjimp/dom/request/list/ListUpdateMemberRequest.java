package mailjimp.dom.request.list;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.enums.EmailType;
import mailjimp.dom.request.MailJimpRequest;

public class ListUpdateMemberRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  @JsonProperty("email_address")
  private String emailAddress;
  
  @JsonProperty("merge_vars")
  private Map<String,Object> mergeVars;
  
  @JsonProperty("email_type")
  private EmailType emailType;
  
  @JsonProperty("replace_interests")
  private Boolean replaceInterests;
  
  public ListUpdateMemberRequest(String apikey, String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, Boolean replaceInterests) {
    super(apikey);
    this.listId = listId;
    this.emailAddress = emailAddress;
    this.mergeVars = mergeVars;
    this.emailType = emailType;
    this.replaceInterests = replaceInterests;
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Map<String, Object> getMergeVars() {
    return mergeVars;
  }

  public void setMergeVars(Map<String, Object> mergeVars) {
    this.mergeVars = mergeVars;
  }

  public EmailType getEmailType() {
    return emailType;
  }

  public void setEmailType(EmailType emailType) {
    this.emailType = emailType;
  }

  public Boolean getReplaceInterests() {
    return replaceInterests;
  }

  public void setReplaceInterests(Boolean replaceInterests) {
    this.replaceInterests = replaceInterests;
  }
}