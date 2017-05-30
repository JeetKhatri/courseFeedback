package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.AddSpecialQuestionBean;
import courseFeedback.bean.LogDetailsBean;
import courseFeedback.util.DBConnection;

public class AddSpecialQuestionDAO {

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(AddSpecialQuestionBean addSpecialQuestionBean) {

		LogDetailsBean logDetailsBean = new LogDetailsDAO().termRetrive();
		connection = DBConnection.getConnection();
		if (connection != null && logDetailsBean != null) {
			String insertSQL = "INSERT INTO QUESTIONS(questionContent,L,T,P,ansType,isSpecial,yearId,termId)"
					+ " values(?,?,?,?,?,?,?,?)";
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, addSpecialQuestionBean.getQuestionContent());
				pstmt.setString(2, addSpecialQuestionBean.getIsLecture());
				pstmt.setString(3, addSpecialQuestionBean.getIsTutorial());
				pstmt.setString(4, addSpecialQuestionBean.getIsPrectical());
				pstmt.setString(5, addSpecialQuestionBean.getAnsType());
				pstmt.setString(6, "1");
				pstmt.setString(7, logDetailsBean.getYearId());
				pstmt.setString(8, logDetailsBean.getTermId());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					insertSQL = "INSERT INTO addSpecialQuestion(termCourseId,questionId) "
							+ " values(?,LAST_INSERT_ID())";
					try {
						pstmt = connection.prepareStatement(insertSQL);
						pstmt.setString(1, addSpecialQuestionBean.getTermCourseId() + "");
						rowsAffected = pstmt.executeUpdate();
						if (rowsAffected > 0) {
							result = true;
							return true;
						} else {
							connection.rollback();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
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

	public List<AddSpecialQuestionBean> list() {
		List<AddSpecialQuestionBean> listOfAddSpecialQuestion = new ArrayList<AddSpecialQuestionBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "select * from addspecialquestion rs,questions q,courseDetails cd where rs.termCourseId=cd.termCourseId and q.questionid=rs.questionid";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				AddSpecialQuestionBean addSpecialQuestionBean = null;
				while (rs.next()) {
					addSpecialQuestionBean = new AddSpecialQuestionBean();
					addSpecialQuestionBean.setTermId(rs.getString("termId"));
					addSpecialQuestionBean.setYearId(rs.getString("yearId"));
					addSpecialQuestionBean.setTermCourseId(rs.getInt("termCourseId"));
					addSpecialQuestionBean.setQuestionId(rs.getInt("questionID") + "");
					addSpecialQuestionBean.setQuestionContent(rs.getString("questionContent"));
					addSpecialQuestionBean.setCourseName(rs.getString("courseName"));
					addSpecialQuestionBean.setCourseCode(rs.getString("courseCode"));
					addSpecialQuestionBean.setAnsType(rs.getString("ansType"));
					listOfAddSpecialQuestion.add(addSpecialQuestionBean);
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
		return listOfAddSpecialQuestion;
	}

	public boolean delete(String questionId, String courseProgramId, String adminEmail) {
		String sql = "delete from addSpecialQuestion where questionId=? and termCourseId=?";
		connection = DBConnection.getConnection();

		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, questionId);
				pstmt.setString(2, courseProgramId);
				if (pstmt.executeUpdate() > 0) {
					if (new QuestionsDAO().delete(questionId, adminEmail)) {
						result = true;

						String query = "delete from addSpecialQuestion where questionId=" + questionId
								+ " and termCourseId=" + courseProgramId;
						result = new LogDetailsDAO().insert(adminEmail, query, "addSpecialQuestion");
						if (result == false)
							connection.rollback();

					} else
						connection.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.commit();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
		return result;
	}
}