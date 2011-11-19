package mailjimp.dom.request;

import java.io.Serializable;

public class ListsRequest implements Serializable {
  private String apikey;
  private int start;
  private int limit;
  
  public ListsRequest() {
    // empty
  }

  public ListsRequest(String apikey, int start, int limit) {
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

  public int getStart() {
    return start;
  }

  public ListsRequest setStart(int start) {
    this.start = start;
    return this;
  }

  public int getLimit() {
    return limit;
  }

  public ListsRequest setLimit(int limit) {
    this.limit = limit;
    return this;
  }
}