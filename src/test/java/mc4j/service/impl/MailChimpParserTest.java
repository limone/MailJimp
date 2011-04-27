package mc4j.service.impl;
/*
 * Copyright 2011 Eike Hirsch
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

import static org.junit.Assert.*;

import mc4j.service.MailChimpException;
import org.junit.Test;

/**
 *
 * Eike Hirsch (me at eike-hirsch dot net)
 * Date: 27.04.11
 * Time: 06:38
 */
public class MailChimpParserTest {

	private MailChimpParser parser = new MailChimpParser();

	@Test
	public void testParseListSubscribeNormalFlow() throws Exception {
		assertTrue( parser.parseListSubscribe(true));
		assertFalse(parser.parseListSubscribe(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListSubscribeFailure() throws Exception {
	    parser.parseListSubscribe("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListSubscribeCanHandleNull() throws Exception {
	    parser.parseListSubscribe(null);
	}

	@Test
	public void testParseListUnsubscribeNormalFlow() throws Exception {
		assertTrue( parser.parseListUnsubscribe(true));
		assertFalse(parser.parseListUnsubscribe(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListUnsubscribeFailure() throws Exception {
		parser.parseListUnsubscribe("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListUnsubscribeCanHandleNull() throws Exception {
		parser.parseListUnsubscribe(null);
	}

	@Test
	public void testParseListUpdateMemberNormalFlow() throws Exception {
		assertTrue( parser.parseListUpdateMember(true));
		assertFalse(parser.parseListUpdateMember(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListUpdateMemberFailure() throws Exception {
		parser.parseListUpdateMember("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListUpdateMemberCanHandleNull() throws Exception {
		parser.parseListUpdateMember(null);
	}


}
