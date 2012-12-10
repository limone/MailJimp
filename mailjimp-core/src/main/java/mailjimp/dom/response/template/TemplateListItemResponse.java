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

import mailjimp.dom.response.MailJimpResponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateListItemResponse extends MailJimpResponse {  


	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("category")
	private String category;

	@JsonProperty("layout")
	private String layout;

	@JsonProperty("preview_image")
	private String previewImage;

	@JsonProperty("date_created")
	private String dateCreated;

	@JsonProperty("edit_source")
	private Boolean editSource;  
	
	@JsonProperty("active")
	private String active;


	public TemplateListItemResponse() {
		// empty
	}

	@Override
	public String toString() {
		return "TemplateListItemResponse [id=" + id + ", name=" + name + ", layout=" + layout +", preview_image=" + previewImage 
				+ ", date_created="+ dateCreated + ",edit_source=" + editSource + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getEditSource() {
		return editSource;
	}

	public void setEditSource(Boolean editSource) {
		this.editSource = editSource;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}