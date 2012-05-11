package mailjimp.dom.request.list;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.request.MailJimpRequest;

public class ListBatchSubscribeRequestWithVars extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  private List<ListBatchSubscribeStructWithVars> batch;
  
  @JsonProperty("double_optin")
  private boolean doubleOptin;
  
  @JsonProperty("update_existing")
  private boolean updateExisting;
  
  @JsonProperty("replace_interests")
  private boolean replaceInterests;

  public ListBatchSubscribeRequestWithVars(String apikey, String listId, List<ListBatchSubscribeStructWithVars> batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) {
    super(apikey);
    this.listId = listId;
    this.batch = batch;
    this.doubleOptin = doubleOptin;
    this.updateExisting = updateExisting;
    this.replaceInterests = replaceInterests;
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public List<ListBatchSubscribeStructWithVars> getBatch() {
    return batch;
  }

  public void setBatch(List<ListBatchSubscribeStructWithVars> batch) {
    this.batch = batch;
  }

  public boolean isDoubleOptin() {
    return doubleOptin;
  }

  public void setDoubleOptin(boolean doubleOptin) {
    this.doubleOptin = doubleOptin;
  }

  public boolean isUpdateExisting() {
    return updateExisting;
  }

  public void setUpdateExisting(boolean updateExisting) {
    this.updateExisting = updateExisting;
  }

  public boolean isReplaceInterests() {
    return replaceInterests;
  }

  public void setReplaceInterests(boolean replaceInterests) {
    this.replaceInterests = replaceInterests;
  }
}