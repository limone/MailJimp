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
package mailjimp.util;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Collection tools needed inside and outside of the {@link mailjimp.service.impl.MailChimpParser}.
 *
 * @author Eike Hirsch (me at eike-hirsch dot net)
 *         Date: 05.05.11
 *         Time: 11:55
 */
public class ParserUtils {

	private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z_0-9]+");

	/**
	 * Converts a property name to its complementary map key.
	 *
	 * @param name The name of the property.
	 * @return its complementary map key.
	 */
	public static String convertKey(String name) {
		if( UPPERCASE_PATTERN.matcher(name).matches()) {
			//return the name untouched.
			return name;
		}
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

	/**
	 * Adds a new ParserHint to the map of all hints.
	 *
	 * @param key   Name of the property the hint is for.
	 * @param path  The path to the value of that property.
	 * @param hints The map to add the new hint to.
	 */
	public static void addParserHint(String key, String path, Map<String, ParserHint> hints) {
		ParserHint hint = new ParserHint(key, path);
		hints.put(hint.getKey(), hint);
	}
}
