/*
 * Copyright 2010-2011 Michael Laccetti
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
package mailjimp.dom.security;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class ApiKey implements Serializable {
  @JsonProperty("apikey")
  private String apiKey;
  
  @JsonProperty("created_at")
  private Date   createdAt;
  
  @JsonProperty("expired_at")
  private Date   expiredAt;

  public ApiKey() {
    // empty
  }

  public ApiKey(String apiKey, Date createdAt, Date expiredAt) {
    this.apiKey = apiKey;
    this.createdAt = createdAt;
    this.expiredAt = expiredAt;
  }

  @Override
  public String toString() {
    return "ApiKey [apiKey=" + apiKey + ", createdAt=" + createdAt + ", expiredAt=" + expiredAt + "]";
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getExpiredAt() {
    return expiredAt;
  }

  public void setExpiredAt(Date expiredAt) {
    this.expiredAt = expiredAt;
  }
}