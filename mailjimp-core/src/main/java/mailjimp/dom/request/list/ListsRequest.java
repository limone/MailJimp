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

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListsRequest extends MailJimpRequest {
  @JsonProperty
  private Integer start;
  
  @JsonProperty
  private Integer limit;
  
  public ListsRequest(String apikey) {
    super(apikey);
  }

  public ListsRequest(String apikey, Integer start, Integer limit) {
    super(apikey);
    this.start = start;
    this.limit = limit;
  }

  public Integer getStart() {
    return start;
  }

  public ListsRequest setStart(int start) {
    this.start = start;
    return this;
  }

  public Integer getLimit() {
    return limit;
  }

  public ListsRequest setLimit(int limit) {
    this.limit = limit;
    return this;
  }
}