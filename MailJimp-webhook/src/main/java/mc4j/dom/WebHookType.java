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
package mc4j.dom;

/**
 *
 * @author Eike Hirsch (me at eike-hirsch dot net)
 *         Date: 06.05.11
 *         Time: 11:31
 */
public enum WebHookType {
	SUBSCRIBE("subscribe"),
	UNSUBSCRIBE("unsubscribe"),
	UPDATE_PROFILE("profile"),
	UPDATE_EMAIL("upemail"),
	CLEANED("cleaned");

	private String type;

	WebHookType(String type) {
		this.type = type;
	}
}
