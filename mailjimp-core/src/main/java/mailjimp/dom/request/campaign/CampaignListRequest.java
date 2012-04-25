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

	import java.util.Date;
	import java.util.HashMap;
	import java.util.Locale;
	import java.util.TimeZone;

	import mailjimp.dom.request.MailJimpRequest;

	import org.codehaus.jackson.annotate.JsonProperty;
	import org.springframework.format.datetime.DateFormatter;

	public class CampaignListRequest extends MailJimpRequest {
		
		@JsonProperty
		private int start;
		
		@JsonProperty
		private int limit;
		
		@JsonProperty
		HashMap<String,Object> filters;
		
		static DateFormatter df = null;
		

		public CampaignListRequest(String apikey, HashMap<String,Object> filters, int start, int limit) {
			super(apikey);
			this.filters = filters;
			this.start = start;
			this.limit = limit;
			
			if (df == null)
			{
				df = new DateFormatter("yyyy-MM-dd HH:mm:ss");
				df.setTimeZone(TimeZone.getTimeZone("GMT"));
			}				
		}
			
		static public HashMap<String,Object> buildFilters(String campaignId, String listId, Integer folderId, Integer templateId, String status, 
				String type, String fromName, String fromEmail, String title, String subject, Date sendTimeStart, Date sendTimeEnd, Boolean exact)
		{
			HashMap<String,Object> options = new HashMap<String,Object>();
		
			if (campaignId != null)
				options.put("campaign_id", campaignId);
			
			if (listId != null)
				options.put("list_id", listId);
			
			if (folderId != 0)
				options.put("folderId", (int) folderId);
			
			if (templateId != null)
				options.put("template_id", templateId);
			
			
			if (status != null)
				options.put("status", status);
			
			if (type != null)
				options.put("type", type);
			
			if (fromName != null)
				options.put("from_name", fromName);
					
			if (fromEmail!= null)
				options.put("from_email", fromEmail);
			
			if (title != null)
				options.put("title", title);
			
			if (subject != null)
				options.put("subject", subject);
			
			if (sendTimeStart != null)
				options.put("sendtime_start", df.print(sendTimeStart, Locale.US));
			
			if (sendTimeEnd != null)
				options.put("sendtime_end", df.print(sendTimeStart, Locale.US));
			
			if (exact != null)
				options.put("exact", (boolean) exact);
			
			return options;
		}
		
		
		
		
		
		

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getLimit() {
			return limit;
		}

		public void setLimit(int limit) {
			this.limit = limit;
		}

		public HashMap<String, Object> getFilters() {
			return filters;
		}

		public void setFilters(HashMap<String, Object> filters) {
			this.filters = filters;
		}	
	}
