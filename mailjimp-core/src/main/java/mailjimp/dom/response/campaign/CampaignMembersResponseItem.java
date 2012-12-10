/*
 * Copyright 2011 Michael Laccetti and Tim Gilbert
 *
 * This file is part of MailJimp and forked MailJimp under https://github.com/knaak/MailJimp
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
package mailjimp.dom.response.campaign;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignMembersResponseItem implements Serializable{
	
		
	@JsonProperty
	String email;
	
	@JsonProperty
	String status;
	
	@JsonProperty
	String absplit_group;
	
	@JsonProperty
	String tz_group;
	
	
	@Override
	public String toString()
	{
		return "CampaignBoucneMessageResponseItem [tz_group=" + tz_group + ", email=" + email + ", absplit_group=" + absplit_group + "];";
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAbsplit_group() {
		return absplit_group;
	}


	public void setAbsplit_group(String absplit_group) {
		this.absplit_group = absplit_group;
	}


	public String getTz_group() {
		return tz_group;
	}


	public void setTz_group(String tz_group) {
		this.tz_group = tz_group;
	}
	
	

}
