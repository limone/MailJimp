package mc4j.service.impl;

import static org.junit.Assert.*;

import mc4j.service.MailChimpException;
import org.junit.Test;

/**
 * User: Eike Hirsch
 * Date: 27.04.11
 * Time: 06:38
 */
public class MailChimpParserTest {

	private MailChimpParser parser = new MailChimpParser();

	@Test
	public void testParseListSubscribeNormalFlow() throws Exception {
		assertTrue( parser.parseListSubscribe(new Boolean(true)));
		assertFalse(parser.parseListSubscribe(new Boolean(false)));
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
		assertTrue( parser.parseListUnsubscribe(new Boolean(true)));
		assertFalse(parser.parseListUnsubscribe(new Boolean(false)));
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
		assertTrue( parser.parseListUpdateMember(new Boolean(true)));
		assertFalse(parser.parseListUpdateMember(new Boolean(false)));
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
