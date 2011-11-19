package mailjimp.service.impl;

import mailjimp.service.IMailJimpService;

abstract class AbstractMailJimpService implements IMailJimpService {
  protected static final String SERVER_URL_PREFIX_HTTP = "http://";
  protected static final String SERVER_URL_PREFIX_HTTPS = "https://";
  protected static final String SERVER_URL_MAIN = ".api.mailchimp.com/";

  //Credentials
  protected String username;
  protected String password;
  protected String apiKey;
  protected String apiVersion;
  protected boolean ssl = true;
  
  protected AbstractMailJimpService() {
    // empty
  }

  protected AbstractMailJimpService(String username, String password, String apiKey, String apiVersion, boolean ssl) {
    this.username = username;
    this.password = password;
    this.apiKey = apiKey;
    this.apiVersion = apiVersion;
    this.ssl = ssl;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }

  public void setSsl(boolean ssl) {
    this.ssl = ssl;
  }

  protected void checkConfig() {
    if (apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("API key cannot be null/empty.");
    }
    if (apiVersion == null || apiVersion.isEmpty()) {
      throw new IllegalArgumentException("API version cannot be null/empty.");
    }
  }

  /**
   * Constructs the url of the MailChimp server to talk to. This takes the data
   * center out of the api key and knows if the connection should get secured
   * via ssl.
   * 
   * @return The URL of the MailChimp server to use.
   */
  protected String buildServerURL() {
    StringBuilder serverURL = new StringBuilder();
    // choose the protocol
    if (ssl) {
      serverURL.append(SERVER_URL_PREFIX_HTTPS);
    } else {
      serverURL.append(SERVER_URL_PREFIX_HTTP);
    }

    final int dcSeparater = apiKey.lastIndexOf('-');
    final String dcLocation = dcSeparater != -1 ? apiKey.substring(dcSeparater + 1) : "us1";

    serverURL
    // parse the data center
    .append(dcLocation)
    // bring in the clue
    .append(SERVER_URL_MAIN)
    // add the version
    .append(apiVersion)
    // and finish with a slash.
    .append('/');
    return serverURL.toString();
  }
}