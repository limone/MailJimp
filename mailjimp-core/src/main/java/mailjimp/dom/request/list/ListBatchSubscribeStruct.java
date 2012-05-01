package mailjimp.dom.request.list;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import mailjimp.dom.enums.EmailType;

public class ListBatchSubscribeStruct implements Serializable {
  @JsonProperty("EMAIL")
  private String emailAddress;
  
  @JsonProperty("EMAIL_TYPE")
  private EmailType emailType;
  
  @JsonProperty("FNAME")
  private String firstName;
  
  @JsonProperty("LNAME")
  private String lastName;
  
  public ListBatchSubscribeStruct() {
    // empty
  }

  public ListBatchSubscribeStruct(String emailAddress, EmailType emailType) {
    super();
    this.emailAddress = emailAddress;
    this.emailType = emailType;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public EmailType getEmailType() {
    return emailType;
  }

  public void setEmailType(EmailType emailType) {
    this.emailType = emailType;
  }
  

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}
}
