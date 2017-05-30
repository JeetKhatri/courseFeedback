package courseFeedback.bean;

public class QuestionsBean {

	private String questionId;
	private String questionContent;
	private String isAvailable;
	private String isLecture;
	private String isPrectical;
	private String isTutorial;
	private String isSpecial;
	private String ansType;
	private String yearId;
	private String termId;

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

	public String getAnsType() {
		return ansType;
	}

	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getIsLecture() {
		return isLecture;
	}

	public void setIsLecture(String isLecture) {
		this.isLecture = isLecture;
	}

	public String getIsPrectical() {
		return isPrectical;
	}

	public void setIsPrectical(String isPrectical) {
		this.isPrectical = isPrectical;
	}

	public String getIsTutorial() {
		return isTutorial;
	}

	public void setIsTutorial(String isTutorial) {
		this.isTutorial = isTutorial;
	}
}
