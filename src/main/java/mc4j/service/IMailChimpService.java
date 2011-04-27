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
package mc4j.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mc4j.dom.ApiKey;
import mc4j.dom.EmailType;
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
	 * Update a members info on a mailing list.
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