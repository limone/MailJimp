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
package mailjimp.dom.request.list;

import java.io.Serializable;
import java.util.Map;

import mailjimp.dom.enums.EmailType;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListBatchSubscribeStructWithVars extends ListBatchSubscribeStruct {
	  @JsonProperty("merge_vars")
	  private Map<String,Object> mergeVars;

	 public ListBatchSubscribeStructWithVars(String emailAddress, EmailType emailType, Map<String,Object> mergeVars)
	 {
		 super(emailAddress, emailType);
		 setMergeVars(mergeVars);
	 }
	  
	  
	public Map<String, Object> getMergeVars() {
		return mergeVars;
	}

	public void setMergeVars(Map<String, Object> mergeVars) {
		this.mergeVars = mergeVars;
	}  
	  
}
