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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mailjimp.dom.WebHookData;
import mailjimp.webhook.IWebHookAdapter;

import org.springframework.stereotype.Service;

/**
 * Author: Eike Hirsch (me at eike-hirsch dot net) Date: 03.05.11 Time: 14:03
 */
@SuppressWarnings("serial")
@Service
public class MyTestWebHookAdapter implements IWebHookAdapter {
  private List<String> called = new ArrayList<String>();
  private WebHookData  data;

  public boolean wasCalled(String methodName) {
    return called.contains(methodName);
  }

  public void resetCalled() {
    called.clear();
  }

  @Override
  public boolean isValidRequest(HttpServletRequest request) {
    called.add("isValidRequest");
    return true;
  }

  @Override
  public void userSubscribed(WebHookData data) {
    called.add("userSubscribed");
    this.data = data;
  }

  @Override
  public void userUnsubscribed(WebHookData data) {
    called.add("userUnsubscribed");
    this.data = data;
  }

  @Override
  public void profileUpdated(WebHookData data) {
    called.add("profileUpdated");
    this.data = data;
  }

  @Override
  public void eMailUpdated(WebHookData data) {
    called.add("eMailUpdated");
    this.data = data;
  }

  @Override
  public void cleaned(WebHookData data) {
    called.add("cleaned");
    this.data = data;
  }

  public WebHookData getData() {
    return data;
  }
}