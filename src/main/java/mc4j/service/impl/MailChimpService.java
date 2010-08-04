package mc4j.service.impl;

import javax.annotation.PostConstruct;

import mc4j.service.IMailChimpService;
import mc4j.service.MailChimpExceptionMapper;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	private final JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
	private IMailChimpService svc;
	
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
		bean.setServiceClass(IMailChimpService.class);
		bean.setAddress("https://api.mailchimp.com/1.2/");
		bean.setProvider(new MailChimpExceptionMapper());
		svc = bean.create(IMailChimpService.class, new Object[]{});
		bean.getInInterceptors().add(new LoggingInInterceptor());
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
	}
	

	public String listApiKeys() {
		return svc.listApiKeys("xml", "apikeys", username, password, apiKey, false);
	}
}