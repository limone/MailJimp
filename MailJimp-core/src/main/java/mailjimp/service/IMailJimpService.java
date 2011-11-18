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
package mailjimp.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mailjimp.dom.*;
import mailjimp.dom.list.Groups;
import mailjimp.dom.list.MailingList;
import mailjimp.dom.list.MemberInfo;
import mailjimp.dom.list.MemberStatus;
import mailjimp.dom.security.ApiKey;

/**
 * All of the functionality that is exposed by the <a
 * href="http://apidocs.mailchimp.com/1.3/index.php"
 * title="MailChimp APi">MailChimp API</a>.
 * 
 * @author Michael Laccetti
 */
public interface IMailJimpService extends Serializable {
  /**
   * Retrieve a list of all MailChimp API Keys for this User - expired keys
   * included.
   * 
   * @return List of keys
   */
  public List<ApiKey> keyList() throws MailJimpException;

  /**
   * Retrieve a list of all MailChimp API Keys for this user -
   * including/excluding expired keys.
   * 
   * Please note that if you have not provided your username and password, this method will return an empty list.
   * 
   * @param includeExpired
   * @return
   * @throws MailJimpException
   */
  public List<ApiKey> keyList(boolean includeExpired) throws MailJimpException;

  /**
   * Add an API Key to your account.
   * 
   * Please note that if you have not provided your username and password, this method will return null.
   * 
   * @return Newly generated key
   */
  public String keyAdd() throws MailJimpException;

  /**
   * Expire a Specific API Key
   * 
   * Please note that if you have not provided your username and password, this method will return false.
   * 
   * @return Whether or not the API key was expired
   */
  public boolean keyExpire() throws MailJimpException;

  /**
   * Retrieve all mailing lists.
   * 
   * @return List of Mailing Lists
   * @throws MailJimpException
   */
  public List<MailingList> getLists() throws MailJimpException;

  /**
   * Retrieve all members for specific mailing list.
   * 
   * @param listId
   * @param memberStatus
   * @param since
   * @param start
   * @param limit
   * @return
   * @throws MailJimpException
   */
  public Map<String, Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailJimpException;

  /**
   * Retrieve member info for a specific mailing list.
   * 
   * @param listId
   * @param emailAddress
   * @return
   * @throws MailJimpException
   */
  public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailJimpException;

  /**
   * Subscribe a user to a mailing list.
   * 
   * @param listId
   * @param emailAddress
   * @param mergeVars
   * @param emailType
   * @param doubleOptin
   * @param updateExisting
   * @param replaceInterests
   * @param sendWelcome
   * @return
   * @throws MailJimpException
   */
  public boolean listSubscribe(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailJimpException;

  /**
   * Batch subscribe a user to a mailing list. See <a
   * href="http://apidocs.mailchimp.com/1.3/listbatchsubscribe.func.php"
   * title="MailChimp API">MailChimp API</a> for more info.
   * 
   * 
   * @param listId
   * @param batch
   * @param doubleOptin
   * @param updateExisting
   * @param replaceInterests
   * 
   * @return The result of this call. Containing add, update and error counts.
   *         In case of errors contains additional information.
   * 
   * @throws MailJimpException
   */
  public BatchResult listBatchSubscribe(String listId, Object[] batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) throws MailJimpException;

  /**
   * Update a members info on a mailing list.
   * 
   * @param listId
   * @param emailAddress
   * @param mergeVars
   * @param emailType
   * @param replaceInterests
   * @return
   * @throws MailJimpException
   */
  public boolean listUpdateMember(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailJimpException;

  /**
   * Unsubscribe a user from a mailing list.
   * 
   * @param listId
   * @param emailAddress
   * @param deleteMember
   * @param sendGoodbye
   * @param sendNotify
   * @return
   * @throws MailJimpException
   */
  public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailJimpException;
  
  /**
   * Get the list of interest groupings for a given mailing list, including the label, form information, and included groups for each.
   * 
   * @param listId              ID of the list
   * 
   * @return                    List of interest groups for the list.
   * @throws MailJimpException  If there was a problem retrieving the list of groups.
   */
  public List<Groups> listInterestGroupings(String listId) throws MailJimpException;
  
  /**
   * Create a single Interest Groups - if groups are not enabled, they will automatically be turned on when adding the first group.
   * 
   * @param listId              ID of the list
   * @param groupName           Name of the group to add
   * @param groupingId          (optional) ID for the new group to use - If not supplied, the first grouping on the list will be used. 
   * 
   * @return                    True if succeeded, an error otherwise.
   * @throws MailJimpException  If the interest group could not be added.
   */
  public boolean listInterestGroupAdd(String listId, String groupId, Integer groupingId) throws MailJimpException;
  
  /**
   * Delete a single Interest Groups - if the last group for a list is deleted, this will also turn groups for the list off.
   * 
   * @param listId              ID of the list
   * @param groupName           Name of the group to delete
   * @param groupingId          The grouping to delete the group from - if not supplied, the first grouping on the list is used.
   * 
   * @return                    True if succeeded, an error otherwise.
   * @throws MailJimpException  If the interest group could not be removed.
   */
  public boolean listInterestGroupDel(String listId, String groupName, Integer groupingId) throws MailJimpException;
  
  /**
   * Change the name of an interest group.
   * 
   * @param listId              ID of the list
   * @param oldName             The interest group name to be changed
   * @param newName             The new interest grup name to be set
   * 
   * @return                    True if succeeded, an error otherwise.
   * @throws MailJimpException  If the interest group could not be renamed.
   */
  public boolean listInterestGroupUpdate(String listId, String oldName, String newName) throws MailJimpException;
}