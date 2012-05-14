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

import java.util.HashMap;
import java.util.Map;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;


public class TemplateUpdateRequest extends MailJimpRequest {
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private int id;
	
	@JsonProperty
	private Map<String, String> values = null;
	
	public TemplateUpdateRequest(String apikey, int id, String name, String html) {
		super(apikey);
		
		if (values == null)
			values = new HashMap<String,String>();
		else
			values.clear();
		
		this.id = id;
		
		if (name != null)
			values.put("name", name);
		
		if (html != null)
			values.put("html", html);
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	
}
