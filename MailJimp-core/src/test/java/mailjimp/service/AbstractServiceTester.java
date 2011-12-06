package mailjimp.service;

import static org.junit.Assert.fail;
import mailjimp.dom.MailJimpError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractServiceTester {
  protected final Logger log = LoggerFactory.getLogger(getClass());
  
  protected void processError(MailJimpException mje) {
    log.error("Exception while trying to process MailJimp call.", mje);
    if (mje.getErrors() != null && mje.getErrors().size() > 0) {
      for (MailJimpError e : mje.getErrors()) {
        log.warn("Mail chimp error: {}", e.getError());
      }
    }
    fail();
  }
}