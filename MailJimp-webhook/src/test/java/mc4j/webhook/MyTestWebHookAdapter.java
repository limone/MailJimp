/*
 * Copyright 2011 Eike Hirsch
 *
 * This file is part of MailJimp.
 *
 * MailJimp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * MailJimp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MailJimp.  If not, see <http://www.gnu.org/licenses/>.
 */

package mc4j.webhook;

import mc4j.dom.list.MemberInfo;
import mc4j.service.impl.MailChimpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 14:03
 */
@Service
public class MyTestWebHookAdapter implements IWebHookAdapter {

	private static final Logger LOG = LoggerFactory.getLogger( MyTestWebHookAdapter.class );


	@Override
	public boolean isValidRequest(HttpServletRequest request) {
		return true;
	}

	@Override
	public void userSubscribed(MemberInfo memberInfo) {
		assertNotNull(memberInfo);

		assertEquals("me@eike-hirsch.net", memberInfo.getEmail());
		assertEquals("Eike", memberInfo.getMerges().get(MailChimpConstants.FNAME));
		assertEquals("VALUE1, VALUE2", memberInfo.getGroupings()[0].getGroups());
	}
}
