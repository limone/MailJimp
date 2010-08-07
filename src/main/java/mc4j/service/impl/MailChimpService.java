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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import mc4j.dom.MailChimpError;
import mc4j.dom.Pair;
import mc4j.service.IMailChimpAPI;
import mc4j.service.IMailChimpService;
import mc4j.service.MailChimpExceptionMapper;
import mc4j.xml.MailChimpParser;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

public class MailChimpService implements IMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	private final JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
	private IMailChimpAPI svc;
	
	@Autowired
	private MailChimpParser mcParser;
	
	// Constants
	private static final String FORMAT = "xml";
	
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
		bean.setServiceClass(IMailChimpAPI.class);
		bean.setAddress("https://api.mailchimp.com/1.2/");
		bean.setProvider(new MailChimpExceptionMapper());
		svc = bean.create(IMailChimpAPI.class, new Object[]{});
		bean.getInInterceptors().add(new LoggingInInterceptor());
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
	}
	
	private Pair<Document,List<MailChimpError>> convertAndCheck(Response r) {
		try {
			Document d = mcParser.convertToDocument(r);
			List<MailChimpError> errors = mcParser.checkErrors(d);
			return new Pair<Document, List<MailChimpError>>(d, errors);
		} catch (Exception ex) {
			log.error("Could not convert XML to document and check for errors.", ex);
			return null;
		}
	}

	@Override
	public String keyAdd() {
		Pair<Document,List<MailChimpError>> ret = convertAndCheck(svc.keyAdd(FORMAT, "apikeyAdd", username, password, apiKey));
		if (ret.t2 != null) {
			log.warn("Errors found while trying to add a key.");
		}
		return null;
	}

	@Override
	public Boolean keyExpire() {
		Pair<Document,List<MailChimpError>> ret = convertAndCheck(svc.keyExpire(FORMAT, "apikeyExpire", username, password, apiKey));
		return Boolean.TRUE;
	}

	@Override
	public String keyList() {
		Pair<Document,List<MailChimpError>> ret = convertAndCheck(svc.keyList(FORMAT, "apikeys", username, password, apiKey, false));
		return null;
	}
}