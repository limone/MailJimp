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

import mailjimp.dom.response.MailJimpResponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListMembersResponse extends MailJimpResponse {
  private Integer total;
  
  @JsonProperty("data")
  private List<MemberResponseInfo> members;
  
  public ListMembersResponse() {
    // empty
  }

  public ListMembersResponse(Integer total, List<MemberResponseInfo> members) {
    this.total = total;
    this.members = members;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<MemberResponseInfo> getMembers() {
    return members;
  }

  public void setMembers(List<MemberResponseInfo> members) {
    this.members = members;
  }

  @Override
  public String toString() {
    return "ListMembersResponse [total=" + total + ", members=" + members + "]";
  }
}