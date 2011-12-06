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

import org.codehaus.jackson.annotate.JsonProperty;

public class Group implements Serializable {
  private String bit;
  
  private String name;
  
  @JsonProperty("display_order")
  private String displayOrder;
  
  private Integer subscribers;
  
  public Group() {
    // empty
  }

  @Override
  public String toString() {
    return "Group [bit=" + bit + ", name=" + name + ", displayOrder=" + displayOrder + ", subscribers=" + subscribers + "]";
  }

  public String getBit() {
    return bit;
  }

  public void setBit(String bit) {
    this.bit = bit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(String displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Integer getSubscribers() {
    return subscribers;
  }

  public void setSubscribers(Integer subscribers) {
    this.subscribers = subscribers;
  }
}