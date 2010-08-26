package mc4j.dom;

public enum EmailType {
	HTML("html"), TEXT("text"), MOBILE("mobile");
	
	private String emailType;
	
	EmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailType() {
		return emailType;
	}
}