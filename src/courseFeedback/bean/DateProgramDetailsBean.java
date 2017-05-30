package courseFeedback.bean;

public class DateProgramDetailsBean {

	private String dateProgramDetailsId;
	private String programDetailsId;
	private String date;
	private String programName;
	private String isAvailable;
	private String userName;
	private String passwordLength;
	private String noOfPassword;

	
	public String getPasswordLength() {
		return passwordLength;
	}

	public void setPasswordLength(String passwordLength) {
		this.passwordLength = passwordLength;
	}

	public String getNoOfPassword() {
		return noOfPassword;
	}

	public void setNoOfPassword(String noOfPassword) {
		this.noOfPassword = noOfPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getDateProgramDetailsId() {
		return dateProgramDetailsId;
	}

	public void setDateProgramDetailsId(String dateProgramDetailsId) {
		this.dateProgramDetailsId = dateProgramDetailsId;
	}

	public String getProgramDetailsId() {
		return programDetailsId;
	}

	public void setProgramDetailsId(String programDetailsId) {
		this.programDetailsId = programDetailsId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
