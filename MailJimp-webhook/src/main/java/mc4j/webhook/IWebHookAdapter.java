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

package mc4j.webhook;

import mc4j.dom.list.MemberInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * This is the gateway to your application. Implement this interface and make the class available in your
 * application context. The {@link WebHookController} will auto wire the adapter so make sure to configure only one
 * implementation of this interface. Otherwise Spring will not be able to choose the right one for you.
 *
 *
 * @author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 09:32
 */
public interface IWebHookAdapter {

	/**
	 * Checks this request if it contains the secret key and value you configured. To use this feature you have to
	 * manually add a secret parameter to the WebHooks url. You can do so at the list tools section in you MailChimp
	 * account. Simply add something like <code>?secretKey=secretValue</code> to the url.
	 * The name of the parameter can be configured in the mc4j.properties file. The key is <code>mc.mc4j.webhook.secrectKey
	 * </code>.
	 *
	 * @param request The request to be tested.
	 *
	 * @return <code>true</code> if this request is valid <code>false</code> otherwise.
	 */
	boolean isValidRequest(HttpServletRequest request);


	void userSubscribed(MemberInfo memberInfo); // TODO: listId? Even more info?

	// TODO: more to come
}
