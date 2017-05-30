package courseFeedback.bean;

public class CourseQuestionAvgTabularBean {
	
	private String courseCode;
	private String courseName;
	private String L,T,P;
	private double avg[]=new double[21];
	private String courseAvg;
	private int noOfStudent;
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getL() {
		return L;
	}
	public void setL(String l) {
		L = l;
	}
	public String getT() {
		return T;
	}
	public void setT(String t) {
		T = t;
	}
	public String getP() {
		return P;
	}
	public void setP(String p) {
		P = p;
	}
	public double[] getAvg() {
		return avg;
	}
	public void setAvg(double[] avg) {
		this.avg = avg;
	}
	public String getCourseAvg() {
		return courseAvg;
	}
	public void setCourseAvg(String courseAvg) {
		this.courseAvg = courseAvg;
	}
	public int getNoOfStudent() {
		return noOfStudent;
	}
	public void setNoOfStudent(int noOfStudent) {
		this.noOfStudent = noOfStudent;
	} 
	
	

}
