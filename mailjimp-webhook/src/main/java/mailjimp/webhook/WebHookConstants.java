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
package mailjimp.webhook;

import mailjimp.dom.MailJimpConstants;

/**
 * Collection of special keys used in the raw data of the callbacks.
 * 
 * @author Eike Hirsch (me at eike-hirsch dot net) Date: 06.05.11 Time: 15:47
 */
public interface WebHookConstants extends MailJimpConstants {
  String REASON    = "reason";
  String OLD_EMAIL = "old_email";
  String NEW_EMAIL = "new_email";
  String ACTION    = "action";
  String LIST_ID   = "list_id";
}