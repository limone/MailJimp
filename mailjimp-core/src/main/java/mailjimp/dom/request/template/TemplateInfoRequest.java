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

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;


public class TemplateInfoRequest extends MailJimpRequest {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private int tid;
	
	@JsonProperty
	private String type;

	public TemplateInfoRequest(String apikey, int id, String type) {
		super(apikey);
		this.tid = id;
		
		if (type == null)
			this.type = "user";
		else			
			this.type = type;
	}


	public int getId() {
		return tid;
	}


	public void setId(int id) {
		this.tid = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
