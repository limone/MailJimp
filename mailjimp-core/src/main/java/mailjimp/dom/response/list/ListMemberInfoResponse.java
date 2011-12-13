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

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.response.MailJimpResponse;

public class ListMemberInfoResponse extends MailJimpResponse {
  private Integer success;
  private Integer errors;
  
  @JsonProperty("data")
  private List<MemberInfo> members;
  
  public ListMemberInfoResponse() {
    // empty
  }

  @Override
  public String toString() {
    return "ListMemberInfoResponse [success=" + success + ", errors=" + errors + ", members=" + members + "]";
  }

  public Integer getSuccess() {
    return success;
  }

  public void setSuccess(Integer success) {
    this.success = success;
  }

  public Integer getErrors() {
    return errors;
  }

  public void setErrors(Integer errors) {
    this.errors = errors;
  }

  public List<MemberInfo> getMembers() {
    return members;
  }

  public void setMembers(List<MemberInfo> members) {
    this.members = members;
  }
}