/**
 * Copyright 2010 Michael Laccetti
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
package mc4j.service.impl;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mc4j.dom.ApiKey;
import mc4j.dom.MailingList;
import mc4j.dom.MemberInfo;
import mc4j.service.MailChimpException;
import mc4j.service.UnexpectedMailChimpResponseException;

public class MailChimpParser {

	private <T> void setVars(Map<String, Object> results, T obj) throws Exception {
		Pattern p = Pattern.compile("set(\\w+)");
		for (Method m : obj.getClass().getMethods()) {
			if (m.getName().startsWith("set") && m.getParameterTypes().length == 1) {
				Matcher matcher = p.matcher(m.getName());
				if (matcher.matches()) {
					String key = matcher.group(1);
					key = convert(key);
					Object value = results.get(key);
					if (value == null) {
						m.invoke(obj, value);
					} else {
						Class<?> param = m.getParameterTypes()[0];
						if (param.isAssignableFrom(value.getClass()) ||
								Boolean.class.isAssignableFrom(value.getClass())) {
							m.invoke(obj, value);
						} else {
							m.invoke(obj, convert(param, value));
						}
					}
				}
			}
		}
	}

	private String convert(String name) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c) && i > 0) {
				sb.append("_").append(Character.toLowerCase(c));
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}
		String result = sb.toString();
		if (result.equalsIgnoreCase("api_key")) {
			result = "apikey";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T,Z> T convert(Class<T> expected, Object value) throws MailChimpException {
		if (value == null) { return null; }

		if (expected.isEnum()) {
			try {
				for (T obj : expected.getEnumConstants()) {
					if (obj.toString().equalsIgnoreCase((String) value)) { return obj; }
				}
				throw new MailChimpException(String.format("No enum constant found for value %s in enum %s.",
						value.toString(), expected.getSimpleName()));
			} catch (Exception ex) {
				throw new MailChimpException("Could not parse Enum.", ex);
			}
		}

		if (expected.equals(Date.class) && value.getClass().equals(String.class)) {
			try {
				String s = (String) value;
				if (s.length() == 0) { return null; }
				return (T) MailChimpConstants.sdf.parse((String) value);
			} catch (ParseException pe) {
				throw new MailChimpException("Could not convert date.", pe);
			}
		}

		if (expected.equals(Double.class)) {
			if (value.getClass().equals(Integer.class)) {
				return (T) Double.valueOf(((Integer) value).toString());
			} else if (value.getClass().equals(String.class)) { return (T) Double.valueOf((String) value); }
		}

		if (expected.equals(Integer.class)) {
			if (value.getClass().equals(Double.class)) { return (T) Integer.valueOf(((Double) value).intValue()); }
		}
		
		if (expected.isAssignableFrom(List.class)) {
			if (value.getClass().isArray()) {
				return null;
			} else if (value.getClass().isAssignableFrom(Map.class)) {
				try {
					T obj = expected.newInstance();
					setVars((Map<String,Object>)value, obj);
				} catch (Exception ex) {
					throw new MailChimpException("Could not process nested collection.", ex);
				}
			}
			throw new MailChimpException("We expected a list, but the inbound element was not an array.");
		}

		throw new IllegalArgumentException(String.format("Could not convert from %s to %s.", value.getClass().getSimpleName(), expected.getSimpleName()));
	}


	public List<ApiKey> parseApiKeys(Object results) throws MailChimpException {
		List<ApiKey> keys = new ArrayList<ApiKey>();
		if (results instanceof Object[]) {
			for (Object o : (Object[]) results) {
				if (o instanceof Map<?, ?>) {
					@SuppressWarnings("unchecked")
					Map<String, Object> m = (Map<String, Object>) o;
					ApiKey ak = new ApiKey();
					try {
						setVars(m, ak);
					} catch (Exception ex) {
						throw new MailChimpException("Could not set fields.", ex);
					}
					keys.add(ak);
				}
			}
		}
		return keys;
	}

	public String createApiKey(Object results) throws MailChimpException {
		if (results instanceof String) { return (String) results; }
		throw new MailChimpException(String.format("Result was an unxpected type: %s.", results.getClass().getName()));
	}

	public Boolean expireApiKey(Object results) throws MailChimpException {
		if (results instanceof Boolean) { return (Boolean) results; }
		throw new MailChimpException(String.format("Result was an unxpected type: %s.", results.getClass().getName()));
	}

	/**
	 * This will parse all list information of the current account.
	 *
	 * @param results This is the Object created out of the xml-rpc-call to the MailChimp API.
	 *
	 * @return A list containing {@link MailingList MailingLists}.
	 *
	 * @throws MailChimpException If parsing goes wrong
	 */
	public List<MailingList> parseLists(Object results) throws MailChimpException {
		List<MailingList> lists;
		if (results instanceof Object[]) { // api version 1.2
			lists = new ArrayList<MailingList>();
			_parseLists((Object[]) results, lists);
		} else if (Map.class.isAssignableFrom( results.getClass() )) { // api version 1.3
			@SuppressWarnings("unchecked")
			Map<String, Object> r = (Map<String, Object>) results;
			lists = new ArrayList<MailingList>((Integer) r.get("total"));
			_parseLists((Object[]) r.get("data"), lists);
		} else {
			throw new UnexpectedMailChimpResponseException("Unsupported api version?");
		}
		return lists;
	}

	private void _parseLists(Object[] results, List<MailingList> lists) throws MailChimpException {
		for (Object o : results) {
			if (o instanceof Map<?, ?>) {
				@SuppressWarnings("unchecked")
				Map<String, Object> m = (Map<String, Object>) o;
				MailingList ml = new MailingList();
				try {
					setVars(m, ml);
					lists.add(ml);
				} catch (Exception ex) {
					throw new MailChimpException("Could not set fields.", ex);
				}
			}
		}
	}

	/**
	 * This will parse all members of a list.
	 *
	 * @param results This is the Object created out of the xml-rpc-call to the MailChimp API.
	 *
	 * @return A map containing e-mail-addresses as keys and subscription dates as values.
	 *
	 * @throws MailChimpException If parsing goes wrong.
	 */
	public Map<String, Date> parseListMembers(Object results) throws MailChimpException {

		Map<String, Date> members;

		if (results instanceof Object[]) {

			// api version 1.2
			members = new HashMap<String, Date>();
			_parseListMembers((Object[]) results, members);

		} else if (Map.class.isAssignableFrom( results.getClass() )) {

			// api version 1.3
			@SuppressWarnings("unchecked")
			Map<String, Object> r = (Map<String, Object>) results;
			members = new HashMap<String, Date>((Integer) r.get("total"));
			_parseListMembers((Object[]) r.get("data"), members);

		} else {
			throw new UnexpectedMailChimpResponseException("Unsupported api version?");
		}
		return members;
	}

	private void _parseListMembers(Object[] results, Map<String, Date> members) throws MailChimpException {
		for (Object o : results) {
			if (o instanceof Map<?, ?>) {
				@SuppressWarnings("unchecked")
				Map<String, String> m = (Map<String, String>) o;
				String email = m.get("email");
				String timestamp = m.get("timestamp");
				try {
					members.put(email, MailChimpConstants.sdf.parse(timestamp));
				} catch (ParseException pe) {
					throw new MailChimpException("Could not parse member list timestamp.", pe);
				}
			}
		}
	}

	/**
	 * This will parse all member information retrieved from the call to <code>listMemberInfo</code> of the
	 * MailChimp API. We are silently ignoring any unsuccessful lookups (unknown email addresses or ids) as this
	 * method is returning only one MemberInfo at a time.
	 * This is because that was the was they did it back in the 1.2 days.
	 *
	 * @param results This is the Object created out of the xml-rpc-call to the MailChimp API.
	 *
	 * @return A {@link MemberInfo} containing - you guess it - the members info.
	 *
	 * @throws MailChimpException If parsing goes wrong.
	 */
	@SuppressWarnings("unchecked")
	public MemberInfo parseListMemberInfo(Object results) throws MailChimpException {
		if (results instanceof Map<?, ?>) {
			MemberInfo mi = new MemberInfo();
			Map<String, Object> m = (Map<String, Object>) results;

			if(m.containsKey("data")) { // this is version 1.3
				m = (Map<String, Object>) ((Object[])m.get("data"))[0];
			}

			try {
				setVars(m, mi);
				return mi;
			} catch (Exception ex) {
				throw new MailChimpException("Could not set fields.", ex);
			}
		}
		throw new MailChimpException(String.format("Result from MailChimp API was not of the expected type (instead, it was %s).", results.getClass().getSimpleName()));
	}
	
	public boolean parseListSubscribe(Object results) throws MailChimpException {
		if (results instanceof Boolean) {
			return (Boolean)results;
		}
		throw new MailChimpException(String.format("List subscription result type was not boolean (was: %s).", results.getClass().getSimpleName()));
	}
	
	public boolean parseListUnsubscribe(Object results) throws MailChimpException {
		if (results instanceof Boolean) {
			return (Boolean)results;
		}
		throw new MailChimpException(String.format("List unsubscription result type was not boolean (was: %s).", results.getClass().getSimpleName()));
	}
}