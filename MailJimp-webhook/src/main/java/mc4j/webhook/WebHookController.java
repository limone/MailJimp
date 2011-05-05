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

import mc4j.service.MailChimpException;
import mc4j.service.impl.MailChimpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 09:29
 */
@Controller
public class WebHookController {

	private static Pattern DATA_PATTERN = Pattern.compile("data\\[(\\w+)\\](\\[(\\w+)\\](\\[(\\d+)\\]\\[(\\w+)\\])?)?");
	private static int INDEX_PARAM_NAME = 1;
	private static int INDEX_MERGES_NAME = 3;
	private static int INDEX_GROUP_INDEX = 5;
	private static int INDEX_GROUP_PARAM = 6;

	@Autowired
	private IWebHookAdapter webHookAdapter;

	// as our core module can be run without spring in place, we can not auto wire the parser in.
	private MailChimpParser parser = new MailChimpParser();


	@RequestMapping(params = "type=subscribe")
	public @ResponseBody String subscribe(WebRequest request) throws MailChimpException {
		webHookAdapter.userSubscribed(parser.parseListMemberInfo(parseRequest(request)));
		return "Copy that!";
	}

	// Watch out! This is going to be dirty! You have been warned.
	private Map<String, Object> parseRequest(WebRequest request) {
		Map<String,String[]> parameterMap = request.getParameterMap();
		Map<String,Object> convertible = new HashMap<String, Object>();

		for (String parameterKey : parameterMap.keySet()) {
			Matcher matcher = DATA_PATTERN.matcher(parameterKey);
			if(matcher.matches()) {
				String key = matcher.group(INDEX_PARAM_NAME);
				Object value = parameterMap.get(parameterKey)[0];

				if( null == matcher.group(INDEX_MERGES_NAME)) {
					// simple values
					convertible.put(key, value);
				} else if( null == matcher.group(INDEX_GROUP_INDEX)) {
					// mapped values
					Map<String, Object> map = getMapProperty(convertible, key);
					map.put(matcher.group(INDEX_MERGES_NAME), value );
				} else {
					// merge values in an array
					// If you like good coding don't read on!
					Map<String, Object> map = getMapProperty(convertible, key);
					// now check for the array
					String arrayKey = matcher.group(INDEX_MERGES_NAME);
					int arrayIndex = Integer.valueOf( matcher.group(INDEX_GROUP_INDEX));
					if( null == map.get(arrayKey)) {
						map.put(arrayKey, new Object[]{});
					}
					Object[] array = (Object[]) map.get(arrayKey);
					while( array.length <= arrayIndex ) {
						Object[] newArray = new Object[array.length+1];
						System.arraycopy(array, 0, newArray, 0, array.length);
						array = newArray;
					}
					if( null == array[arrayIndex]) {
						array[arrayIndex] = new HashMap<String,Object>();
					}
					map.put(arrayKey, array);
					//noinspection unchecked
					((Map<String, Object>) array[arrayIndex]).put(matcher.group(INDEX_GROUP_PARAM), value);
				}

			}
		}
		return convertible;
	}

	@SuppressWarnings({"unchecked"})
	private Map<String, Object> getMapProperty(Map<String, Object> convertible, String key) {
		if( !convertible.containsKey(key)) {
			convertible.put(key, new HashMap<String, Object>());
		}
		return (Map<String, Object>) convertible.get(key);
	}



}
