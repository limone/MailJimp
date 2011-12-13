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
package mailjimp.dom.request.list;

import java.util.List;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListMemberInfoRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  @JsonProperty("email_address")
  private List<String> emailAddresses;
  
  /**
   * Get all the information for particular members of a list.
   * 
   * @param apikey A valid API key for your user account
   * @param listId The list ID to connect to
   * @param emailAddresses  An array of up to 50 email addresses to get information for.
   *                        Alternately, the IDs for the members returned from listMembers, 
   *                        WebHooks and Campaigns.
   */
  public ListMemberInfoRequest(String apikey, String listId, List<String> emailAddresses) {
    super(apikey);
    this.listId = listId;
    this.emailAddresses = emailAddresses;
  }

  public String getListId() {
    return listId;
  }

  public ListMemberInfoRequest setListId(String listId) {
    this.listId = listId;
    return this;
  }

  public List<String> getEmailAddresses() {
    return emailAddresses;
  }

  public ListMemberInfoRequest setEmailAddresses(List<String> emailAddresses) {
    this.emailAddresses = emailAddresses;
    return this;
  }
}