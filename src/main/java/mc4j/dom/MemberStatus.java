package mc4j.dom;

public enum MemberStatus {
	SUBSCRIBED("subscribed"), UNSUBSCRIBED("unsubscribed"), CLEANED("cleaned"), UPDATED("updated");
	
	private String status;
	
	MemberStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}