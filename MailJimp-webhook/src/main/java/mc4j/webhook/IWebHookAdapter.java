package mc4j.webhook;

import mc4j.dom.MemberInfo;

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
