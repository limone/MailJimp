package mailjimp.dom.response.list;

import org.codehaus.jackson.annotate.JsonProperty;

public class MailingListStats {
  @JsonProperty("member_count")
  private Integer memberCount;
  
  @JsonProperty("unsubscribe_count")
  private Integer unsubscribeCount;
  
  @JsonProperty("cleaned_count")
  private Integer cleanedCount;
  
  @JsonProperty("member_count_since_send")
  private Integer memberCountSinceSend;
  
  @JsonProperty("unsubscribe_count_since_send")
  private Integer unsubscribeCountSinceSend;
  
  @JsonProperty("cleaned_count_since_send")
  private Integer cleanedCountSinceSend;
  
  @JsonProperty("campaign_count")
  private Integer campaignCount;
  
  @JsonProperty("grouping_count")
  private Integer groupingCount;
  
  @JsonProperty("group_count")
  private Integer groupCount;
  
  @JsonProperty("merge_var_count")
  private Integer mergeVarCount;
  
  @JsonProperty("avg_sub_rate")
  private Integer avgSubRate;
  
  @JsonProperty("avg_unsub_rate")
  private Integer avgUnsubRate;
  
  @JsonProperty("target_sub_rate")
  private Integer targetSubRate;
  
  @JsonProperty("open_rate")
  private Integer openRate;
  
  @JsonProperty("click_rate")
  private Integer clickRate;
  
  public MailingListStats() {
    // empty
  }

  public Integer getMemberCount() {
    return memberCount;
  }

  public void setMemberCount(Integer memberCount) {
    this.memberCount = memberCount;
  }

  public Integer getUnsubscribeCount() {
    return unsubscribeCount;
  }

  public void setUnsubscribeCount(Integer unsubscribeCount) {
    this.unsubscribeCount = unsubscribeCount;
  }

  public Integer getCleanedCount() {
    return cleanedCount;
  }

  public void setCleanedCount(Integer cleanedCount) {
    this.cleanedCount = cleanedCount;
  }

  public Integer getMemberCountSinceSend() {
    return memberCountSinceSend;
  }

  public void setMemberCountSinceSend(Integer memberCountSinceSend) {
    this.memberCountSinceSend = memberCountSinceSend;
  }

  public Integer getUnsubscribeCountSinceSend() {
    return unsubscribeCountSinceSend;
  }

  public void setUnsubscribeCountSinceSend(Integer unsubscribeCountSinceSend) {
    this.unsubscribeCountSinceSend = unsubscribeCountSinceSend;
  }

  public Integer getCleanedCountSinceSend() {
    return cleanedCountSinceSend;
  }

  public void setCleanedCountSinceSend(Integer cleanedCountSinceSend) {
    this.cleanedCountSinceSend = cleanedCountSinceSend;
  }

  public Integer getCampaignCount() {
    return campaignCount;
  }

  public void setCampaignCount(Integer campaignCount) {
    this.campaignCount = campaignCount;
  }

  public Integer getGroupingCount() {
    return groupingCount;
  }

  public void setGroupingCount(Integer groupingCount) {
    this.groupingCount = groupingCount;
  }

  public Integer getGroupCount() {
    return groupCount;
  }

  public void setGroupCount(Integer groupCount) {
    this.groupCount = groupCount;
  }

  public Integer getMergeVarCount() {
    return mergeVarCount;
  }

  public void setMergeVarCount(Integer mergeVarCount) {
    this.mergeVarCount = mergeVarCount;
  }

  public Integer getAvgSubRate() {
    return avgSubRate;
  }

  public void setAvgSubRate(Integer avgSubRate) {
    this.avgSubRate = avgSubRate;
  }

  public Integer getAvgUnsubRate() {
    return avgUnsubRate;
  }

  public void setAvgUnsubRate(Integer avgUnsubRate) {
    this.avgUnsubRate = avgUnsubRate;
  }

  public Integer getTargetSubRate() {
    return targetSubRate;
  }

  public void setTargetSubRate(Integer targetSubRate) {
    this.targetSubRate = targetSubRate;
  }

  public Integer getOpenRate() {
    return openRate;
  }

  public void setOpenRate(Integer openRate) {
    this.openRate = openRate;
  }

  public Integer getClickRate() {
    return clickRate;
  }

  public void setClickRate(Integer clickRate) {
    this.clickRate = clickRate;
  }

  @Override
  public String toString() {
    return "MailingListStats [memberCount=" + memberCount + ", unsubscribeCount=" + unsubscribeCount + ", cleanedCount=" + cleanedCount + ", memberCountSinceSend=" + memberCountSinceSend + ", unsubscribeCountSinceSend=" + unsubscribeCountSinceSend + ", cleanedCountSinceSend=" + cleanedCountSinceSend + ", campaignCount=" + campaignCount + ", groupingCount=" + groupingCount + ", groupCount=" + groupCount + ", mergeVarCount=" + mergeVarCount + ", avgSubRate=" + avgSubRate + ", avgUnsubRate=" + avgUnsubRate + ", targetSubRate=" + targetSubRate + ", openRate=" + openRate + ", clickRate=" + clickRate + "]";
  }
}