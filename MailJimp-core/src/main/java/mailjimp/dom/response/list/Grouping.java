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

import java.util.List;

/**
 * Represents a group of MailChimps groupings.
 * 
 * @author Eike Hirsch (me at eike-hirsch dot net) Date: 05.05.11 Time: 10:10
 */
@SuppressWarnings("serial")
public class Grouping extends GroupingParent {
  private List<String> groups;

  /**
   * Get the assigned groups as a comma separated string.
   * 
   * @return The assigned groups as a comma separated string.
   */
  public List<String> getGroups() {
    return groups;
  }

  /**
   * Set the assigned groups as a comma separated string.
   * 
   * @param groups
   *          The assigned groups as a comma separated string.
   */
  public void setGroups(List<String> groups) {
    this.groups = groups;
  }
}