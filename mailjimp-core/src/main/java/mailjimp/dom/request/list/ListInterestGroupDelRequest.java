package mailjimp.dom.request.list;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class ListInterestGroupDelRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  @JsonProperty("group_name")
  private String groupName;
  
  @JsonProperty("grouping_id")
  private Integer groupingId;
  
  public ListInterestGroupDelRequest(String apikey, String listId, String groupName, Integer groupingId) {
    super(apikey);
    this.listId = listId;
    this.groupName = groupName;
    this.groupingId = groupingId;
  }

  @Override
  public String toString() {
    return "ListInterestGroupDelRequest [listId=" + listId + ", groupName=" + groupName + ", groupingId=" + groupingId + "]";
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Integer getGroupingId() {
    return groupingId;
  }

  public void setGroupingId(Integer groupingId) {
    this.groupingId = groupingId;
  }
}