package mailjimp.dom.response;

public class MailJimpError extends MailJimpResponse {
  private String error;
  private String code;
  
  public MailJimpError() {
    // empty
  }

  public MailJimpError(String error, String code) {
    this.error = error;
    this.code = code;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}