/*
 * Copyright 2011 Eike Hirsch
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
import java.util.List;

/**
 * Represents a group of MailChimps groupings.
 * 
 * @author Eike Hirsch (me at eike-hirsch dot net) Date: 05.05.11 Time: 10:10
 */
@SuppressWarnings("serial")
public class Groups implements Serializable {
  private int    id;
  private String name;
  private String formField;
  private List<Group> groups;

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the assigned groups as a comma separated string.
   * 
   * @return The assigned groups as a comma separated string.
   */
  public List<Group> getGroups() {
    return groups;
  }

  /**
   * Set the assigned groups as a comma separated string.
   * 
   * @param groups
   *          The assigned groups as a comma separated string.
   */
  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }
}