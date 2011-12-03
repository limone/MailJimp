package mailjimp.dom.request;

import java.io.Serializable;

public abstract class MailJimpRequest implements Serializable {
  protected String apikey;
  
  protected MailJimpRequest(String apikey) {
    this.apikey = apikey;
  }

  public String getApikey() {
    return apikey;
  }

  public MailJimpRequest setApikey(String apikey) {
    this.apikey = apikey;
    return this;
  }
}