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

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import mc4j.service.IMailChimpService;
import mc4j.service.MailChimpException;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailChimpService implements IMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	private transient XmlRpcClient client;
	
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
			config.setServerURL(new URL("http://api.mailchimp.com/1.2/"));
		} catch (MalformedURLException mue) {
			log.warn("MailChimp API URL was invalid.");
		}
		client = new XmlRpcClient();
		client.setConfig(config);
	}
	
	@Override
	public String keyAdd() throws MailChimpException {
		/*Document d = convertAndCheck(svc.keyAdd(FORMAT, "apikeyAdd", username, password, apiKey));
		String key = mcParser.retrieveContent(d, String.class);
		return key;*/
		return null;
	}

	@Override
	public Boolean keyExpire() throws MailChimpException {
		/*Document d = convertAndCheck(svc.keyExpire(FORMAT, "apikeyExpire", username, password, apiKey));
		Boolean isExpired = mcParser.retrieveContent(d, Boolean.class);
		return isExpired;*/
		return Boolean.FALSE;
	}

	@Override
	public String keyList() throws MailChimpException {
		/*ApiKeys keys = svc.keyList(FORMAT, "apikeys", username, password, apiKey, false);
		log.debug("Key: {}", keys.toString());
		return null;*/
		return null;
	}
}