package courseFeedback.bean;

public class CourseProgramDetailsBean {

	private String courseProgramId;
	private String termCourseId;
	private String courseName;
	private String courseCode;
	private String programDetailsId;
	private String programName;
	private int isAvailable;

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

	public String getCourseProgramId() {
		return courseProgramId;
	}

	public void setCourseProgramId(String courseProgramId) {
		this.courseProgramId = courseProgramId;
	}

	public String getTermCourseId() {
		return termCourseId;
	}

	public void setTermCourseId(String termCourseId) {
		this.termCourseId = termCourseId;
	}

	public String getProgramDetailsId() {
		return programDetailsId;
	}

	public void setProgramDetailsId(String programDetailsId) {
		this.programDetailsId = programDetailsId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
}
