/**
 * Copyright 2010 Michael Laccetti
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package mc4j.service.impl;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import mc4j.dom.ApiKey;
import mc4j.dom.EmailType;
import mc4j.dom.MailingList;
import mc4j.dom.MemberInfo;
import mc4j.dom.MemberStatus;
import mc4j.service.IMailChimpService;
import mc4j.service.MailChimpException;

import org.apache.commons.lang.ClassUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailChimpService implements IMailChimpService {

	private static transient final Logger log = LoggerFactory.getLogger(MailChimpService.class);

	private transient XmlRpcClient client;

	private transient final MailChimpParser mp = new MailChimpParser();

	private static final String SERVER_URL_PREFIX_HTTP = "http://";
	private static final String SERVER_URL_PREFIX_HTTPS = "https://";
	private static final String SERVER_URL_MAIN = ".api.mailchimp.com/";

	// Credentials
	private String username;
	private String password;
	private String apiKey;
	private String apiVersion;
	private boolean ssl = false;

	protected MailChimpService() {
		// empty, just for Spring
	}
	
	public MailChimpService(final String username,
	                        final String password,
	                        final String apiKey,
	                        final String apiVersion,
							final boolean ssl) {
		this.username = username;
		this.password = password;
		this.apiKey = apiKey;
		this.apiVersion = apiVersion;
		this.ssl = ssl;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	@PostConstruct
	protected void init() {
		// check if everything is configured.
		checkConfig();
		log.info("Creating MailChimp integration client.");
		String url = buildServerURL();
		log.info("Server URL is: {}", url);
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setEnabledForExtensions(true);
			config.setServerURL(new URL(url));
		} catch (MalformedURLException mue) {
			log.warn("MailChimp API URL was invalid.");
		}
		client = new XmlRpcClient();
		client.setConfig(config);
	}

	private void checkConfig() {
		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Username cannot be null/empty.");
		}
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null/empty.");
		}
		if (apiKey == null || apiKey.length() == 0) {
			throw new IllegalArgumentException("API key cannot be null/empty.");
		}
		if (apiVersion == null || apiVersion.length() == 0) {
			throw new IllegalArgumentException("API version cannot be null/empty.");
		}
	}

	/**
	 * Constructs the url of the MailChimp server to talk to. This takes the data center out of the api key and knows
	 * if the connection should get secured via ssl.
	 *
	 * @return The URL of the MailChimp server to use.
	 */
	private String buildServerURL() {
		StringBuilder serverURL = new StringBuilder();
		// choose the protocol
		if(ssl) {
			serverURL.append(SERVER_URL_PREFIX_HTTPS);
		} else {
			serverURL.append(SERVER_URL_PREFIX_HTTP);
		}

		serverURL
				// parse the data center
				.append(apiKey.substring(apiKey.lastIndexOf('-') + 1))
				// bring in the clue
				.append(SERVER_URL_MAIN)
				// add the version
				.append(apiVersion)
				// and finish with a slash.
				.append('/');

		return serverURL.toString();
	}

	@SuppressWarnings("unchecked")
	private <T> T invoke(String method, Object[] params, String methodName) throws MailChimpException {
		try {
			Method m = ClassUtils.getPublicMethod(MailChimpParser.class, methodName, new Class[]{Object.class});
			return (T)m.invoke(mp, client.execute(method, params));
		} catch (Exception ex) {
			log.error("Could not invoke XML RPC client.", ex);
			throw new MailChimpException("Could not invoke XML RPC client.", ex);
		}
	}
	
	@Override
	public String keyAdd() throws MailChimpException {
		Object[] params = new Object[] { username, password, apiKey };
		return invoke("apikeyAdd", params, "createApiKey");
	}

	@Override
	public boolean keyExpire() throws MailChimpException {
		Object[] params = new Object[] { username, password, apiKey };
		return (Boolean)invoke("apikeyExpire", params, "expireApiKey");
	}

	@Override
	public List<ApiKey> keyList() throws MailChimpException {
		return keyList(true);
	}
	
	@Override
	public List<ApiKey> keyList(boolean includeExpired) throws MailChimpException {
		Object[] params = new Object[] { username, password, apiKey, includeExpired };
		return invoke("apikeys", params, "parseApiKeys");
	}

	@Override
	public List<MailingList> getLists() throws MailChimpException {
		Object[] params = new Object[] { apiKey };
		return invoke("lists", params, "parseLists");
	}

	@Override
	public Map<String, Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailChimpException {
		List<Object> p = new ArrayList<Object>();
		p.add(apiKey);
		p.add(listId);
		p.add(memberStatus.getStatus());
		if (since != null) {
			p.add(MailChimpConstants.sdf.format(since));
		}
		if (start != null) {
			p.add(start);
		}
		if (limit != null) {
			p.add(limit);
		}
		return invoke("listMembers", p.toArray(), "parseListMembers");
	}

	@Override
	public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailChimpException {
		System.out.println("emailAddress = " + emailAddress);
		Object address = emailAddress;
		if("1.3".equals(apiVersion)) {
			address = new Object[]{address};
		}
		Object[] params = new Object[] { apiKey, listId, address };
		return invoke("listMemberInfo", params, "parseListMemberInfo");
	}

	@Override
	public boolean listSubscribe(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailChimpException {
		Object[] params = new Object[] { apiKey, listId, emailAddress, mergeVars, emailType.getEmailType(), doubleOptin, updateExisting, replaceInterests, sendWelcome };
		return (Boolean)invoke("listSubscribe", params, "parseListSubscribe");
	}

	@Override
	public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailChimpException {
		Object[] params = new Object[] { apiKey, listId, emailAddress, deleteMember, sendGoodbye, sendNotify };
		return (Boolean)invoke("listUnsubscribe", params, "parseListUnsubscribe");
	}

	@Override
	public boolean listUpdateMember(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailChimpException {
		Object[] params = new Object[] { apiKey, listId, emailAddress, mergeVars, emailType.getEmailType(), replaceInterests };
		return (Boolean)invoke("listUpdateMember", params, "parseListUpdateMember");
	}
}