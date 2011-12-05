package mailjimp.dom.request.list;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class ListUnsubscribeRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  @JsonProperty("email_address")
  private String emailAddress;
  
  @JsonProperty("delete_member")
  private Boolean deleteMember;
  
  @JsonProperty("send_goodbye")
  private Boolean sendGoodbye;
  
  @JsonProperty("send_notify")
  private Boolean sendNotify;

  public ListUnsubscribeRequest(String apikey, String listId, String emailAddress, Boolean deleteMember, Boolean sendGoodbye, Boolean sendNotify) {
    super(apikey);
    this.listId = listId;
    this.emailAddress = emailAddress;
    this.deleteMember = deleteMember;
    this.sendGoodbye = sendGoodbye;
    this.sendNotify = sendNotify;
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

  public Boolean getDeleteMember() {
    return deleteMember;
  }

  public void setDeleteMember(Boolean deleteMember) {
    this.deleteMember = deleteMember;
  }

  public Boolean getSendGoodbye() {
    return sendGoodbye;
  }

  public void setSendGoodbye(Boolean sendGoodbye) {
    this.sendGoodbye = sendGoodbye;
  }

  public Boolean getSendNotify() {
    return sendNotify;
  }

  public void setSendNotify(Boolean sendNotify) {
    this.sendNotify = sendNotify;
  }
}