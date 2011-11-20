package mailjimp.dom.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListsRequest implements Serializable {
  @JsonProperty
  private String apikey;
  
  @JsonProperty
  private Integer start;
  
  @JsonProperty
  private Integer limit;
  
  public ListsRequest() {
    // empty
  }
  
  public ListsRequest(String apikey) {
    this.apikey = apikey;
  }

  public ListsRequest(String apikey, Integer start, Integer limit) {
    this.apikey = apikey;
    this.start = start;
    this.limit = limit;
  }

  public String getApikey() {
    return apikey;
  }

  public ListsRequest setApikey(String apikey) {
    this.apikey = apikey;
    return this;
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