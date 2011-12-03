package mailjimp.dom.response.list;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.response.MailJimpResponse;

public class ListMemberInfoResponse extends MailJimpResponse {
  private Integer success;
  private Integer errors;
  
  @JsonProperty("data")
  private List<MemberInfo> members;
  
  public ListMemberInfoResponse() {
    // empty
  }

  @Override
  public String toString() {
    return "ListMemberInfoResponse [success=" + success + ", errors=" + errors + ", members=" + members + "]";
  }

  public Integer getSuccess() {
    return success;
  }

  public void setSuccess(Integer success) {
    this.success = success;
  }

  public Integer getErrors() {
    return errors;
  }

  public void setErrors(Integer errors) {
    this.errors = errors;
  }

  public List<MemberInfo> getMembers() {
    return members;
  }

  public void setMembers(List<MemberInfo> members) {
    this.members = members;
  }
}