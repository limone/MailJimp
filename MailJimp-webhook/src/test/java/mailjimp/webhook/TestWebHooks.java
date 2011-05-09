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
package mailjimp.webhook;

import mailjimp.dom.WebHookData;
import mailjimp.dom.WebHookType;
import mailjimp.dom.list.MemberInfo;
import mailjimp.service.impl.MailChimpConstants;
import mailjimp.webhook.WebHookConstants;
import mailjimp.webhook.WebHookController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.junit.Assert.*;

/**
 * Author: Eike Hirsch (me at eike-hirsch dot net) Date: 03.05.11 Time: 13:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-webhook-test-spring-config.xml" })
public class TestWebHooks {
  @Autowired
  private WebHookController              controller;
  @Autowired
  private MyTestWebHookAdapter           webHookAdapter;
  @Autowired
  private AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter;
  private MockHttpServletResponse        response;
  private MockHttpServletRequest         request;
  private WebHookData                    data;

  @Before
  public void initTestWebHooks() throws Exception {
    // reset the adapter
    webHookAdapter.resetCalled();
    response = new MockHttpServletResponse();
    request = new MockHttpServletRequest(RequestMethod.POST.name(), "/");
    request.addParameter("fired_at", "2011-05-06 13:55:32");
    // reset data
    data = null;
  }

  @Test
  public void subscribe() throws Exception {
    request.addParameter("type", "subscribe");
    addMemberInfo();
    assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));
    assertEquals("Copy that!", response.getContentAsString());
    assertTrue(webHookAdapter.wasCalled("userSubscribed"));
    data = webHookAdapter.getData();
    assertEquals(WebHookType.SUBSCRIBE, data.getType());
    assertFiredAt(data);
    assertMemberInfo(data);
  }

  private void assertFiredAt(WebHookData data) {
    assertEquals("2011-05-06 13:55:32", MailChimpConstants.SDF.format(data.getFiredAt()));
  }

  private void assertMemberInfo(WebHookData data) {
    MemberInfo memberInfo = data.getMemberInfo();
    assertNotNull(memberInfo);
    assertEquals("me@eike-hirsch.net", memberInfo.getEmail());
    assertEquals("Eike", memberInfo.getMerges().get(MailChimpConstants.MERGE_FNAME));
    assertEquals("VALUE1, VALUE2", memberInfo.getGroupings()[0].getGroups());
  }

  @Test
  public void unsubscribe() throws Exception {
    request.addParameter("type", "unsubscribe");
    addMemberInfo();
    assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));
    assertEquals("Ten four!", response.getContentAsString());
    assertTrue(webHookAdapter.wasCalled("userUnsubscribed"));
    data = webHookAdapter.getData();
    assertFiredAt(data);
    assertMemberInfo(data);
  }

  @Test
  public void profile() throws Exception {
    request.addParameter("type", "profile");
    addMemberInfo();
    assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));
    assertEquals("Roger that!", response.getContentAsString());
    assertTrue(webHookAdapter.wasCalled("profileUpdated"));
    data = webHookAdapter.getData();
    assertFiredAt(data);
    assertMemberInfo(data);
  }

  private void addMemberInfo() {
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
  }

  @Test
  public void updateEmail() throws Exception {
    request.addParameter("type", "upemail");
    request.addParameter("data[" + WebHookConstants.OLD_EMAIL + "]", "me@eike-hirsch.net");
    request.addParameter("data[" + WebHookConstants.NEW_EMAIL + "]", "me@new_comp.de");
    assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));
    assertEquals("Understood!", response.getContentAsString());
    assertTrue(webHookAdapter.wasCalled("eMailUpdated"));
    data = webHookAdapter.getData();
    assertEquals("me@new_comp.de", data.getRawData().get(WebHookConstants.NEW_EMAIL));
  }

  @Test
  public void cleaned() throws Exception {
    request.addParameter("type", "cleaned");
    request.addParameter("data[reason]", "hard");
    request.addParameter("data[email]", "me@new_comp.de");
    assertNull(annotationMethodHandlerAdapter.handle(request, response, controller));
    assertEquals("OK!", response.getContentAsString());
    assertTrue(webHookAdapter.wasCalled("cleaned"));
    data = webHookAdapter.getData();
    assertEquals("hard", data.getRawData().get(WebHookConstants.REASON));
  }
}
