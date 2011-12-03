package mailjimp.dom.response.list;

import java.util.List;

import mailjimp.dom.response.MailJimpResponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListMembersResponse extends MailJimpResponse {
  private Integer total;
  
  @JsonProperty("data")
  private List<MemberResponseInfo> members;
  
  public ListMembersResponse() {
    // empty
  }

  public ListMembersResponse(Integer total, List<MemberResponseInfo> members) {
    this.total = total;
    this.members = members;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<MemberResponseInfo> getMembers() {
    return members;
  }

  public void setMembers(List<MemberResponseInfo> members) {
    this.members = members;
  }

  @Override
  public String toString() {
    return "ListMembersResponse [total=" + total + ", members=" + members + "]";
  }
}