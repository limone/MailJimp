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
package mc4j.dom;

import java.io.Serializable;
import java.util.Date;

public class MailingList implements Serializable {
	private String id;
	private Integer webId;
	private String name;
	private Date dateCreated;
	private Integer memberCount;
	private Integer unsubscribeCount;
	private Integer cleanedCount;
	private boolean emailTypeOption;
	private String defaultFromName;
	private String defaultFromEmail;
	private String defaultSubject;
	private String defaultLanguage;
	private Double listRating;
	private Integer memberCountSinceSend;
	private Integer unsubscribeCountSinceSend;
	private Integer cleanedCountSinceSend;
	
	public MailingList() {
		// empty
	}

	public MailingList(String id, Integer webId, String name, Date dateCreated, Integer memberCount, Integer unsubscribeCount, Integer cleanedCount, boolean emailTypeOption, String defaultFromName, String defaultFromEmail, String defaultSubject, String defaultLanguage, Double listRating, Integer memberCountSinceSend, Integer unsubscribeCountSinceSend, Integer cleanedCountSinceSend) {
		this.id = id;
		this.webId = webId;
		this.name = name;
		this.dateCreated = dateCreated;
		this.memberCount = memberCount;
		this.unsubscribeCount = unsubscribeCount;
		this.cleanedCount = cleanedCount;
		this.emailTypeOption = emailTypeOption;
		this.defaultFromName = defaultFromName;
		this.defaultFromEmail = defaultFromEmail;
		this.defaultSubject = defaultSubject;
		this.defaultLanguage = defaultLanguage;
		this.listRating = listRating;
		this.memberCountSinceSend = memberCountSinceSend;
		this.unsubscribeCountSinceSend = unsubscribeCountSinceSend;
		this.cleanedCountSinceSend = cleanedCountSinceSend;
	}

	@Override
	public String toString() {
		return "MailingList [id=" + id + ", webId=" + webId + ", name=" + name + ", dateCreated=" + dateCreated + ", memberCount=" + memberCount + ", unsubscribeCount=" + unsubscribeCount + ", cleanedCount=" + cleanedCount + ", emailTypeOption=" + emailTypeOption + ", defaultFromName=" + defaultFromName + ", defaultFromEmail=" + defaultFromEmail + ", defaultSubject=" + defaultSubject + ", defaultLanguage=" + defaultLanguage + ", listRating=" + listRating + ", memberCountSinceSend=" + memberCountSinceSend + ", unsubscribeCountSinceSend=" + unsubscribeCountSinceSend + ", cleanedCountSinceSend=" + cleanedCountSinceSend + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getUnsubscribeCount() {
		return unsubscribeCount;
	}

	public void setUnsubscribeCount(Integer unsubscribeCount) {
		this.unsubscribeCount = unsubscribeCount;
	}

	public Integer getCleanedCount() {
		return cleanedCount;
	}

	public void setCleanedCount(Integer cleanedCount) {
		this.cleanedCount = cleanedCount;
	}

	public boolean isEmailTypeOption() {
		return emailTypeOption;
	}

	public void setEmailTypeOption(boolean emailTypeOption) {
		this.emailTypeOption = emailTypeOption;
	}

	public String getDefaultFromName() {
		return defaultFromName;
	}

	public void setDefaultFromName(String defaultFromName) {
		this.defaultFromName = defaultFromName;
	}

	public String getDefaultFromEmail() {
		return defaultFromEmail;
	}

	public void setDefaultFromEmail(String defaultFromEmail) {
		this.defaultFromEmail = defaultFromEmail;
	}

	public String getDefaultSubject() {
		return defaultSubject;
	}

	public void setDefaultSubject(String defaultSubject) {
		this.defaultSubject = defaultSubject;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public Double getListRating() {
		return listRating;
	}

	public void setListRating(Double listRating) {
		this.listRating = listRating;
	}

	public Integer getMemberCountSinceSend() {
		return memberCountSinceSend;
	}

	public void setMemberCountSinceSend(Integer memberCountSinceSend) {
		this.memberCountSinceSend = memberCountSinceSend;
	}

	public Integer getUnsubscribeCountSinceSend() {
		return unsubscribeCountSinceSend;
	}

	public void setUnsubscribeCountSinceSend(Integer unsubscribeCountSinceSend) {
		this.unsubscribeCountSinceSend = unsubscribeCountSinceSend;
	}

	public Integer getCleanedCountSinceSend() {
		return cleanedCountSinceSend;
	}

	public void setCleanedCountSinceSend(Integer cleanedCountSinceSend) {
		this.cleanedCountSinceSend = cleanedCountSinceSend;
	}
}