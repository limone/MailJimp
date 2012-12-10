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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mailjimp.dom.MailJimpConstants;
import mailjimp.dom.request.MailJimpRequest;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListBatchSubscribeRequestWithVars extends MailJimpRequest {
  @JsonProperty("id")
  private String listId;
  
  private List<Map<String,Object>> batch;
  
  @JsonProperty("double_optin")
  private boolean doubleOptin;
  
  @JsonProperty("update_existing")
  private boolean updateExisting;
  
  @JsonProperty("replace_interests")
  private boolean replaceInterests;

  public ListBatchSubscribeRequestWithVars(String apikey, String listId, List<ListBatchSubscribeStructWithVars> batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) {
    super(apikey);
    this.listId = listId;    
    this.doubleOptin = doubleOptin;
    this.updateExisting = updateExisting;
    this.replaceInterests = replaceInterests;
    
    this.batch = new ArrayList<Map<String,Object>>(batch.size());
    for (ListBatchSubscribeStructWithVars v : batch)
    {
    	HashMap<String,Object> data = new HashMap<String,Object>();
    	
    	data.put(MailJimpConstants.MERGE_EMAIL, v.getEmailAddress());
    	data.put(MailJimpConstants.MERGE_EMAIL_TYPE, v.getEmailType());
    	data.put(MailJimpConstants.MERGE_FNAME, v.getFirstName());
    	data.put(MailJimpConstants.MERGE_LNAME, v.getLastName());
    	
    	data.putAll(v.getMergeVars());   	
    	
    	this.batch.add(data);
    }
  }

  public String getListId() {
    return listId;
  }

  public void setListId(String listId) {
    this.listId = listId;
  }



  public boolean isDoubleOptin() {
    return doubleOptin;
  }

  public void setDoubleOptin(boolean doubleOptin) {
    this.doubleOptin = doubleOptin;
  }

  public boolean isUpdateExisting() {
    return updateExisting;
  }

  public void setUpdateExisting(boolean updateExisting) {
    this.updateExisting = updateExisting;
  }

  public boolean isReplaceInterests() {
    return replaceInterests;
  }

  public void setReplaceInterests(boolean replaceInterests) {
    this.replaceInterests = replaceInterests;
  }

public List<Map<String, Object>> getBatch() {
	return batch;
}

public void setBatch(List<Map<String, Object>> batch) {
	this.batch = batch;
}
}