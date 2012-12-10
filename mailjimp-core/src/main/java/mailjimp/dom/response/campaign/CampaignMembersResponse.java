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
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignMembersResponse implements Serializable{
	
	@JsonProperty
	int total;
	
	@JsonProperty("data")
	List<CampaignMembersResponseItem> items;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CampaignMembersResponseItem> getItems() {
		return items;
	}

	public void setItems(List<CampaignMembersResponseItem> items) {
		this.items = items;
	}	
	
	@Override
	public String toString()
	{
		return "CampaignBounceMessageResponse: [total=" + total+"];";
	}
}
