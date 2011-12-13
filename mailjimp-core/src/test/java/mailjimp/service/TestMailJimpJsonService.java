/*
 * Copyright 2011 Michael Laccetti
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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mailjimp.dom.MailJimpConstants;
import mailjimp.dom.enums.EmailType;
import mailjimp.dom.enums.InterestGroupingType;
import mailjimp.dom.enums.InterestGroupingUpdateType;
import mailjimp.dom.enums.MemberStatus;
import mailjimp.dom.request.list.ListBatchSubscribeStruct;
import mailjimp.dom.response.list.InterestGrouping;
import mailjimp.dom.response.list.ListBatchSubscribeResponse;
import mailjimp.dom.response.list.ListBatchUnsubscribeResponse;
import mailjimp.dom.response.list.MailingList;
import mailjimp.dom.response.list.MemberInfo;
import mailjimp.dom.response.list.MemberResponseInfo;
import mailjimp.dom.security.ApiKey;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-test-spring-config.xml" })
@Configurable
public class TestMailJimpJsonService extends AbstractServiceTester {
  @Autowired
  private IMailJimpService mSvc;

  @Value("${mj.test.listId}")
  private String listId;
  
  @Value("${mj.test.groupingId}")
  private Integer groupingId;

  @Value("${mj.test.subscribedUserEMailAddress}")
  private String subscribedUserEMailAddress;

  /**
   * This address is subscribed, updated, then removed.
   */
  private static final String randomEmailAddress = "test" + RandomStringUtils.randomNumeric(3) + "@laccetti.com";
  
  /**
   * This api key is to track the one that is added, then removed
   */
  private static String generatedApiKey = null;
  
  /**
   * The ID of the interest grouping we create, so we can then remove it
   */
  private static Integer generatedInterestGroupingId = null;
  
  /**
   * These emails are batch subscribed, then batch unsubscribed
   */
  private static final List<String> batchEmails = new ArrayList<String>();
  
  /**
   * The test interest grouping group we create, update, then remove.  Comes in two flavours:
   * the original, and the "updated"
   */
  private static String groupName = RandomStringUtils.randomAlphabetic(8);
  private static String newGroupName = RandomStringUtils.randomAlphabetic(8);

  @BeforeClass
  public static void setup() {
    for (int i=0; i<5; i++) {
      batchEmails.add("test" + RandomStringUtils.randomNumeric(3) + "@laccetti.com");
    }
  }

  @After
  public void after() {
    System.out.println("\n\n\n");
  }

  @Test
  public void testLists() {
    try {
      log.debug("Testing lists");
      List<MailingList> content = mSvc.lists();
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListMembers() {
    try {
      Calendar i = Calendar.getInstance();
      i.set(Calendar.YEAR, 2009);
      i.set(Calendar.MONTH, Calendar.DECEMBER);
      i.set(Calendar.DATE, 15);
      log.debug("Testing list members");
      List<MemberResponseInfo> content = mSvc.listMembers(listId, MemberStatus.subscribed, i.getTime(), null, null);
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  /**
   * The following tests were put in place based on issue #11
   */
  @Test
  public void testListMembersAllNull() {
    try {
      log.debug("Testing list members - all null");
      List<MemberResponseInfo> content = mSvc.listMembers(listId, MemberStatus.subscribed, null, null, null);
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListMembersAllPopulated() {
    try {
      Calendar i = Calendar.getInstance();
      i.set(Calendar.YEAR, 2011);
      i.set(Calendar.MONTH, Calendar.JANUARY);
      i.set(Calendar.DATE, 1);
      log.debug("Test list members - all populated");
      List<MemberResponseInfo> content = mSvc.listMembers(listId, MemberStatus.subscribed, i.getTime(), 0, 100);
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListMembersMissingDate() {
    try {
      log.debug("Test list members - missing date");
      List<MemberResponseInfo> content = mSvc.listMembers(listId, MemberStatus.subscribed, null, 0, 100);
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListMember() {
    try {
      log.debug("Test list member");
      List<MemberInfo> members = mSvc.listMemberInfo(listId, Arrays.asList(new String[] { subscribedUserEMailAddress }));
      log.debug("Member info: {}", members);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListSubscribe() {
    try {
      log.debug("Test list subscribe");
      boolean response = mSvc.listSubscribe(listId, randomEmailAddress, null, EmailType.HTML, false, false, false, false);
      log.debug("User subscribed: {}", response);
      assertTrue("User could not subscribe to the list.", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListSubscribeMergeVars() {
    // Create Merges with test merge field and grouping
    Map<String, Object> merges = new HashMap<String, Object>();
    merges.put(MailJimpConstants.MERGE_EMAIL, randomEmailAddress);
    merges.put(MailJimpConstants.MERGE_FNAME, "Test");
    merges.put(MailJimpConstants.MERGE_LNAME, "TestMergeVars");
    merges.put("MMERGE3", "test merge");

    List<Map<Object, Object>> groupings = new ArrayList<Map<Object, Object>>();
    Map<Object, Object> groups = new HashMap<Object, Object>();
    groups.put("name", "Test Group");
    groups.put("groups", "Group 1");
    groupings.add(groups);

    merges.put("GROUPINGS", groupings);

    try {
      log.debug("Test list subscribe with merges");
      boolean response = mSvc.listSubscribe(listId, randomEmailAddress, merges, EmailType.HTML, false, true, false, true);
      log.debug("User subscribed: {}", response);
      assertTrue("User was not subscribed.", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListUpdateMember() {
    try {
      // Create Merges with test merge field and grouping
      Map<String, Object> merges = new HashMap<String, Object>();
      merges.put(MailJimpConstants.MERGE_EMAIL, subscribedUserEMailAddress);
      merges.put(MailJimpConstants.MERGE_FNAME, "Test");
      merges.put(MailJimpConstants.MERGE_LNAME, "TestMergeVars");
      merges.put("MMERGE3", "test merge");

      List<Map<Object, Object>> groupings = new ArrayList<Map<Object, Object>>();
      Map<Object, Object> groups = new HashMap<Object, Object>();
      groups.put("name", "Test Group");
      groups.put("groups", "Group 2");
      groupings.add(groups);

      merges.put("GROUPINGS", groupings);

      log.debug("Test list update member");
      boolean response = mSvc.listUpdateMember(listId, subscribedUserEMailAddress, merges, EmailType.HTML, true);
      log.debug("User updated: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListUnsubscribe() {
    try {
      log.debug("Test list unusubscribe");
      boolean response = mSvc.listUnsubscribe(listId, randomEmailAddress, false, true, true);
      log.debug("User unsubscribed: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  
  @Test
  @Ignore
  public void testApiKeys() {
    try {
      log.debug("Test api keys");
      List<ApiKey> keys = mSvc.apikeys(true);
      log.debug("API keys: {}", keys);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  @Ignore
  public void testApiKeyAdd() {
    try {
      log.debug("Test API key add");
      generatedApiKey = mSvc.apikeyAdd();
      log.debug("API key: {}", generatedApiKey);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  @Ignore
  public void testApiKeyExpire() {
    assertNotNull("API key was null.", generatedApiKey);
    try {
      log.debug("Test API key expire");
      boolean status = mSvc.apikeyExpire(generatedApiKey);
      log.debug("API key expire success: {}", status);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupings() {
    try {
      log.debug("Test list interest groupings");
      List<InterestGrouping> response = mSvc.listInterestGroupings(listId);
      log.debug("Interest groupings: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupingsAdd() {
    try {
      log.debug("Test list interest groupings add");
      generatedInterestGroupingId = mSvc.listInterestGroupingAdd(listId, RandomStringUtils.randomAlphabetic(10), InterestGroupingType.hidden, Arrays.asList(new String[]{"test 1", "test 2", "test 3"}));
      log.debug("Interest groupings add: {}", generatedInterestGroupingId);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupingsUpdate() {
    try {
      log.debug("Test list interest groupings update");
      Boolean success = mSvc.listInterestGroupingUpdate(generatedInterestGroupingId, InterestGroupingUpdateType.name, RandomStringUtils.randomAlphabetic(10));
      log.debug("Interest groupings update: {}", success);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupingsDelete() {
    try {
      log.debug("Test list interest groupings delete");
      Boolean success = mSvc.listInterestGroupingDelete(generatedInterestGroupingId);
      log.debug("Interest groupings delete: {}", success);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListBatchSubscribe() {
    try {
      log.debug("Test list batch subscribe");
      List<ListBatchSubscribeStruct> batch = new ArrayList<ListBatchSubscribeStruct>(batchEmails.size());
      for (String email : batchEmails) {
        batch.add(new ListBatchSubscribeStruct(email, EmailType.TEXT));
      }
      
      ListBatchSubscribeResponse response = mSvc.listBatchSubscribe(listId, batch, false, true, true);
      log.debug("List batch subscribe: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListBatchUnsubscribe() {
    try {
      log.debug("Test list batch unsubscribe");
      ListBatchUnsubscribeResponse response = mSvc.listBatchUnsubscribe(listId, batchEmails, true, false, false);
      log.debug("List batch unsubscribe: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupAdd() {
    try {
      log.debug("Test list interest group add");
      Boolean response = mSvc.listInterestGroupAdd(listId, groupName, groupingId);
      log.debug("List interest group add: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupUpdate() {
    try {
      log.debug("Test list interest group update");
      Boolean response = mSvc.listInterestGroupUpdate(listId, groupName, newGroupName);
      log.debug("List interest group update: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListInterestGroupDelete() {
    try {
      log.debug("Test list interest group delete");
      Boolean response = mSvc.listInterestGroupDelete(listId, newGroupName, groupingId);
      log.debug("List interest group delete: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
}