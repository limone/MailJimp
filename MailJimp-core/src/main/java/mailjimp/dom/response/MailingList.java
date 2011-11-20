package mailjimp.dom.response;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class MailingList {
  private String id;
  
  @JsonProperty("web_id")
  private Long webId;
  
  private String name;
  
  @JsonProperty("date_created")
  private Date dateCreated;
  
  @JsonProperty("email_type_option")
  private Boolean emailTypeOption;
  
  @JsonProperty("use_awesomebar")
  private boolean useAwesomebar;
  
  @JsonProperty("default_from_name")
  private String defaultFromName;
  
  @JsonProperty("default_from_email")
  private String defaultFromEmail;
  
  @JsonProperty("default_subject")
  private String defaultSubject;
  
  @JsonProperty("default_language")
  private String defaultLanguage;
  
  @JsonProperty("list_rating")
  private Integer listRating;
  
  @JsonProperty("subscribe_url_short")
  private String subscribeUrlShort;
  
  @JsonProperty("subscribe_url_long")
  private String subscribeUrlLong;
  
  @JsonProperty("beamer_address")
  private String beamerAddress;
  
  private MailingListStats stats;
  
  private List<Module> modules;
  
  public MailingList() {
    // empty
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getWebId() {
    return webId;
  }

  public void setWebId(Long webId) {
    this.webId = webId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Boolean getEmailTypeOption() {
    return emailTypeOption;
  }

  public void setEmailTypeOption(Boolean emailTypeOption) {
    this.emailTypeOption = emailTypeOption;
  }

  public boolean isUseAwesomebar() {
    return useAwesomebar;
  }

  public void setUseAwesomebar(boolean useAwesomebar) {
    this.useAwesomebar = useAwesomebar;
  }

  public String getDefaultFromName() {
    return defaultFromName;
  }

  public void setDefaultFromName(String defaultFromName) {
    this.defaultFromName = defaultFromName;
  }

  public String getDefaultFromEmail() {
    return defaultFromEmail;
  }

  public void setDefaultFromEmail(String defaultFromEmail) {
    this.defaultFromEmail = defaultFromEmail;
  }

  public String getDefaultSubject() {
    return defaultSubject;
  }

  public void setDefaultSubject(String defaultSubject) {
    this.defaultSubject = defaultSubject;
  }

  public String getDefaultLanguage() {
    return defaultLanguage;
  }

  public void setDefaultLanguage(String defaultLanguage) {
    this.defaultLanguage = defaultLanguage;
  }

  public Integer getListRating() {
    return listRating;
  }

  public void setListRating(Integer listRating) {
    this.listRating = listRating;
  }

  public String getSubscribeUrlShort() {
    return subscribeUrlShort;
  }

  public void setSubscribeUrlShort(String subscribeUrlShort) {
    this.subscribeUrlShort = subscribeUrlShort;
  }

  public String getSubscribeUrlLong() {
    return subscribeUrlLong;
  }

  public void setSubscribeUrlLong(String subscribeUrlLong) {
    this.subscribeUrlLong = subscribeUrlLong;
  }

  public String getBeamerAddress() {
    return beamerAddress;
  }

  public void setBeamerAddress(String beamerAddress) {
    this.beamerAddress = beamerAddress;
  }

  public MailingListStats getStats() {
    return stats;
  }

  public void setStats(MailingListStats stats) {
    this.stats = stats;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  @Override
  public String toString() {
    return "MailingList [id=" + id + ", webId=" + webId + ", name=" + name + ", dateCreated=" + dateCreated + ", emailTypeOption=" + emailTypeOption + ", useAwesomebar=" + useAwesomebar + ", defaultFromName=" + defaultFromName + ", defaultFromEmail=" + defaultFromEmail + ", defaultSubject=" + defaultSubject + ", defaultLanguage=" + defaultLanguage + ", listRating=" + listRating + ", subscribeUrlShort=" + subscribeUrlShort + ", subscribeUrlLong=" + subscribeUrlLong + ", beamerAddress=" + beamerAddress + ", stats=" + stats + ", modules=" + modules + "]";
  }
}