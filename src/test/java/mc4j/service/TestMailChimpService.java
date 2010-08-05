package mc4j.service;

import static org.junit.Assert.*;

import mc4j.service.impl.MailChimpService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml"})
public class TestMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MailChimpService mSvc;
		
	@Test
	public void testListApiKeys() {
		String content = mSvc.keyList();
		assertNotNull("No content", content);
		log.debug("List Content: {}", content);
	}
	
	@Test
	public void testKeyAdd() {
		String content = mSvc.keyAdd();
		assertNotNull("No Content", content);
		log.debug("Add Content: {}", content);
	}
	
	@Test
	public void testKeyExpire() {
		Boolean content = mSvc.keyExpire();
		assertNotNull("No Content", content);
		log.debug("Expire Content: {}", content);
	}
}