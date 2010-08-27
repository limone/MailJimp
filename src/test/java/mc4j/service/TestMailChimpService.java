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

import mc4j.dom.ApiKey;
import mc4j.dom.EmailType;
import mc4j.dom.MailChimpError;
import mc4j.dom.MailingList;
import mc4j.dom.MemberInfo;
import mc4j.dom.MemberStatus;
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
	
	private void processError(MailChimpException mce) {
		log.error("Exception while trying to process MailChimp call.");
		if (mce.getErrors() != null && mce.getErrors().size() > 0) {
			for (MailChimpError e : mce.getErrors()) {
				log.warn("Mail chimp error: {}", e.getError());
			}
		}
	}

	@Test
	@Ignore
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
	
	@Test
	@Ignore
	public void testGetLists() {
		try {
			List<MailingList> content = mSvc.getLists();
			log.debug("Lists Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	@Ignore
	public void testGetListMembers() {
		try {
			Map<String,Date> content = mSvc.getListMembers("b0308c77a5", MemberStatus.SUBSCRIBED, null, null, null);
			log.debug("List members: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testGetListMemberInfo() {
		try {
			MemberInfo content = mSvc.getMemberInfo("b0308c77a5", "michael@laccetti.com");
			log.debug("Members info: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testListSubscribe() {
		try {
			boolean status = mSvc.listSubscribe("b0308c77a5", "test@laccetti.com", new HashMap<String,Object>(), EmailType.HTML, true, false, true, false);
			log.debug("Subscription status: {}", status);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
	
	@Test
	public void testListUnsubscribe() {
		try {
			boolean status = mSvc.listUnsubscribe("b0308c77a5", "test@laccetti.com", true, false, true);
			log.debug("Unsubscription status: {}", status);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}
}