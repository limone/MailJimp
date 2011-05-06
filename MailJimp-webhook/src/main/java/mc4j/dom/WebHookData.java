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
package mc4j.dom;

import mc4j.dom.list.MemberInfo;

import java.util.Date;
import java.util.Map;

/**
 * Generic object to keep all the additional data that is provided by MailChimp.
 *
 * @author Eike Hirsch (me at eike-hirsch dot net)
 *         Date: 06.05.11
 *         Time: 10:57
 */
public class WebHookData {

	private WebHookType type;
	private Date firedAt;
	private Map<String,Object> rawData;
	private MemberInfo memberInfo;

	public WebHookData(WebHookType type) {
		setType(type);
	}

	/**
	 * Get the type of this callback.
	 *
	 * @return What is this all about?
	 */
	public WebHookType getType() {
		return type;
	}

	public void setType(WebHookType type) {
		this.type = type;
	}

	/**
	 * The time of this callback.
	 *
	 * @return When did it happen?
	 */
	public Date getFiredAt() {
		return firedAt;
	}

	public void setFiredAt(Date firedAt) {
		this.firedAt = firedAt;
	}

	/**
	 * Everything else. Including the raw data of a possible already parsed {@link mc4j.dom.list.MemberInfo MemberInfo}
	 * object.
	 *
	 * @return All we got.
	 */
	public Map<String, Object> getRawData() {
		return rawData;
	}

	public void setRawData(Map<String, Object> rawData) {
		this.rawData = rawData;
	}

	/**
	 * If there was MemberInfo in the request, here is the place to get it.
	 *
	 * @return Parsed member info or null;
	 */
	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
}
