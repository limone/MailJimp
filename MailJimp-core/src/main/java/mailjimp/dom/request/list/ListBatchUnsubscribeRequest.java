package mailjimp.dom.request.list;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class ListBatchUnsubscribeRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  private List<String> emails;
  
  @JsonProperty("delete_member")
  private boolean deleteMember;
  
  @JsonProperty("send_goodbye")
  private boolean sendGoodbye;
  
  @JsonProperty("send_notify")
  private boolean sendNotify;
  
  public ListBatchUnsubscribeRequest(String apikey, String listId, List<String> emails, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) {
    super(apikey);
    this.listId = listId;
    this.emails = emails;
    this.deleteMember = deleteMember;
    this.sendGoodbye = sendGoodbye;
    this.sendNotify = sendNotify;
  }

  @Override
  public String toString() {
    return "ListBatchUnsubscribeRequest [listId=" + listId + ", emails=" + emails + ", deleteMember=" + deleteMember + ", sendGoodbye=" + sendGoodbye + ", sendNotify=" + sendNotify + "]";
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public boolean isDeleteMember() {
    return deleteMember;
  }

  public void setDeleteMember(boolean deleteMember) {
    this.deleteMember = deleteMember;
  }

  public boolean isSendGoodbye() {
    return sendGoodbye;
  }

  public void setSendGoodbye(boolean sendGoodbye) {
    this.sendGoodbye = sendGoodbye;
  }

  public boolean isSendNotify() {
    return sendNotify;
  }

  public void setSendNotify(boolean sendNotify) {
    this.sendNotify = sendNotify;
  }
}