package courseFeedback.bean;

public class ProgramDetailsBean {

	private String programDetailsId;
	private String programName;
	private int isAvailable;
	
	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	
	public String getProgramDetailsId() {
		return programDetailsId;
	}

	public void setProgramDetailsId(String programDetailsId) {
		this.programDetailsId = programDetailsId;
	}

}
