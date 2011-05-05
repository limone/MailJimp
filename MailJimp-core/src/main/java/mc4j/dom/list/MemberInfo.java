/*
 * Copyright 2010-2011 Michael Laccetti
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
package mc4j.dom.list;

import mc4j.dom.IHasParserHints;
import mc4j.util.ParserHint;
import mc4j.util.ParserUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberInfo implements Serializable, IHasParserHints {

	private static final Map<String,ParserHint> hints = new HashMap<String, ParserHint>();

	static {
		ParserUtils.addParserHint("groupings", "merges/GROUPINGS", hints );
	}

	private String id;
	private String email;
	private String emailType;
	private Map<String,String> merges;
	private MemberStatus status;
	private String ipOpt;
	private String ipSignup;
	private Integer memberRating;
	private String campaignId;
	private Date timestamp;
	private Date infoChanged;
	private Integer webId;
	private Group[] groupings;
	
	public MemberInfo() {
		// empty
	}

	public MemberInfo(String id, String email, String emailType, Map<String,String> merges, MemberStatus status,
	                  String ipOpt, String ipSignup, Integer memberRating, String campaignId, Date timestamp,
	                  Date infoChanged, Integer webId) {
		this.id = id;
		this.email = email;
		this.emailType = emailType;
		this.merges = merges;
		this.status = status;
		this.ipOpt = ipOpt;
		this.ipSignup = ipSignup;
		this.memberRating = memberRating;
		this.campaignId = campaignId;
		this.timestamp = timestamp;
		this.infoChanged = infoChanged;
		this.webId = webId;
	}

	@Override
	public String toString() {
		return String.format("MemberInfo [campaignId=%s, email=%s, emailType=%s, id=%s, infoChanged=%s, " +
				"ipOpt=%s, ipSignup=%s, memberRating=%d, merges=%s, status=%s, timestamp=%s, webId=%d]",
				campaignId, email, emailType, id, infoChanged, ipOpt, ipSignup, memberRating, merges, status,
				timestamp, webId);
	}

	@Override
	public Map<String, ParserHint> getHints() {
		return hints;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public Map<String,String> getMerges() {
		return merges;
	}

	public void setMerges(Map<String,String> merges) {
		this.merges = merges;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

	public String getIpOpt() {
		return ipOpt;
	}

	public void setIpOpt(String ipOpt) {
		this.ipOpt = ipOpt;
	}

	public String getIpSignup() {
		return ipSignup;
	}

	public void setIpSignup(String ipSignup) {
		this.ipSignup = ipSignup;
	}

	public Integer getMemberRating() {
		return memberRating;
	}

	public void setMemberRating(Integer memberRating) {
		this.memberRating = memberRating;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getInfoChanged() {
		return infoChanged;
	}

	public void setInfoChanged(Date infoChanged) {
		this.infoChanged = infoChanged;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public Group[] getGroupings() {
		return groupings;
	}

	public void setGroupings(Group[] groupings) {
		this.groupings = groupings;
	}


}