package mc4j.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mc4j.dom.ApiKey;
import mc4j.dom.MailingList;
import mc4j.dom.MemberInfo;
import mc4j.dom.MemberStatus;

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
	
	public List<MailingList> getLists() throws MailChimpException;
	public Map<String,Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailChimpException;
	public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailChimpException;
}