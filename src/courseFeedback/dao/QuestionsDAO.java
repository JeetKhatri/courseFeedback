package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.LogDetailsBean;
import courseFeedback.bean.QuestionsBean;
import courseFeedback.util.DBConnection;

public class QuestionsDAO {

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(QuestionsBean questionsBean) {
		LogDetailsBean logDetailsBean = new LogDetailsDAO().termRetrive();
		connection = DBConnection.getConnection();
		if (connection != null && logDetailsBean != null) {
			String insertSQL = "INSERT INTO QUESTIONS(questionContent,L,T,P,isAvailable,ansType,yearId,termId)"
					+ " values(?,?,?,?,?,?,?,?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, questionsBean.getQuestionContent());
				pstmt.setString(2, questionsBean.getIsLecture());
				pstmt.setString(3, questionsBean.getIsTutorial());
				pstmt.setString(4, questionsBean.getIsPrectical());
				pstmt.setString(5, questionsBean.getIsAvailable());
				pstmt.setString(6, questionsBean.getAnsType());
				pstmt.setString(7, logDetailsBean.getYearId());
				pstmt.setString(8, logDetailsBean.getTermId());
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;
				} else {
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return result;
	}

	public List<QuestionsBean> list() {
		List<QuestionsBean> listOfQuestions = new ArrayList<QuestionsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM Questions where isspecial=0";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				QuestionsBean question = null;
				while (rs.next()) {
					question = new QuestionsBean();
					question.setQuestionId(rs.getString("questionId"));
					question.setQuestionContent(rs.getString("questionContent"));
					question.setIsAvailable(rs.getString("isAvailable"));
					question.setIsLecture(rs.getString("L"));
					question.setIsTutorial(rs.getString("T"));
					question.setIsPrectical(rs.getString("P"));
					question.setIsSpecial(rs.getString("isSpecial"));
					question.setAnsType(rs.getString("ansType"));
					question.setYearId(rs.getString("yearId"));
					question.setTermId(rs.getString("termId"));
					listOfQuestions.add(question);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listOfQuestions;
	}

	public List<QuestionsBean> listForRemoveQuestions() { // use in
															// removequestionsinsert.jsp
		List<QuestionsBean> listOfQuestions = new ArrayList<QuestionsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM Questions where isSpecial=0 and questionId NOT IN (SELECT questionId FROM removespecialquestion)";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				QuestionsBean question = null;
				while (rs.next()) {
					question = new QuestionsBean();
					question.setQuestionId(rs.getString("questionId"));
					question.setQuestionContent(rs.getString("questionContent"));
					question.setIsAvailable(rs.getString("isAvailable"));
					question.setIsLecture(rs.getString("L"));
					question.setIsTutorial(rs.getString("T"));
					question.setIsPrectical(rs.getString("P"));
					question.setIsSpecial(rs.getString("isSpecial"));
					question.setAnsType(rs.getString("ansType"));
					listOfQuestions.add(question);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listOfQuestions;
	}

	public boolean delete(String questionId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String deleteSQL = "update questions set isAvailable = 0  WHERE questionId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, questionId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update questions set isAvailable = 0  WHERE questionId=" + questionId;
					result = new LogDetailsDAO().insert(adminEmail, query, "questions");

					if (result == false) {
						connection.rollback();
					}
				} else {
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.commit();
					connection.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	public QuestionsBean getByPK(String questionId) {

		connection = DBConnection.getConnection();

		QuestionsBean question = new QuestionsBean();

		if (connection != null) {

			String selectSQL = "select * from questions where questionId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);

				pstmt.setString(1, questionId);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					question.setQuestionId(rs.getString("questionId"));
					question.setQuestionContent(rs.getString("questionContent"));
					question.setIsAvailable(rs.getString("isAvailable"));
					question.setIsLecture(rs.getString("L"));
					question.setIsTutorial(rs.getString("T"));
					question.setIsPrectical(rs.getString("P"));
					question.setIsSpecial(rs.getString("isSpecial"));
					question.setAnsType(rs.getString("ansType"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return question;
	}

	public boolean update(QuestionsBean questionsBean, String adminEmail) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE questions set questionContent=?,L=?,T=?,P=?,isAvailable=?,ansType=? where questionId=?";

			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(updateSQL);

				pstmt.setString(1, questionsBean.getQuestionContent());
				pstmt.setString(2, questionsBean.getIsLecture());
				pstmt.setString(3, questionsBean.getIsTutorial());
				pstmt.setString(4, questionsBean.getIsPrectical());
				pstmt.setString(5, questionsBean.getIsAvailable());
				pstmt.setString(6, questionsBean.getAnsType());
				pstmt.setString(7, questionsBean.getQuestionId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

					String query = "UPDATE questions set questionContent=" + questionsBean.getQuestionContent() + ",L="
							+ questionsBean.getIsLecture() + ",T=" + questionsBean.getIsTutorial() + "," + "P="
							+ questionsBean.getIsPrectical() + ",isAvailable=" + questionsBean.getIsAvailable()
							+ "ansType=" + questionsBean.getQuestionId() + " where questionId="
							+ questionsBean.getQuestionId();
					result = new LogDetailsDAO().insert(adminEmail, query, "questions");

					if (result == false) {
						connection.rollback();
					}

				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.commit();
					connection.close();
				} catch (Exception e) {
				}
			}

		}
		return result;
	}

public String getNameQuestionId(String questionId) {
		
		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT questionContent  FROM questions where questionId =?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, questionId);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					return rs.getString("questionContent");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return "";
	}
}
