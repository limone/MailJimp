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
package mailjimp.dom.request.campaign;

import java.util.HashMap;

import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampaignCreateRequest extends MailJimpRequest {
	
	
	@JsonProperty
	private String type;
	
	@JsonProperty
	HashMap<String,Object> options;

	@JsonProperty
	HashMap<String,String> content;
	

	public CampaignCreateRequest(String apikey, String type, HashMap<String,Object> options,HashMap<String,String> content) {
		super(apikey);

		this.type = type;
		this.options = options;
		this.content = content;
	}

	static public HashMap<String,Object> buildOptions(String listId, String campaignName, String fromEmail, String fromName, String toName, int templateId)
	{
		HashMap<String,Object> options = new HashMap<String,Object>();

		options.put("list_id", listId);
		options.put("subject", campaignName);
		options.put("from_email",fromEmail);
		options.put("from_name",fromName);
		options.put("to_name",toName);
		options.put("template_id", templateId);
		
		return options;
	}
	
	static public HashMap<String,String> buildContentFromString(String html, String text)
	{
		HashMap<String,String> content = new HashMap<String,String>();
		content.put("html", html);
		content.put("text", text);
		return content;
		
	}
	
	static public HashMap<String,String> buildContentFromUrl(String url)
	{
		HashMap<String,String> content = new HashMap<String,String>();
		content.put("url", url);
		return content;		
	}
	
	static public HashMap<String,String> buildContentFromArchive(String archiveBase64, String archiveType)
	{
		HashMap<String,String> content = new HashMap<String,String>();
		content.put("archive", archiveBase64);
		content.put("archive_type", archiveType);
		return content;		
	}
	
		
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String, Object> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, Object> options) {
		this.options = options;
	}

	public HashMap<String, String> getContent() {
		return content;
	}

	public void setContent(HashMap<String, String> content) {
		this.content = content;
	}
}