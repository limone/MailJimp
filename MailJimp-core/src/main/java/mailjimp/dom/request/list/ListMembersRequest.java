package mailjimp.dom.request.list;

import java.util.Date;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListMembersRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  private String status;
  
  private Date since;
  
  private Integer start;
  
  private Integer limit;
  
  public ListMembersRequest(String apikey, String listId, String status, Date since, Integer start, Integer limit) {
    super(apikey);
    this.listId = listId;
    this.status = status;
    this.since = since;
    this.start = start;
    this.limit = limit;
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getSince() {
    return since;
  }

  public void setSince(Date since) {
    this.since = since;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }
}