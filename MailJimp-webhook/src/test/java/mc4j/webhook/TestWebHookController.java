package mc4j.webhook;

import mc4j.dom.MemberInfo;
import mc4j.webhook.IWebHookAdapter;
import mc4j.webhook.WebHookController;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

/**
 * @author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 12:28
 */
public class TestWebHookController extends EasyMockSupport {

	private WebHookController controller;

	private IWebHookAdapter adapter;

	@Before
	public void initTestWebHookController() throws Exception {
		controller = new WebHookController();
		adapter = createMock(IWebHookAdapter.class);

		// auto wire "magic" ;)
		Field f = controller.getClass().getDeclaredField("webHookAdapter");
		f.setAccessible(true);
		f.set(controller, adapter);
		f.setAccessible(false);
	}

	@Test
	public void subscribeCallsUserSubscribed() throws Exception {
		MemberInfo mi = new MemberInfo();
		adapter.userSubscribed(mi);
		expectLastCall();
		replayAll();

		// now call the controller.
//		assertEquals("Copy that!", controller.subscribe(request));

		// check if the adapter was called
		verifyAll();
	}




}
