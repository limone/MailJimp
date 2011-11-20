package mailjimp.dom.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListsResponse extends MailJimpResponse {
  private Integer total;
  
  @JsonProperty("data")
  private List<MailingList> lists;
  
  public ListsResponse() {
    // empty
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<MailingList> getLists() {
    return lists;
  }

  public void setLists(List<MailingList> lists) {
    this.lists = lists;
  }

  @Override
  public String toString() {
    return "ListsResponse [total=" + total + ", lists=" + lists + "]";
  }
}