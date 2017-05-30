package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.RemoveSpecialQuestionBean;
import courseFeedback.util.DBConnection;

public class RemoveSpecialQuestionDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(RemoveSpecialQuestionBean removeSpecialQuestionBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO RemoveSpecialQuestion(termCourseId,questionId) " + " values(?,?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, removeSpecialQuestionBean.getTermCourseId() + "");
				pstmt.setString(2, removeSpecialQuestionBean.getQuestionId() + "");
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

	public List<RemoveSpecialQuestionBean> list() {
		List<RemoveSpecialQuestionBean> listOfRemoveSpecialQuestion = new ArrayList<RemoveSpecialQuestionBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "select * from removespecialquestion rs,questions q,courseDetails cd where rs.termCourseId=cd.termCourseId and q.questionid=rs.questionid";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				RemoveSpecialQuestionBean removeSpecialQuestionBean = null;
				while (rs.next()) {
					removeSpecialQuestionBean = new RemoveSpecialQuestionBean();
					removeSpecialQuestionBean.setTermCourseId(rs.getInt("termCourseId"));
					removeSpecialQuestionBean.setQuestionId(rs.getInt("questionID"));
					removeSpecialQuestionBean.setQuestionContent(rs.getString("questionContent"));
					removeSpecialQuestionBean.setCourseName(rs.getString("courseNAme"));
					removeSpecialQuestionBean.setCourseCode(rs.getString("courseCode"));
					removeSpecialQuestionBean.setYearId(rs.getString("yearId"));
					removeSpecialQuestionBean.setTermId(rs.getString("termId"));
					listOfRemoveSpecialQuestion.add(removeSpecialQuestionBean);
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
		return listOfRemoveSpecialQuestion;
	}

	public boolean delete(String questionId, String courseProgramId, String adminEmail) {
		String sql = "delete from removeSpecialQuestion where questionId=?";
		connection = DBConnection.getConnection();

		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, questionId);
				if (pstmt.executeUpdate() > 0) {
					result = true;

					String query = "delete from removeSpecialQuestion where questionId=" + questionId;
					result = new LogDetailsDAO().insert(adminEmail, query, "removeSpecialQuestion");

					if (result == false) {
						connection.rollback();
					}
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
