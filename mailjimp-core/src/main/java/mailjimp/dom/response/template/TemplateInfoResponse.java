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

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.response.MailJimpResponse;

public class TemplateInfoResponse extends MailJimpResponse {  
  
  @JsonProperty("default_content")
  private List<String> defaultContent;
  
  @JsonProperty("sections")
  private List<String> sections;
  
  @JsonProperty
  private String source;
  
  @JsonProperty
  private String preview;  
  
  
  public TemplateInfoResponse() {
    // empty
  }

  @Override
  public String toString() {
    return "TemplateInfoResponse [default_content=" + defaultContent +", sections=" + sections + ", source=" + source +", preview="+preview+"]";
  }

public List<String> getDefaultContent() {
	return defaultContent;
}

public void setDefaultContent(List<String> defaultContent) {
	this.defaultContent = defaultContent;
}

public List<String> getSections() {
	return sections;
}

public void setSections(List<String> sections) {
	this.sections = sections;
}

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getPreview() {
	return preview;
}

public void setPreview(String preview) {
	this.preview = preview;
}


}