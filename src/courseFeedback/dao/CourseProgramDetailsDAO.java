package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.CourseProgramDetailsBean;
import courseFeedback.util.DBConnection;

public class CourseProgramDetailsDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(CourseProgramDetailsBean courseProgramDetailsBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO courseProgramDetails(termCourseId,programDetailsId,isAvailable)"
					+ " values(?,?,?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, courseProgramDetailsBean.getTermCourseId());
				pstmt.setString(2, courseProgramDetailsBean.getProgramDetailsId());
				pstmt.setInt(3, 1);
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

	public List<CourseProgramDetailsBean> list() {
		List<CourseProgramDetailsBean> listOfDateProgramDetails = new ArrayList<CourseProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM " + "courseProgramDetails CPD,programDetails PD,coursedetails CD where "
					+ "CPD.termCourseId=CD.termCourseId " + "AND CPD.programDetailsId=PD.programDetailsId";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				CourseProgramDetailsBean courseProgramDetails = null;
				while (rs.next()) {
					courseProgramDetails = new CourseProgramDetailsBean();
					courseProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					courseProgramDetails.setCourseCode(rs.getString("courseCode"));
					courseProgramDetails.setCourseName(rs.getString("courseName"));
					courseProgramDetails.setCourseProgramId(rs.getString("courseProgramId"));
					courseProgramDetails.setIsAvailable(rs.getInt("isAvailable"));
					courseProgramDetails.setProgramName(rs.getString("programName"));
					courseProgramDetails.setTermCourseId(rs.getString("termCourseId"));
					listOfDateProgramDetails.add(courseProgramDetails);
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
		return listOfDateProgramDetails;
	}

	public boolean delete(String courseProgramId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String deleteSQL = "update courseProgramDetails set isAvailable=0 WHERE courseProgramId=?";

			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, courseProgramId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update courseProgramDetails set isAvailable=0 WHERE courseProgramId="
							+ courseProgramId;
					result = new LogDetailsDAO().insert(adminEmail, query, "courseProgramDetails");

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

	public CourseProgramDetailsBean getByPK(String courseProgramId) {
		connection = DBConnection.getConnection();
		CourseProgramDetailsBean courseProgramDetails = new CourseProgramDetailsBean();
		if (connection != null) {
			String selectSQL = "SELECT * FROM courseProgramDetails CPD,programDetails PD,coursedetails CD where "
					+ "CPD.termCourseId=CD.termCourseId "
					+ "AND CPD.programDetailsId=PD.programDetailsId AND courseProgramId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setInt(1, Integer.parseInt(courseProgramId));
				rs = pstmt.executeQuery();
				while (rs.next()) {
					courseProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					courseProgramDetails.setCourseCode(rs.getString("courseCode"));
					courseProgramDetails.setCourseName(rs.getString("courseName"));
					courseProgramDetails.setCourseProgramId(rs.getString("courseProgramId"));
					courseProgramDetails.setIsAvailable(rs.getInt("isAvailable"));
					courseProgramDetails.setProgramName(rs.getString("programName"));
					courseProgramDetails.setTermCourseId(rs.getString("termCourseId"));
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
		return courseProgramDetails;
	}

	public boolean update(CourseProgramDetailsBean courseProgramDetailsBean, String adminEmail) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE courseProgramDetails set programDetailsId=?,termCourseId=?,isAvailable=? "
					+ "WHERE courseProgramId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(updateSQL);
				pstmt.setString(1, courseProgramDetailsBean.getProgramDetailsId());
				pstmt.setString(2, courseProgramDetailsBean.getTermCourseId());
				pstmt.setInt(3, courseProgramDetailsBean.getIsAvailable());
				pstmt.setString(4, courseProgramDetailsBean.getCourseProgramId());
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

					String query = "UPDATE courseProgramDetails set programDetailsId="
							+ courseProgramDetailsBean.getProgramDetailsId() + ",termCourseId="
							+ courseProgramDetailsBean.getTermCourseId() + ",isAvailable="
							+ courseProgramDetailsBean.getIsAvailable() + "WHERE courseProgramId="
							+ courseProgramDetailsBean.getCourseProgramId();
					result = new LogDetailsDAO().insert(adminEmail, query, "courseProgramDetails");

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

	public List<CourseProgramDetailsBean> listToday(String id) {
		List<CourseProgramDetailsBean> listOfDateProgramDetails = new ArrayList<CourseProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM " + "courseProgramDetails CPD,programDetails PD,coursedetails CD where "
					+ "CPD.termCourseId=CD.termCourseId "
					+ "AND CPD.programDetailsId=PD.programDetailsId and PD.programDetailsID=? and cpd.isAvailable=1";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				CourseProgramDetailsBean courseProgramDetails = null;
				while (rs.next()) {
					courseProgramDetails = new CourseProgramDetailsBean();
					courseProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					courseProgramDetails.setCourseCode(rs.getString("courseCode"));
					courseProgramDetails.setCourseName(rs.getString("courseName"));
					courseProgramDetails.setCourseProgramId(rs.getString("courseProgramId"));
					courseProgramDetails.setIsAvailable(rs.getInt("isAvailable"));
					courseProgramDetails.setProgramName(rs.getString("programName"));
					courseProgramDetails.setTermCourseId(rs.getString("termCourseId"));


					listOfDateProgramDetails.add(courseProgramDetails);
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
		return listOfDateProgramDetails;
	}
}
