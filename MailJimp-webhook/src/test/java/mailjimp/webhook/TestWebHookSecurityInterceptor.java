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

import mailjimp.webhook.WebHookSecurityInterceptor;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

/**
 * @author Eike Hirsch (me at eike-hirsch dot net) Date: 07.05.11 Time: 22:51
 */
public class TestWebHookSecurityInterceptor {
  @Test
  public void testPreHandle() throws Exception {
    MyTestWebHookAdapter adapter = new MyTestWebHookAdapter();
    WebHookSecurityInterceptor interceptor = new WebHookSecurityInterceptor();
    // set the adapter like spring would do.
    Field field = WebHookSecurityInterceptor.class.getDeclaredField("webHookAdapter");
    field.setAccessible(true);
    field.set(interceptor, adapter);
    field.setAccessible(false);
    // call the interceptor.
    interceptor.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), null);
    // all we have to check is if the adapter was called.
    assertTrue(adapter.wasCalled("isValidRequest"));
  }
}