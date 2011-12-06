/*
 * Copyright 2011 Michael Laccetti
 *
 * This file is part of MailJimp.
 *
 * MailJimp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * MailJimp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MailJimp.  If not, see <http://www.gnu.org/licenses/>.
 */
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