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

	public class CampaignBounceMessagesRequest extends MailJimpRequest {
		
		@JsonProperty("cid")
		String campaignId;
		
		@JsonProperty
		private int start;
		
		@JsonProperty
		private int limit;
	
		@JsonProperty
		String since;
		
		static private DateFormatter df = null;
		

		public CampaignBounceMessagesRequest(String apikey, String campaignId, Date since, int start, int limit) {
			super(apikey);
			this.start = start;
			this.limit = limit;
			this.campaignId = campaignId;
			
			if (df == null)
			{
				df = new DateFormatter("yyyy-MM-dd");
				df.setTimeZone(TimeZone.getTimeZone("GMT"));
			}
			
			this.since = df.print(since, Locale.US);
		}


		
		
		
		
		public String getCampaignId() {
			return campaignId;
		}


		public void setCampaignId(String campaignId) {
			this.campaignId = campaignId;
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


		public String getSince() {
			return since;
		}


		public void setSince(String since) {
			this.since = since;
		}

		
		
	}
