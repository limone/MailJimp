package mailjimp.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mailjimp.dom.enums.EmailType;
import mailjimp.dom.enums.MemberStatus;
import mailjimp.dom.response.list.MailingList;
import mailjimp.dom.response.list.MemberInfo;
import mailjimp.dom.response.list.MemberResponseInfo;
import mailjimp.service.impl.MailJimpConstants;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-test-spring-config.xml" })
@Configurable
public class TestMailJimpJsonService extends AbstractServiceTester {
  @Autowired
  private IMailJimpService mSvc;

  @Value("${mj.test.listId}")
  private String listId;

  @Value("${mj.test.subscribedUserEMailAddress}")
  private String subscribedUserEMailAddress;
  
  /**
   * This address is subscribed, updated, then removed.
   */
  private static String randomEmailAddress = null;
  
  @BeforeClass
  public static void setup() {
    randomEmailAddress = "test" + RandomStringUtils.randomNumeric(3) + "@laccetti.com";
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
      List<MemberInfo> members = mSvc.listMemberInfo(listId, Arrays.asList(new String[] {subscribedUserEMailAddress}));
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
 // Add List Member to existing group “Sphere”

    // Create Merges with test merge field and grouping
    Map<String,Object> merges = new HashMap<String,Object>();
    merges.put(MailJimpConstants.MERGE_EMAIL, randomEmailAddress);
    merges.put(MailJimpConstants.MERGE_FNAME, "Test");
    merges.put(MailJimpConstants.MERGE_LNAME, "TestMergeVars");
    merges.put("MMERGE3", "test merge");  // This works.
    merges.put("GROUPINGS", "577");  // This doesn’t work.

    try {
      log.debug("Test list subscribe");
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
      Map<String,Object> mergeVars = new HashMap<String, Object>();
      mergeVars.put(MailJimpConstants.MERGE_FNAME, "Test");
      mergeVars.put(MailJimpConstants.MERGE_FNAME, "TestLast");
      
      log.debug("Test list update member");
      boolean response = mSvc.listUpdateMember(listId, randomEmailAddress, mergeVars, EmailType.HTML, true);
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
  
  @After
  public void after() {
    System.out.println("\n\n\n");
  }
}