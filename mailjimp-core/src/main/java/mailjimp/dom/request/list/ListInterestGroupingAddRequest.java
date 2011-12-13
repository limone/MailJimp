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

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.enums.InterestGroupingType;
import mailjimp.dom.request.MailJimpRequest;

public class ListInterestGroupingAddRequest extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  private String name;
  
  private InterestGroupingType type;
  
  private List<String> groups;
  
  public ListInterestGroupingAddRequest(String apikey, String listId, String name, InterestGroupingType type, List<String> groups) {
    super(apikey);
    this.listId = listId;
    this.name = name;
    this.type = type;
    this.groups = groups;
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InterestGroupingType getType() {
    return type;
  }

  public void setType(InterestGroupingType type) {
    this.type = type;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }
}