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

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import mailjimp.dom.WebHookData;
import mailjimp.dom.response.list.MemberInfo;

/**
 * This is the gateway to your application. Implement this interface and make
 * the class available in your application context. The
 * {@link WebHookController} will auto wire the adapter. So make sure to
 * configure only one implementation of this interface. Otherwise Spring will
 * not be able to choose the right one for you.
 * 
 * 
 * @author Eike Hirsch (me at eike-hirsch dot net)
 *         Date: 03.05.11
 *         Time: 09:32
 */
public interface IWebHookAdapter extends Serializable {

  /**
	 * Checks if this request is secure. It's a good idea to manually add a secret parameter to the WebHooks url and
	 * test that parameter and it's value here. You can always add a parameter at the list tools section in you
	 * MailChimp account. Simply add something like <code>?secretKey=secretValue</code> to the url.
   * 
   * @param request
   *          The request to be tested.
   * 
   * @return <code>true</code> if this request is valid <code>false</code>
   *          otherwise.
   */
  boolean isValidRequest(HttpServletRequest request);

  /**
   * Called every time a new user subscribed to a list.
   * 
   * This callback contains a MemberInfo object.
   * 
   * See <a href="http://http://apidocs.mailchimp.com/webhooks/"
   * title="WebHook API docs">WebHook API docs</a> for instructions on how to
   * get a complete list of all keys.
   * 
   * @param data
   *          all the data containing {@link MemberInfo}:
   */
  void userSubscribed(WebHookData data);

  /**
   * Called every time a user has unsubscribed from a list.
   * 
   * This callback contains a MemberInfo object.
   * 
   * Additional raw data keys:
   * <ul>
   * <li>action (describes whether it was via a <code>delete</code> or actual
   * <code>unsub</code>)</li>
   * <li>reason (hard|manual|?)</li>
   * <li>campaign_id</li>
   * </ul>
   * 
   * See <a href="http://http://apidocs.mailchimp.com/webhooks/"
   * title="WebHook API docs">WebHook API docs</a> for instructions on how to
   * get a complete list.
   * 
   * @param data
   *          all the data containing {@link MemberInfo}:
   */
  void userUnsubscribed(WebHookData data);

  /**
   * A user updated her/his profile.
   * 
   * This callback contains a MemberInfo object.
   * 
   * See <a href="http://http://apidocs.mailchimp.com/webhooks/"
   * title="WebHook API docs">WebHook API docs</a> for instructions on how to
   * get a complete list.
   * 
   * @param data
   *          all the data containing {@link MemberInfo}:
   */
  void profileUpdated(WebHookData data);

  /**
   * Called whenever a users eMail address changed.
   * 
   * Available raw data keys:
   * <ul>
   * <li>list_id</li>
   * <li>new_id</li>
   * <li>new_email</li>
   * <li>old_email</li>
   * </ul>
   * 
   * See <a href="http://http://apidocs.mailchimp.com/webhooks/"
   * title="WebHook API docs">WebHook API docs</a> for instructions on how to
   * get a complete list.
   * 
   * @param data
   *          No MemberInfo but the above keys.
   */
  void eMailUpdated(WebHookData data);

  /**
   * Called for a cleaned account.
   * 
   * Available raw data keys:
   * <ul>
   * <li>list_id</li>
   * <li>campaign_id</li>
   * <li>reason
   * (Reason will be one of "hard" (for hard bounces) or "abuse")</li>
   * <li>email</li>
   * </ul>
   * 
   * See <a href="http://http://apidocs.mailchimp.com/webhooks/"
   * title="WebHook API docs">WebHook API docs</a> for instructions on how to
   * get a complete list.
   * 
   * @param data
   *          No MemberInfo but the above keys.
   * 
   * 
   */
  void cleaned(WebHookData data);
}