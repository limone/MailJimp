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
package mailjimp.dom.response.template;

import java.util.List;

import mailjimp.dom.response.MailJimpResponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateListResponse extends MailJimpResponse {

	@JsonProperty("user")
	List<TemplateListItemResponse> listItems;

	public List<TemplateListItemResponse> getListItems() {
		return listItems;
	}

	public void setListItems(List<TemplateListItemResponse> listItems) {
		this.listItems = listItems;
	}
	
}
