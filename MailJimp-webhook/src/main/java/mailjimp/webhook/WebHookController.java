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
import mailjimp.service.MailChimpException;
import mailjimp.service.impl.MailChimpConstants;
import mailjimp.service.impl.MailChimpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Central entry point for all incoming MailChimp callbacks.
 *
 * The controller will simply parse the request as good as it can and dispatch
 * the call to the corresponding method of the {@link IWebHookAdapter}.
 *
 * Author: Eike Hirsch (me at eike-hirsch dot net) Date: 03.05.11 Time: 09:29
 */
@Controller
public class WebHookController {

  private static Pattern  DATA_PATTERN
		  = Pattern.compile("data\\[(\\w+)\\](\\[(\\w+)\\](\\[(\\d+)\\]\\[(\\w+)\\])?)?");

  private static int      INDEX_PARAM_NAME              = 1;
  private static int      INDEX_MAPPED_PARAM_NAME       = 3;
  private static int      INDEX_MAPPED_ARRAY_INDEX      = 5;
  private static int      INDEX_MAPPED_ARRAY_PARAM_NAME = 6;

  @Autowired
  private IWebHookAdapter webHookAdapter;
  // as our core module can be run without spring in place, we can not auto wire
  // the parser.
  private MailChimpParser parser                        = new MailChimpParser();

  /**
   * WebHook for the subscribe callbacks. This will call
   * {@link IWebHookAdapter#userSubscribed(mailjimp.dom.WebHookData)}.
   *
   * @param request
   *          The request containing all the data.
   *
   * @return A simple string MailChimp doesn't care what the answer looks like
   *         as long as it's a 2xx status code.
   *
   * @throws Exception
   *           if parsing fails.
   */
  @RequestMapping(params = "type=subscribe")
  public @ResponseBody
  String subscribe(WebRequest request) throws Exception {
    WebHookData data = new WebHookData(WebHookType.SUBSCRIBE);
    webHookAdapter.userSubscribed(buildData(request, data));
    return "Copy that!";
  }

  /**
   * WebHook for the unsubscribe callbacks. This will call
   * {@link IWebHookAdapter#userUnsubscribed(mailjimp.dom.WebHookData)}.
   *
   * @param request
   *          The request containing all the data.
   *
   * @return A simple string MailChimp doesn't care what the answer looks like
   *         as long as it's a 2xx status code.
   *
   * @throws Exception
   *           if parsing fails.
   */
  @RequestMapping(params = "type=unsubscribe")
  public @ResponseBody
  String unsubscribe(WebRequest request) throws Exception {
    WebHookData data = new WebHookData(WebHookType.UNSUBSCRIBE);
    webHookAdapter.userUnsubscribed(buildData(request, data));
    return "Ten four!";
  }

  /**
   * WebHook for the subscribe callbacks. This will call
   * {@link IWebHookAdapter#userSubscribed(mailjimp.dom.WebHookData)}.
   *
   * @param request
   *          The request containing all the data.
   *
   * @return A simple string MailChimp doesn't care what the answer looks like
   *         as long as it's a 2xx status code.
   *
   * @throws Exception
   *           if parsing fails.
   */
  @RequestMapping(params = "type=profile")
  public @ResponseBody
  String profile(WebRequest request) throws Exception {
    WebHookData data = new WebHookData(WebHookType.UPDATE_PROFILE);
    webHookAdapter.profileUpdated(buildData(request, data));
    return "Roger that!";
  }

  /**
   * WebHook for the subscribe callbacks. This will call
   * {@link IWebHookAdapter#userSubscribed(mailjimp.dom.WebHookData)}.
   *
   * @param request
   *          The request containing all the data.
   *
   * @return A simple string MailChimp doesn't care what the answer looks like
   *         as long as it's a 2xx status code.
   *
   * @throws Exception
   *           if parsing fails.
   */
  @RequestMapping(params = "type=upemail")
  public @ResponseBody
  String upemail(WebRequest request) throws Exception {
    WebHookData data = new WebHookData(WebHookType.UPDATE_EMAIL);
    webHookAdapter.eMailUpdated(buildData(request, data));
    return "Understood!";
  }

  /**
   * WebHook for the subscribe callbacks. This will call
   * {@link IWebHookAdapter#userSubscribed(mailjimp.dom.WebHookData)}.
   *
   * @param request
   *          The request containing all the data.
   *
   * @return A simple string MailChimp doesn't care what the answer looks like
   *         as long as it's a 2xx status code.
   *
   * @throws Exception
   *           if parsing fails.
   */
  @RequestMapping(params = "type=cleaned")
  public @ResponseBody
  String cleaned(WebRequest request) throws Exception {
    WebHookData data = new WebHookData(WebHookType.CLEANED);
    webHookAdapter.cleaned(buildData(request, data));
    return "OK!";
  }

  private WebHookData buildData(WebRequest request, final WebHookData data) throws ParseException, MailChimpException {
    data.setFiredAt(MailChimpConstants.SDF.parse(request.getParameter("fired_at")));
    data.setRawData(parseRequest(request));
    if (WebHookType.SUBSCRIBE == data.getType() || WebHookType.UNSUBSCRIBE == data.getType() || WebHookType.UPDATE_PROFILE == data.getType()) {
      data.setMemberInfo(parser.parseListMemberInfo(data.getRawData()));
    }
    return data;
  }

  // Watch out! This is going to be dirty! You have been warned.
  @SuppressWarnings("unchecked")
  private Map<String, Object> parseRequest(WebRequest request) {
    Map<String, String[]> parameterMap = request.getParameterMap();
    Map<String, Object> convertible = new HashMap<String, Object>();
    for (String parameterKey : parameterMap.keySet()) {
      Matcher matcher = DATA_PATTERN.matcher(parameterKey);
      if (matcher.matches()) {
        String key = matcher.group(INDEX_PARAM_NAME);
        Object value = parameterMap.get(parameterKey)[0];
        if (null == matcher.group(INDEX_MAPPED_PARAM_NAME)) {
          // simple values
          convertible.put(key, value);
        } else if (null == matcher.group(INDEX_MAPPED_ARRAY_INDEX)) {
          // mapped values
          Map<String, Object> map = getMapParam(convertible, key);
          map.put(matcher.group(INDEX_MAPPED_PARAM_NAME), value);
        } else {
          // merge values in an array
          // If you like good coding don't read on!
          Map<String, Object> map = getMapParam(convertible, key);
          // now check for the array
          String arrayKey = matcher.group(INDEX_MAPPED_PARAM_NAME);
          int arrayIndex = Integer.valueOf(matcher.group(INDEX_MAPPED_ARRAY_INDEX));
          if (null == map.get(arrayKey)) {
            map.put(arrayKey, new Object[] {});
          }
          Object[] array = (Object[]) map.get(arrayKey);
          while (array.length <= arrayIndex) {
            Object[] newArray = new Object[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
          }
          if (null == array[arrayIndex]) {
            array[arrayIndex] = new HashMap<String, Object>();
          }
          map.put(arrayKey, array);
          // noinspection unchecked
          ((Map<String, Object>) array[arrayIndex]).put(matcher.group(INDEX_MAPPED_ARRAY_PARAM_NAME), value);
        }
      }
    }
    return convertible;
  }

  @SuppressWarnings({ "unchecked" })
  private Map<String, Object> getMapParam(Map<String, Object> convertible, String key) {
    if (!convertible.containsKey(key)) {
      convertible.put(key, new HashMap<String, Object>());
    }
    return (Map<String, Object>) convertible.get(key);
  }
}