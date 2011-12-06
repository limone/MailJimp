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

public class InterestGrouping extends GroupingParent {
  @JsonProperty("form_field")
  private String formField;
  
  @JsonProperty("display_order")
  private String displayOrder;
  
  private List<Group> groups;
  
  public InterestGrouping() {
    // empty
  }

  @Override
  public String toString() {
    return "InterestGrouping [formField=" + formField + ", displayOrder=" + displayOrder + ", groups=" + groups + "]";
  }

  public String getFormField() {
    return formField;
  }

  public void setFormField(String formField) {
    this.formField = formField;
  }

  public String getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(String displayOrder) {
    this.displayOrder = displayOrder;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }
}