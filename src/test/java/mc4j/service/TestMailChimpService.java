package mc4j.service;

import java.util.List;

import mc4j.dom.ApiKey;
import mc4j.dom.MailChimpError;
import mc4j.service.impl.MailChimpService;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-config.xml" })
public class TestMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MailChimpService mSvc;

	@Test
	public void testListApiKeys() {
		try {
			List<ApiKey> content = mSvc.keyList();
			log.debug("List Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Test
	@Ignore
	public void testKeyAdd() {
		try {
			String content = mSvc.keyAdd();
			log.debug("Add Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	@Ignore
	public void testKeyExpire() {
		try {
			Boolean content = mSvc.keyExpire();
			log.debug("Expire Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	private void processError(MailChimpException mce) {
		log.error("Exception while trying to process MailChimp call.");
		if (mce.getErrors() != null && mce.getErrors().size() > 0) {
			for (MailChimpError e : mce.getErrors()) {
				log.warn("Mail chimp error: {}", e.getError());
			}
		}
	}
}