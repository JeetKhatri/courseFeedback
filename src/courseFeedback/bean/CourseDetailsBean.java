package courseFeedback.bean;

public class CourseDetailsBean {

	private int termCourseId, isAvailable;
	private int yearId, termId;
	private String courseName, courseCode;
	private double credit, l, t, p;

	public int getTermCourseId() {
		return termCourseId;
	}

	public void setTermCourseId(int termCourseId) {
		this.termCourseId = termCourseId;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

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

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

}
