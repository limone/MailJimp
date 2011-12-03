package mailjimp.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import mailjimp.dom.enums.EmailType;
import mailjimp.dom.enums.MemberStatus;
import mailjimp.dom.response.list.MailingList;
import mailjimp.dom.response.list.MemberInfo;
import mailjimp.dom.response.list.MemberResponseInfo;

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
      List<MemberResponseInfo> content = mSvc.listMembers(listId, MemberStatus.subscribed, i.getTime(), null, null);
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }

  @Test
  public void testListMember() {
    try {
      List<MemberInfo> members = mSvc.listMemberInfo(listId, Arrays.asList(new String[] {TEST_EMAIL_ADDRESS}));
      log.debug("Member info: {}", members);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
  
  @Test
  public void testListSubscribe() {
    try {
      boolean response = mSvc.listSubscribe(listId, "subtest@laccetti.com", null, EmailType.HTML, false, true, false, true);
      log.debug("User subscribed: {}", response);
    } catch (MailJimpException mje) {
      processError(mje);
    }
  }
}