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
package mc4j.util;

/**
 * Helping the {@link mc4j.service.impl.MailChimpParser parser} to find nested properties.
 *
 * @author Eike Hirsch (me at eike-hirsch dot net)
 *         Date: 05.05.11
 *         Time: 14:51
 */
public class ParserHint {

	private String origPath;
	private String key;
	private String[] steps;

	/**
	 * Creates a ParserHint.
	 *
	 * @param key The name of the property to set.
	 * @param origPath The path to the value.
	 */
	public ParserHint(String key, String origPath) {
		this.key = ParserUtils.convertKey(key);
		this.origPath = origPath;
		parseSteps();
	}


	private void parseSteps() {
		this.steps = this.origPath.split("/");
		for (int i = 0; i < steps.length; i++) {
			steps[i] = ParserUtils.convertKey(steps[i]);
		}
	}

	/**
	 * Provides the steps to the desired value. Each step is already converted to match the naming schema of the
	 * MailChip API.
	 *
	 * @return All steps in an array.
	 */
	public String[] getSteps() {
		return steps;
	}

	/**
	 * Provides the property name converted to a valid MailChimp map key.
	 *
	 * @return The property name converted to a valid MailChimp map key.
	 */
	public String getKey() {
		return key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ParserHint that = (ParserHint) o;

		if (key != null ? !key.equals(that.key) : that.key != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return key != null ? key.hashCode() : 0;
	}
}
