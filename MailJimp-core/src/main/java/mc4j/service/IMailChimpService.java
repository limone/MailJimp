/*
 * Copyright 2010-2011 Michael Laccetti
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
package mc4j.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mc4j.dom.*;
import mc4j.dom.list.MailingList;
import mc4j.dom.list.MemberInfo;
import mc4j.dom.list.MemberStatus;
import mc4j.dom.security.ApiKey;

/**
 * All of the functionality that is exposed by the <a href="http://apidocs.mailchimp.com/1.3/index.php"
 * title="MailChimp APi">MailChimp API</a>.
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
	public boolean keyExpire() throws MailChimpException;
	
	/**
	 * Retrieve all mailing lists.
	 * 
	 * @return List of Mailing Lists
	 * @throws MailChimpException
	 */
	public List<MailingList> getLists() throws MailChimpException;
	
	/**
	 * Retrieve all members for specific mailing list.
	 * @param listId
	 * @param memberStatus
	 * @param since
	 * @param start
	 * @param limit
	 * @return
	 * @throws MailChimpException
	 */
	public Map<String,Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailChimpException;
	
	/**
	 * Retrieve member info for a specific mailing list.
	 * @param listId
	 * @param emailAddress
	 * @return
	 * @throws MailChimpException
	 */
	public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailChimpException;
	
	/**
	 * Subscribe a user to a mailing list.
	 * @param listId
	 * @param emailAddress
	 * @param mergeVars
	 * @param emailType
	 * @param doubleOptin
	 * @param updateExisting
	 * @param replaceInterests
	 * @param sendWelcome
	 * @return
	 * @throws MailChimpException
	 */
	public boolean listSubscribe(String listId, String emailAddress, Map<String,Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailChimpException;

	/**
	 * Batch subscribe a user to a mailing list. See <a href="http://apidocs.mailchimp.com/1.3/listbatchsubscribe.func.php"
	 * title="MailChimp API">MailChimp API</a> for more info.
	 *
	 *
	 * @param listId
	 * @param batch
	 * @param doubleOptin
	 * @param updateExisting
	 * @param replaceInterests
	 *
	 * @return The result of this call. Containing add, update and error counts. In case of errors contains additional
	 * information.
	 *
	 * @throws MailChimpException
	 */
	public BatchResult listBatchSubscribe(String listId,
	                                      Object[] batch,
	                                      boolean doubleOptin,
	                                      boolean updateExisting,
	                                      boolean replaceInterests)
			throws MailChimpException;

	/**
	 * Update a members info on a mailing list.
	 *
	 * @param listId
	 * @param emailAddress
	 * @param mergeVars
	 * @param emailType
	 * @param replaceInterests
	 * @return
	 * @throws MailChimpException
	 */
	public boolean listUpdateMember(String listId, String emailAddress, Map<String,Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailChimpException;

	/**
	 * Unsubscribe a user from a mailing list.
	 * @param listId
	 * @param emailAddress
	 * @param deleteMember
	 * @param sendGoodbye
	 * @param sendNotify
	 * @return
	 * @throws MailChimpException
	 */
	public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailChimpException;

}