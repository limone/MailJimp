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
						if (param.isAssignableFrom(value.getClass())) {
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
	private <T> T convert(Class<T> expected, Object value) throws MailChimpException {
		if (value == null) { return null; }

		if (expected.isEnum()) {
			try {
				for (T obj : expected.getEnumConstants()) {
					if (obj.toString().equalsIgnoreCase((String) value)) { return obj; }
				}
				throw new MailChimpException(String.format("No enum constant found for value %s in enum %s.", value.toString(), expected.getSimpleName()));
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
				Object[] o = (Object[])value;
				return null;
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

	public List<MailingList> parseLists(Object results) throws MailChimpException {
		List<MailingList> lists = new ArrayList<MailingList>();
		if (results instanceof Object[]) {
			for (Object o : (Object[]) results) {
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
		return lists;
	}

	public Map<String, Date> parseListMembers(Object results) throws MailChimpException {
		Map<String, Date> r = new HashMap<String, Date>();
		if (results instanceof Object[]) {
			for (Object o : (Object[]) results) {
				if (o instanceof Map<?, ?>) {
					@SuppressWarnings("unchecked")
					Map<String, String> m = (Map<String, String>) o;
					String email = m.get("email");
					String timestamp = m.get("timestamp");
					try {
						r.put(email, MailChimpConstants.sdf.parse(timestamp));
					} catch (ParseException pe) {
						throw new MailChimpException("Could not parse member list timestamp.", pe);
					}
				}
			}
		}
		return r;
	}

	public MemberInfo parseListMemberInfo(Object results) throws MailChimpException {

		if (results instanceof Map<?, ?>) {
			MemberInfo mi = new MemberInfo();
			@SuppressWarnings("unchecked")
			Map<String, Object> m = (Map<String, Object>) results;
			try {
				setVars(m, mi);
				return mi;
			} catch (Exception ex) {
				throw new MailChimpException("Could not set fields.", ex);
			}
		}
		throw new MailChimpException(String.format("Result from MailChimp API was not of the expected type (instead, it was %s).", results.getClass().getSimpleName()));
	}
}