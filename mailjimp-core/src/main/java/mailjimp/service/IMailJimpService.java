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

import mailjimp.dom.enums.EmailType;
import mailjimp.dom.enums.InterestGroupingType;
import mailjimp.dom.enums.InterestGroupingUpdateType;
import mailjimp.dom.enums.MemberStatus;
import mailjimp.dom.request.NamedBoolean;
import mailjimp.dom.request.list.ListBatchSubscribeStruct;
import mailjimp.dom.response.list.InterestGrouping;
import mailjimp.dom.response.list.ListBatchSubscribeResponse;
import mailjimp.dom.response.list.ListBatchUnsubscribeResponse;
import mailjimp.dom.response.list.MailingList;
import mailjimp.dom.response.list.MemberInfo;
import mailjimp.dom.response.list.MemberResponseInfo;
import mailjimp.dom.response.template.TemplateInfoResponse;
import mailjimp.dom.response.template.TemplateListResponse;
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
   * Retrieve a list of all MailChimp API Keys for this user -
   * including/excluding expired keys.
   * 
   * Please note that if you have not provided your username and password, this method will return an empty list.
   * 
   * @param includeExpired
   * @return
   * @throws MailJimpException
   */
  public List<ApiKey> apikeys(boolean expired) throws MailJimpException;

  
  /**
   * Add an API Key to your account.
   * 
   * Please note that if you have not provided your username and password, this method will return null.
   * 
   * @return Newly generated key
   */
  public String apikeyAdd() throws MailJimpException;

  /**
   * Expire a Specific API Key
   * 
   * Please note that if you have not provided your username and password, this method will return false.
   * 
   * @param keyToExpire The key to expire - note that we force you to pass one in, in case you don't want to expire the
   * one that is currently in use!
   * @return Whether or not the API key was expired
   */
  public boolean apikeyExpire(String keyToExpire) throws MailJimpException;

  /**
   * Retrieve all mailing lists.
   * 
   * @return List of Mailing Lists
   * @throws MailJimpException
   */
  public List<MailingList> lists() throws MailJimpException;

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
  public List<MemberResponseInfo> listMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailJimpException;

  /**
   * Retrieve member info for a specific mailing list.
   * 
   * @param listId
   * @param emailAddress
   * @return
   * @throws MailJimpException
   */
  public List<MemberInfo> listMemberInfo(String listId, List<String> emailAddresses) throws MailJimpException;

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
   * Batch subscribe a user to a mailing list. See the <a
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
  public ListBatchSubscribeResponse listBatchSubscribe(String listId, List<ListBatchSubscribeStruct> batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) throws MailJimpException;
  
  
  /**
   * Batch subscribe many users to a list.  See the <a
   * href="http://apidocs.mailchimp.com/api/rtfm/listbatchunsubscribe.func.php"
   * title="MailChimp API">MailChimp API</a> for more info.
   * 
   * @param listId
   * @param emails
   * @param deleteMember
   * @param sendGoodbye
   * @param sendNotify
   * @return
   * @throws MailJimpException
   */
  public ListBatchUnsubscribeResponse listBatchUnsubscribe(String listId, List<String> emails, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailJimpException;

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
  public List<InterestGrouping> listInterestGroupings(String listId) throws MailJimpException;
  
  /**
   * Add a new Interest Grouping - if interest groups for the List are not yet enabled, adding the first grouping will automatically turn them on.
   * 
   * @param listId              ID of the list
   * @param name                Name of the interest grouping
   * @param type                Type of the grouping to add
   * @param groups              List of initial group names to be added - at least one is required, and names must be unique within a grouping.  If the addition takes the total number of groups over 60, an error will be thrown.
   * 
   * @return                    The new grouping ID.
   * @throws                    If there was a problem creating the interest grouping
   */
  public Integer listInterestGroupingAdd(String listId, String name, InterestGroupingType type, List<String> groups) throws MailJimpException;
  
  /**
   * Update an existing Interest Grouping
   * 
   * @param groupingId          ID of the grouping to update
   * @param fieldNameToUpdate   The field to update - we use an enumeration so we can catch possible problems
   * @param value               The new name of the field
   * 
   * @return                    True if the request succeeds
   * @throws                    If there was a problem updating the interest grouping
   */
  public Boolean listInterestGroupingUpdate(Integer groupingId, InterestGroupingUpdateType fieldNameToUpdate, String value) throws MailJimpException;
  
  /**
   * Remove an Interest Grouping
   * 
   * @param groupingId          ID of the grouping to delete
   * @param name                Name of the interest grouping
   * @param type                Type of the grouping to add
   * @param groups              List of initial group names to be added - at least one is required, and names must be unique within a grouping.  If the addition takes the total number of groups over 60, an error will be thrown.
   * 
   * @return                    True if the request succeeds
   * @throws                    If there was a problem deleting the interest grouping
   */
  public Boolean listInterestGroupingDelete(Integer groupingId) throws MailJimpException;
  
  /**
   * Create a single Interest Grouping - if groups are not enabled, they will automatically be turned on when adding the first group.
   * 
   * @param listId              ID of the list
   * @param groupName           Name of the group to add
   * @param groupingId          (optional) ID for the new group to use - If not supplied, the first grouping on the list will be used. 
   * 
   * @return                    True if succeeded, an error otherwise.
   * @throws MailJimpException  If the interest group could not be added.
   */
  public boolean listInterestGroupAdd(String listId, String groupName, Integer groupingId) throws MailJimpException;
  
  /**
   * Delete a single Interest Grouping - if the last group for a list is deleted, this will also turn groups for the list off.
   * 
   * @param listId              ID of the list
   * @param groupName           Name of the group to delete
   * @param groupingId          The grouping to delete the group from - if not supplied, the first grouping on the list is used.
   * 
   * @return                    True if succeeded, an error otherwise.
   * @throws MailJimpException  If the interest group could not be removed.
   */
  public boolean listInterestGroupDelete(String listId, String groupName, Integer groupingId) throws MailJimpException;
  
  
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
  
  /**
  Parameters	
   * name						the name for the template - names must be unique and a max of 50 bytes
   * html						a string specifying the entire template to be created. This is NOT campaign content. They are intended to utilize our template language. * 
   * @return                    new templateId
   * @throws MailJimpException  If the template couldn't be added.
   */
  public int templateAdd(String name, String html) throws MailJimpException;


  /**
  Parameters	
   * id							the template id
   * @return                    boolean success
   * @throws MailJimpException  If the template id can't be found
   */  
  boolean templateDel(int templateId) throws MailJimpException;
  
  /**
  Parameters	
   * id							the template id
   * @return                    boolean success
   * @throws MailJimpException  If the template id can't be found   */  
  boolean templateUndel(int templateId) throws MailJimpException;

  /**
  Parameters	
   * id							the template id
   * name						the new name, null if no change
   * html						the new html, null if no change
   * @return                    boolean success
   * @throws MailJimpException  If the template id can't be found or both name and html are null.
   */  
  boolean templateUpdate(int templateId, String name, String html) throws MailJimpException;

  /**
  Parameters	
   * id							the template id
   * name						the new name, null if no change
   * html						the new html, null if no change
   * @return                    TemplateInfoResponse success
   * @throws MailJimpException  If the template id can't be found or both name and html are null.
   */
  TemplateInfoResponse templateInfo(int templateId, String type) throws MailJimpException;


TemplateListResponse templateList(int templateId, String category,
		List<NamedBoolean> types, List<NamedBoolean> inactives)
		throws MailJimpException;
  
}