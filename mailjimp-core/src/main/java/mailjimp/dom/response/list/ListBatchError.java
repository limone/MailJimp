package mailjimp.dom.response.list;

import java.io.Serializable;

public class ListBatchError implements Serializable {
  private String email;
  private Integer code;
  private String message;
  
  public ListBatchError() {
    // empty
  }

  @Override
  public String toString() {
    return "ListBatchError [email=" + email + ", code=" + code + ", message=" + message + "]";
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}