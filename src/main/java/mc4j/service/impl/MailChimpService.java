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
import mc4j.dom.MailingList;
import mc4j.dom.MemberStatus;
import mc4j.service.IMailChimpService;
import mc4j.service.MailChimpException;

import org.apache.commons.lang.ClassUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailChimpService implements IMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	private transient XmlRpcClient client;
	
	private transient final MailChimpParser mp = new MailChimpParser();
	
	// Credentials
	private String username;
	private String password;
	private String apiKey;
	
	protected MailChimpService() {
		// empty, just for Spring
	}
	
	public MailChimpService(String username, String password, String apiKey) {
		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Username cannot be null/empty.");
		}
		
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null/empty.");
		}
		
		if (apiKey == null || apiKey.length() == 0) {
			throw new IllegalArgumentException("API key cannot be null/empty.");
		}
		
		this.username = username;
		this.password = password;
		this.apiKey = apiKey;
	}
	
	public void setUsername(String username) {
		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Username cannot be null/empty.");
		}
		
		this.username = username;
	}

	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null/empty.");
		}
		
		this.password = password;
	}

	public void setApiKey(String apiKey) {
		if (apiKey == null || apiKey.length() == 0) {
			throw new IllegalArgumentException("API key cannot be null/empty.");
		}
		
		this.apiKey = apiKey;
	}

	@PostConstruct
	protected void init() {
		log.info("Creating MailChimp integration client.");
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setEnabledForExtensions(true);
			config.setServerURL(new URL("http://api.mailchimp.com/1.2/"));
		} catch (MalformedURLException mue) {
			log.warn("MailChimp API URL was invalid.");
		}
		client = new XmlRpcClient();
		client.setConfig(config);
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
		Object[] params = new Object[] { username, password, apiKey};
		return invoke("apikeyAdd", params, "createApiKey");
	}

	@Override
	public Boolean keyExpire() throws MailChimpException {
		Object[] params = new Object[] { username, password, apiKey};
		return invoke("apikeyExpire", params, "expireApiKey");
	}

	@Override
	public List<ApiKey> keyList() throws MailChimpException {
		return keyList(true);
	}
	
	@Override
	public List<ApiKey> keyList(boolean includeExpired) throws MailChimpException {
		Object[] params = new Object[] { username, password, apiKey, includeExpired};
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
}