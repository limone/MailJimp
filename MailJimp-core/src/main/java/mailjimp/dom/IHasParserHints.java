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
package mailjimp.dom;

import mailjimp.util.ParserHint;

import java.io.Serializable;
import java.util.Map;

/**
 * The {@link mailjimp.service.impl.MailJimpParser parser} does its best to
 * guess the correct types of the properties it has to set. But when it comes to
 * merges this is not enough - IHasParserHints to the rescue!
 * 
 * Implementing classes can provide a mapping between some of their property
 * names and the name of a merges value.
 * 
 * @author Eike Hirsch (me at eike-hirsch dot net) Date: 05.05.11 Time: 08:23
 */
public interface IHasParserHints extends Serializable {
  /**
   * Returns the Mapping between properties of the implementing class and the
   * name of the key found in the response from the MailChimp server.
   * 
   * @return The mapping.
   */
  Map<String, ParserHint> getHints();
}