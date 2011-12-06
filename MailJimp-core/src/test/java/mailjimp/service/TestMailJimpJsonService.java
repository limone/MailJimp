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

import org.junit.After;
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

  @Value("${mj.test.subscribedUserEMailAddress}")
  private String subscribedUserEMailAddress;

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
      List<MemberInfo> members = mSvc.listMemberInfo(listId, Arrays.asList(new String[] {TEST_EMAIL_ADDRESS}));
      log.debug("Member info: {}", members);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListSubscribe() {
    try {
      log.debug("Test list subscribe");
      boolean response = mSvc.listSubscribe(listId, "subtest@laccetti.com", null, EmailType.HTML, false, true, false, true);
      log.debug("User subscribed: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListUpdateMember() {
    try {
      Map<String,Object> mergeVars = new HashMap<String, Object>();
      mergeVars.put("FNAME", "Test");
      mergeVars.put("LNAME", "TestLast");
      
      log.debug("Test list update member");
      boolean response = mSvc.listUpdateMember(listId, TEST_EMAIL_ADDRESS, mergeVars, EmailType.HTML, true);
      log.debug("User updated: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListUnsubscribe() {
    try {
      log.debug("Test list unusubscribe");
      boolean response = mSvc.listUnsubscribe(listId, "subtest@laccetti.com", false, true, true);
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