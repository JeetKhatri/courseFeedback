package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.util.DBConnection;

public class CourseDetailsDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(CourseDetailsBean courseDetailsBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO courseDetails(yearId,termId,courseName,courseCode,courseCredit,L,T,P)"
					+ " values(?,?,?,?,?,?,?,?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setInt(1, courseDetailsBean.getYearId());
				pstmt.setInt(2, courseDetailsBean.getTermId());
				pstmt.setString(3, courseDetailsBean.getCourseName());
				pstmt.setString(4, courseDetailsBean.getCourseCode());
				pstmt.setDouble(5, courseDetailsBean.getCredit());
				pstmt.setDouble(6, courseDetailsBean.getL());
				pstmt.setDouble(7, courseDetailsBean.getT());
				pstmt.setDouble(8, courseDetailsBean.getP());
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

	public ArrayList<CourseDetailsBean> select() {
		ArrayList<CourseDetailsBean> listOfCourses = new ArrayList<>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM courseDetails";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				CourseDetailsBean courseDetailsBean = new CourseDetailsBean();
				while (rs.next()) {
					courseDetailsBean = new CourseDetailsBean();
					courseDetailsBean.setCourseCode(rs.getString("courseCode"));
					courseDetailsBean.setCourseName(rs.getString("courseName"));
					courseDetailsBean.setCredit(rs.getDouble("courseCredit"));
					courseDetailsBean.setYearId(rs.getInt("yearId"));
					courseDetailsBean.setTermId(rs.getInt("termId"));
					courseDetailsBean.setL(Double.parseDouble(rs.getString("L")));
					courseDetailsBean.setT(Double.parseDouble(rs.getString("T")));
					courseDetailsBean.setP(Double.parseDouble(rs.getString("P")));
					courseDetailsBean.setTermCourseId(rs.getInt("termcourseId"));
					courseDetailsBean.setIsAvailable(rs.getInt("isAvailable"));
					listOfCourses.add(courseDetailsBean);
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

		return listOfCourses;
	}

	public CourseDetailsBean getByPK(int termcourseId) {

		CourseDetailsBean courseDetailsBean = new CourseDetailsBean();
		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT * FROM courseDetails where termCourseId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setInt(1, termcourseId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					courseDetailsBean = new CourseDetailsBean();
					courseDetailsBean.setCourseCode(rs.getString("courseCode"));
					courseDetailsBean.setCourseName(rs.getString("courseName"));
					courseDetailsBean.setCredit(rs.getDouble("courseCredit"));
					courseDetailsBean.setYearId(rs.getInt("yearId"));
					courseDetailsBean.setTermId(rs.getInt("termId"));
					courseDetailsBean.setL(Double.parseDouble(rs.getString("L")));
					courseDetailsBean.setT(Double.parseDouble(rs.getString("T")));
					courseDetailsBean.setP(Double.parseDouble(rs.getString("P")));
					courseDetailsBean.setIsAvailable(rs.getInt("isAvailable"));
					courseDetailsBean.setTermCourseId(rs.getInt("termcourseId"));
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
		return courseDetailsBean;
	}

	public boolean getByCode(String courseCode) {

		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT * FROM courseDetails where courseCode=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, courseCode + "");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (Exception e) {
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

		return false;
	}

	public boolean update(CourseDetailsBean courseDetailsBean, String adminEmail) {

		String sql = "update courseDetails set courseCredit=?,courseName=?,CourseCode=?,yearId=?,TermId=?,l=?,t=?,p=?,isAvailable=? where termCourseID=?";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(sql);
				pstmt.setDouble(1, courseDetailsBean.getCredit());
				pstmt.setString(2, courseDetailsBean.getCourseName());
				pstmt.setString(3, courseDetailsBean.getCourseCode());
				pstmt.setInt(4, courseDetailsBean.getYearId());
				pstmt.setInt(5, courseDetailsBean.getTermId());
				pstmt.setDouble(6, courseDetailsBean.getL());
				pstmt.setDouble(7, courseDetailsBean.getT());
				pstmt.setDouble(8, courseDetailsBean.getP());
				pstmt.setDouble(9, courseDetailsBean.getIsAvailable());
				pstmt.setDouble(10, courseDetailsBean.getTermCourseId());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update courseDetails set courseCredit=" + courseDetailsBean.getCredit()
							+ ",courseName=" + courseDetailsBean.getCourseName() + ",CourseCode="
							+ courseDetailsBean.getCourseCode() + ",yearId=" + courseDetailsBean.getYearId() + ""
							+ ",TermId=" + courseDetailsBean.getTermId() + ",l=" + courseDetailsBean.getL() + ",t="
							+ courseDetailsBean.getT() + ",p=" + courseDetailsBean.getP() + ",isAvailable="
							+ courseDetailsBean.getIsAvailable() + " where termCourseID="
							+ courseDetailsBean.getTermCourseId();
					result = new LogDetailsDAO().insert(adminEmail, query, "courseDetails");

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

	public boolean delete(int termCourseId, String adminEmail) {

		String sql = "update courseDetails set isAvailable=? where termCourseID=?";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, termCourseId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update courseDetails set isAvailable=0 where termCourseID=" + termCourseId;
					result = new LogDetailsDAO().insert(adminEmail, query, "courseDetails");

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

	public String getCode(String termCourseId) {

		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT * FROM courseDetails where termCourseId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, termCourseId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getString("courseCode");
				}
			} catch (Exception e) {
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

	public boolean updateWithoutCourseCode(CourseDetailsBean courseDetailsBean) {

		String sql = "update courseDetails set courseCredit=?,courseName=?,yearId=?,TermId=?,l=?,t=?,p=? where termCourseID=?";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setDouble(1, courseDetailsBean.getCredit());
				pstmt.setString(2, courseDetailsBean.getCourseName());
				pstmt.setInt(3, courseDetailsBean.getYearId());
				pstmt.setInt(4, courseDetailsBean.getTermId());
				pstmt.setDouble(5, courseDetailsBean.getL());
				pstmt.setDouble(6, courseDetailsBean.getT());
				pstmt.setDouble(7, courseDetailsBean.getP());
				pstmt.setDouble(8, courseDetailsBean.getTermCourseId());

				return pstmt.execute();
			} catch (Exception e) {
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

		return false;
	}

	public CourseDetailsBean getByCourseCode(String courseCode) {
		connection = DBConnection.getConnection();
		CourseDetailsBean courseDetailsBean = new CourseDetailsBean();
		if (connection != null) {
			String selectSQL = "SELECT * FROM courseDetails where courseCode=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, courseCode);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					courseDetailsBean = new CourseDetailsBean();
					courseDetailsBean.setCourseCode(rs.getString("courseCode"));
					courseDetailsBean.setCourseName(rs.getString("courseName"));
					courseDetailsBean.setCredit(rs.getDouble("courseCredit"));
					courseDetailsBean.setYearId(rs.getInt("yearId"));
					courseDetailsBean.setTermId(rs.getInt("termId"));
					courseDetailsBean.setL(Double.parseDouble(rs.getString("L")));
					courseDetailsBean.setT(Double.parseDouble(rs.getString("T")));
					courseDetailsBean.setP(Double.parseDouble(rs.getString("P")));
					courseDetailsBean.setTermCourseId(rs.getInt("termcourseId"));
					courseDetailsBean.setIsAvailable(rs.getInt("isAvailable"));
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
		return courseDetailsBean;
	}
public String getNameCourseCode(String courseCode) {
		
		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT courseName FROM courseDetails where courseCode=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, courseCode);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					return rs.getString("courseName");
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
