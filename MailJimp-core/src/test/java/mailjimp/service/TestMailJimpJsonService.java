package mailjimp.service;

import java.util.List;

import mailjimp.dom.list.MailingList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-test-spring-config.xml" })
@Configurable
public class TestMailJimpJsonService extends AbstractServiceTester {
  private static final String    TEST_EMAIL_ADDRESS = "test@laccetti.com";
  
  @Autowired
  @Qualifier("json")
  private IMailJimpService       mSvc;
  
  @Value("${mj.test.listId}")
  private String                 listId;
  
  @Value("${mj.test.subscribedUserEMailAddress}")
  private String                 subscribedUserEMailAddress;
  
  @Test
  public void testGetLists() {
    try {
      List<MailingList> content = mSvc.getLists();
      log.debug("Lists Content: {}", content);
    } catch (MailJimpException mce) {
      processError(mce);
    }
  }
}