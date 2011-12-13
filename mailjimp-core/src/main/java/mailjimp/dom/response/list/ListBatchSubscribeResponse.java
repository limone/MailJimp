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

public class ListBatchSubscribeResponse extends MailJimpResponse {
  @JsonProperty("add_count")
  private Integer addCount;
  
  @JsonProperty("update_count")
  private Integer updateCount;
  
  @JsonProperty("error_count")
  private Integer errorCount;
  
  private List<ListBatchError> errors;
  
  public ListBatchSubscribeResponse() {
    // empty
  }

  @Override
  public String toString() {
    return "ListBatchSubscribeResponse [addCount=" + addCount + ", updateCount=" + updateCount + ", errorCount=" + errorCount + ", errors=" + errors + "]";
  }

  public Integer getAddCount() {
    return addCount;
  }

  public void setAddCount(Integer addCount) {
    this.addCount = addCount;
  }

  public Integer getUpdateCount() {
    return updateCount;
  }

  public void setUpdateCount(Integer updateCount) {
    this.updateCount = updateCount;
  }

  public Integer getErrorCount() {
    return errorCount;
  }

  public void setErrorCount(Integer errorCount) {
    this.errorCount = errorCount;
  }

  public List<ListBatchError> getErrors() {
    return errors;
  }

  public void setErrors(List<ListBatchError> errors) {
    this.errors = errors;
  }
}