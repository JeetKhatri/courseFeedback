package courseFeedback.bean;

public class SemesterProgramDetailsBean extends ProgramDetailsBean {

	private String semId;
	private String semName;
	private String batchId;
	private String batchName;
	private String yearId;
	private String termId;
	private String programDetailsId;
	private String courseName;
	private String courseCode;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSemId() {
		return semId;
	}

	public void setSemId(String semId) {
		this.semId = semId;
	}

	public String getSemName() {
		return semName;
	}

	public void setSemName(String semName) {
		this.semName = semName;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getProgramDetailsId() {
		return programDetailsId;
	}

	public void setProgramDetailsId(String programDetailsId) {
		this.programDetailsId = programDetailsId;
	}

}