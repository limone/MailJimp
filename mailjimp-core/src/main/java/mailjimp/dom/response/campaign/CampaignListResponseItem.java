package mailjimp.dom.response.campaign;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({ "segment_opts", "type_opts" }) //TODO
public class CampaignListResponseItem implements Serializable{
	
	@JsonProperty
	String id;
	
	@JsonProperty("web_id")
	Integer webId;
	
	@JsonProperty("list_id")
	String listId;
	
	@JsonProperty("folder_id")
	Integer folderId;
	
	@JsonProperty
	int	template_id;
	
	@JsonProperty	
	String	content_type;
	
	@JsonProperty
	String	title;
	
	@JsonProperty
	String	type;
	
	@JsonProperty
	String	create_time;
	
	@JsonProperty
	String	send_time;
	
	@JsonProperty
	int	emails_sent;
	
	@JsonProperty
	String	status;
	
	@JsonProperty
	String	from_name;
	
	@JsonProperty
	String	from_email;
	
	@JsonProperty
	String	subject;
	
	@JsonProperty
	String	to_name;
	
	@JsonProperty
	String	archive_url;
	
	@JsonProperty
	boolean	inline_css;
	
	@JsonProperty
	String	analytics;
	
	@JsonProperty
	String	analytics_tag;
	
	@JsonProperty
	boolean	authenticate;
	
	@JsonProperty
	boolean	ecomm360;
	
	@JsonProperty
	boolean	auto_tweet;
	
	@JsonProperty
	String	auto_fb_post;
	
	@JsonProperty
	boolean	auto_footer;
	
	@JsonProperty
	boolean	timewarp;
	
	@JsonProperty
	String	timewarp_schedule;
	
	@JsonProperty
	HashMap<String,Object> tracking;
	
	@JsonProperty
	boolean	html_clicks;
	
	@JsonProperty
	boolean	text_clicks;
	
	@JsonProperty
	boolean	opens;
	
	@JsonProperty
	String	segment_text;
	
	

//	@JsonProperty	
//	List<String>	segment_opts;
//	
//	@JsonProperty
//	List<String>	type_opts;
	
	
	@Override
	public String toString()
	{
		return "CampaignListResponseItem: [id=" + this.id + ", listId=" + this.listId + ", templateId=" + template_id + ", title=" + this.title +"];";
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getWebId() {
		return webId;
	}
	public void setWebId(Integer webId) {
		this.webId = webId;
	}
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public int getEmails_sent() {
		return emails_sent;
	}
	public void setEmails_sent(int emails_sent) {
		this.emails_sent = emails_sent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFrom_name() {
		return from_name;
	}
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	public String getFrom_email() {
		return from_email;
	}
	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTo_name() {
		return to_name;
	}
	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}
	public String getArchive_url() {
		return archive_url;
	}
	public void setArchive_url(String archive_url) {
		this.archive_url = archive_url;
	}
	public boolean isInline_css() {
		return inline_css;
	}
	public void setInline_css(boolean inline_css) {
		this.inline_css = inline_css;
	}
	public String getAnalytics() {
		return analytics;
	}
	public void setAnalytics(String analytics) {
		this.analytics = analytics;
	}
	public String getAnalytics_tag() {
		return analytics_tag;
	}
	public void setAnalytics_tag(String analytics_tag) {
		this.analytics_tag = analytics_tag;
	}
	public boolean isAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(boolean authenticate) {
		this.authenticate = authenticate;
	}
	public boolean isEcomm360() {
		return ecomm360;
	}
	public void setEcomm360(boolean ecomm360) {
		this.ecomm360 = ecomm360;
	}
	public boolean isAuto_tweet() {
		return auto_tweet;
	}
	public void setAuto_tweet(boolean auto_tweet) {
		this.auto_tweet = auto_tweet;
	}
	public String getAuto_fb_post() {
		return auto_fb_post;
	}
	public void setAuto_fb_post(String auto_fb_post) {
		this.auto_fb_post = auto_fb_post;
	}
	public boolean isAuto_footer() {
		return auto_footer;
	}
	public void setAuto_footer(boolean auto_footer) {
		this.auto_footer = auto_footer;
	}
	public boolean isTimewarp() {
		return timewarp;
	}
	public void setTimewarp(boolean timewarp) {
		this.timewarp = timewarp;
	}
	public String getTimewarp_schedule() {
		return timewarp_schedule;
	}
	public void setTimewarp_schedule(String timewarp_schedule) {
		this.timewarp_schedule = timewarp_schedule;
	}
	public HashMap<String,Object> getTracking() {
		return tracking;
	}
	public void setTracking(HashMap<String,Object> tracking) {
		this.tracking = tracking;
	}
	public boolean isHtml_clicks() {
		return html_clicks;
	}
	public void setHtml_clicks(boolean html_clicks) {
		this.html_clicks = html_clicks;
	}
	public boolean isText_clicks() {
		return text_clicks;
	}
	public void setText_clicks(boolean text_clicks) {
		this.text_clicks = text_clicks;
	}
	public boolean isOpens() {
		return opens;
	}
	public void setOpens(boolean opens) {
		this.opens = opens;
	}
	public String getSegment_text() {
		return segment_text;
	}
	public void setSegment_text(String segment_text) {
		this.segment_text = segment_text;
	}
//	public List<String> getSegment_opts() {
//		return segment_opts;
//	}
//	public void setSegment_opts(List<String> segment_opts) {
//		this.segment_opts = segment_opts;
//	}
//	public List<String> getType_opts() {
//		return type_opts;
//	}
//	public void setType_opts(List<String> type_opts) {
//		this.type_opts = type_opts;
//	}
//	
	
	
	
	}
