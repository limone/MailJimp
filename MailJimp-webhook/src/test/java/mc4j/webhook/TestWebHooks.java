package mc4j.webhook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 13:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mc4j-webhook-test-spring-config.xml"})
public class TestWebHooks {

	@Autowired
	private WebHookController controller;

	@Autowired
	private AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter;

	@Test
	public void subscribe() throws Exception {
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest request = new MockHttpServletRequest(RequestMethod.POST.name(), "/");

		request.addParameter("type", "subscribe");

		request.addParameter("data[email]", "me@eike-hirsch.net");
		request.addParameter("data[email_type]", "html");
		request.addParameter("data[id]", "3456c799ddj");
		request.addParameter("data[list_id]", "someId");
		request.addParameter("data[ip_opt]", "10.20.30.40");
		request.addParameter("data[web_id]", "123456789");
		request.addParameter("data[merges][EMAIL]", "me@eike-hirsch.net");
		request.addParameter("data[merges][FNAME]", "Eike");
		request.addParameter("data[merges][LNAME]", "Hirsch");
		request.addParameter("data[merges][GROUPINGS][0][groups]", "VALUE1, VALUE2");
		request.addParameter("data[merges][GROUPINGS][0][id]", "5");
		request.addParameter("data[merges][GROUPINGS][0][name]", "GROUP NAME");
		request.addParameter("data[merges][INTERESTS]", "VALUE1, VALUE2");


		assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));

		assertEquals("Copy that!", response.getContentAsString());
	}
}