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

import java.io.Serializable;

/**
 * Marker interface to show the {@link mailjimp.service.impl.MailJimpParser}
 * that the implementing class should be parsed.
 * 
 * The parser will throw an exception if it has to try to set a complex type
 * property. If the properties type implements this interface and if the parsed
 * value is of type {@link java.util.Map} the parser will try to parse and set
 * the property.
 * 
 * Author: Eike Hirsch (me at eike-hirsch dot net) Date: 01.05.11 Time: 13:06
 */
public interface IParsableProperty extends Serializable {
  // empty
}