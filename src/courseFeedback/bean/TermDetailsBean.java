package courseFeedback.bean;

public class TermDetailsBean {

	private int yearId, termId;
	private String yearName, TermName;
	private String isAvailable;
	private String termdetailsId;

	
	public String getTermdetailsId() {
		return termdetailsId;
	}

	public void setTermdetailsId(String termdetailsId) {
		this.termdetailsId = termdetailsId;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
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

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

	public String getTermName() {
		return TermName;
	}

	public void setTermName(String termName) {
		TermName = termName;
	}

}
