package mailjimp.dom.response.list;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class MemberResponseInfo implements Serializable {
  private String email;
  
  private Date timestamp;
  
  private String reason;
  
  @JsonProperty("reason_text")
  private String reasonText;
  
  public MemberResponseInfo() {
    // empty
  }

  public MemberResponseInfo(String email, Date timestamp, String reason, String reasonText) {
    this.email = email;
    this.timestamp = timestamp;
    this.reason = reason;
    this.reasonText = reasonText;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReasonText() {
    return reasonText;
  }

  public void setReasonText(String reasonText) {
    this.reasonText = reasonText;
  }

  @Override
  public String toString() {
    return "MemberResponseInfo [email=" + email + ", timestamp=" + timestamp + ", reason=" + reason + ", reasonText=" + reasonText + "]";
  }
}