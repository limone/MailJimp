package mailjimp.dom.request.list;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListsRequest extends MailJimpRequest {
  @JsonProperty
  private Integer start;
  
  @JsonProperty
  private Integer limit;
  
  public ListsRequest(String apikey) {
    super(apikey);
  }

  public ListsRequest(String apikey, Integer start, Integer limit) {
    super(apikey);
    this.start = start;
    this.limit = limit;
  }

  public Integer getStart() {
    return start;
  }

  public ListsRequest setStart(int start) {
    this.start = start;
    return this;
  }

  public Integer getLimit() {
    return limit;
  }

  public ListsRequest setLimit(int limit) {
    this.limit = limit;
    return this;
  }
}