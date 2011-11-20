package mailjimp.service;

import static org.junit.Assert.fail;
import mailjimp.dom.MailJimpError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractServiceTester {
  protected final Logger log = LoggerFactory.getLogger(getClass());
  
  protected void processError(MailJimpException mce) {
    log.error("Exception while trying to process MailChimp call.", mce);
    if (mce.getErrors() != null && mce.getErrors().size() > 0) {
      for (MailJimpError e : mce.getErrors()) {
        log.warn("Mail chimp error: {}", e.getError());
      }
    }
    fail();
  }
}