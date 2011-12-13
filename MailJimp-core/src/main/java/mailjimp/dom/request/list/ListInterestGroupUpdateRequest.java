package mailjimp.dom.request.list;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class ListInterestGroupUpdateRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  @JsonProperty("old_name")
  private String oldName;
  
  @JsonProperty("new_name")
  private String newName;
  
  public ListInterestGroupUpdateRequest(String apikey, String listId, String oldName, String newName) {
    super(apikey);
    this.listId = listId;
    this.oldName = oldName;
    this.newName = newName;
  }

  @Override
  public String toString() {
    return "ListInterestGroupUpdateRequest [listId=" + listId + ", oldName=" + oldName + ", newName=" + newName + "]";
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getOldName() {
    return oldName;
  }

  public void setOldName(String oldName) {
    this.oldName = oldName;
  }

  public String getNewName() {
    return newName;
  }

  public void setNewName(String newName) {
    this.newName = newName;
  }
}