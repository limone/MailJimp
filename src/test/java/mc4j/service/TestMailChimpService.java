/**
 * Copyright 2010 Michael Laccetti
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package mc4j.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mc4j.dom.*;
import mc4j.service.impl.MailChimpService;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mc4j-test-spring-config.xml"})
@Configurable
public class TestMailChimpService {

	private static final String TEST_EMAIL_ADDRESS = "test@laccetti.com";
	private transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MailChimpService mSvc;

	@Value("${mc.test.listId}")
	private String listId;
	@Value("${mc.test.subscribedUserEMailAddress}")
	private String subscribedUserEMailAddress;

	private void processError(MailChimpException mce) {
		log.error("Exception while trying to process MailChimp call.");
		if (mce.getErrors() != null && mce.getErrors().size() > 0) {
			for (MailChimpError e : mce.getErrors()) {
				log.warn("Mail chimp error: {}", e.getError());
			}
		}
		fail();
	}

	@Test
	public void testListApiKeys() {
		try {
			List<ApiKey> content = mSvc.keyList();
			log.debug("List Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			fail();
		}
	}

	@Test
	@Ignore("This will create a new API key - so clean it up afterwords.")
	public void testKeyAdd() {
		try {
			String content = mSvc.keyAdd();
			log.debug("Add Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	@Ignore("This will disable your api key an all following tests will fail. Use with caution.")
	public void testKeyExpire() {
		try {
			Boolean content = mSvc.keyExpire();
			log.debug("Expire Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testGetLists() {
		try {
			List<MailingList> content = mSvc.getLists();
			log.debug("Lists Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testGetListMembers() {
		try {
			Map<String,Date> content = mSvc.getListMembers(listId, MemberStatus.SUBSCRIBED, null, null, null);
			log.debug("List members: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testGetListMemberInfo() {
		try {
			MemberInfo content = mSvc.getMemberInfo(listId, subscribedUserEMailAddress);
			log.debug("Members info: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testListSubscribe() {
		try {
			boolean status = mSvc.listSubscribe(listId, TEST_EMAIL_ADDRESS, new HashMap<String, Object>(),
					EmailType.HTML, false, true, true, false);
			log.debug("Subscription status: {}", status);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	/**
	 * Only works if {@link #testListSubscribe()} ran before!
	 */
	@Test
	public void testUpdateMember() {
		try {
			final HashMap<String, Object> mergeVars = new HashMap<String, Object>();
			mergeVars.put("FNAME", "Test");
			boolean status = mSvc.listUpdateMember(listId, TEST_EMAIL_ADDRESS, mergeVars,
					EmailType.HTML, true);
			log.debug("Update member info: {}", status);
			MemberInfo content = mSvc.getMemberInfo(listId, TEST_EMAIL_ADDRESS);
			log.debug("Members info: {}", content);
			assertEquals( "Test", content.getMerges().get("FNAME"));
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	public void testBatchSubscribe() throws Exception {
		try {
			final HashMap<String, Object> user1 = new HashMap<String, Object>();
			final HashMap<String, Object> user2 = new HashMap<String, Object>();
			user1.put("EMAIL", "Test1@gorilla-concept.de");
			user1.put("EMAIL_TYPE", EmailType.HTML );
			user2.put("EMAIL", "Test2 at gorilla-concept.de"); // invalid!
			user2.put("EMAIL_TYPE", EmailType.TEXT );
			final Object[] batch = new Object[]{user1, user2};

			BatchResult result = mSvc.listBatchSubscribe(listId, batch, false, true, true );
			log.debug("Batch subscribe: {}", result);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	public void testListUnsubscribe() {
		try {
			boolean status = mSvc.listUnsubscribe(listId, TEST_EMAIL_ADDRESS, true, false, false);
			log.debug("Unsubscription status: {}", status);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
}