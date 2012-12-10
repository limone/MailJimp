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

	import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

	public class CampaignMembersRequest extends MailJimpRequest {
		
		@JsonProperty("cid")
		String campaignId;
		
		@JsonProperty
		private int start;
		
		@JsonProperty
		private int limit;
	
		@JsonProperty
		String status;
		
		public CampaignMembersRequest(String apikey, String campaignId, String status, int start, int limit) {
			super(apikey);
			this.start = start;
			this.limit = limit;
			this.campaignId = campaignId;			
			this.status = status;
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


		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		
	}
