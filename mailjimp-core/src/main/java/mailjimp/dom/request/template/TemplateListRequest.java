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
package mailjimp.dom.request.template;

import java.util.List;

import mailjimp.dom.request.MailJimpRequest;
import mailjimp.dom.request.NamedBoolean;

import org.codehaus.jackson.annotate.JsonProperty;


public class TemplateListRequest extends MailJimpRequest {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private List<NamedBoolean> types;
	
	@JsonProperty
	private String category;
	
	@JsonProperty
	private List<NamedBoolean> inactives;
	 

	public TemplateListRequest(String apikey, String category, List<NamedBoolean> types, List<NamedBoolean> inactives) {
		super(apikey);
				
		this.category = category;
		this.types = types;
		this.inactives = inactives;
		
	}


	public List<NamedBoolean> getTypes() {
		return types;
	}


	public void setTypes(List<NamedBoolean> types) {
		this.types = types;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public List<NamedBoolean> getInactives() {
		return inactives;
	}


	public void setInactives(List<NamedBoolean> inactives) {
		this.inactives = inactives;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
