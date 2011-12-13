package mailjimp.dom.response.list;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.response.MailJimpResponse;

public class ListBatchUnsubscribeResponse extends MailJimpResponse {
  @JsonProperty("success_count")
  private Integer successCount;
  
  @JsonProperty("error_count")
  private Integer errorCount;
  
  private List<ListBatchError> errors;
  
  public ListBatchUnsubscribeResponse() {
    // empty
  }

  public Integer getSuccessCount() {
    return successCount;
  }

  public void setSuccessCount(Integer successCount) {
    this.successCount = successCount;
  }

  public Integer getErrorCount() {
    return errorCount;
  }

  public void setErrorCount(Integer errorCount) {
    this.errorCount = errorCount;
  }

  public List<ListBatchError> getErrors() {
    return errors;
  }

  public void setErrors(List<ListBatchError> errors) {
    this.errors = errors;
  }
}