package mc4j.service;

import java.io.Serializable;
import java.util.List;

import mc4j.dom.ApiKey;

/**
 * All of the functionality that is exposed by the MailChimp API.
 * 
 * @author Michael Laccetti
 */
public interface IMailChimpService extends Serializable {
	/**
	 * Retrieve a list of all MailChimp API Keys for this User - expired keys included.
	 * 
	 * @return List of keys
	 */
	public List<ApiKey> keyList() throws MailChimpException;

	/**
	 * Retrieve a list of all MailChimp API Keys for this user - including/excluding expired keys.
	 * @param includeExpired
	 * @return
	 * @throws MailChimpException
	 */
	public List<ApiKey> keyList(boolean includeExpired) throws MailChimpException;
	
	/**
	 * Add an API Key to your account. 
	 * 
	 * @return Newly generated key
	 */
	public String keyAdd() throws MailChimpException;
	
	/**
	 * Expire a Specific API Key.
	 * 
	 * @return Whether or not the API key was expired
	 */
	public Boolean keyExpire() throws MailChimpException;
}