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

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.enums.InterestGroupingUpdateType;
import mailjimp.dom.request.MailJimpRequest;

public class ListInterestGroupingUpdateRequest extends MailJimpRequest {
  @JsonProperty("grouping_id")
  private Integer groupingId;
  
  private InterestGroupingUpdateType name;
  private String value;
  
  public ListInterestGroupingUpdateRequest(String apikey, Integer groupingId, InterestGroupingUpdateType name, String value) {
    super(apikey);
    this.groupingId = groupingId;
    this.name = name;
    this.value = value;
  }

  public Integer getGroupingId() {
    return groupingId;
  }

  public void setGroupingId(Integer groupingId) {
    this.groupingId = groupingId;
  }

  public InterestGroupingUpdateType getName() {
    return name;
  }

  public void setName(InterestGroupingUpdateType name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
