package mc4j.service;

import java.io.Serializable;

/**
 * All of the functionality that is exposed by the MailChimp API.
 * 
 * @author Michael Laccetti
 */
public interface IMailChimpService extends Serializable {
	/**
	 * Retrieve a list of all MailChimp API Keys for this User 
	 * 
	 * @return List of keys
	 */
	public String keyList() throws MailChimpException;
	
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