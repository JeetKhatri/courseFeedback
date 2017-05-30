package courseFeedback.bean;

public class AdminBean {

	private String adminEmail;
	private String adminPassword;
	private String adminId;
	private String isSuper;
	private int isAvailable;

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}
}
