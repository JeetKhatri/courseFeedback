package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.ProgramDetailsBean;
import courseFeedback.util.DBConnection;

public class ProgramDetailsDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(ProgramDetailsBean programDetailsBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO ProgramDetails(programName)" + " values(?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, programDetailsBean.getProgramName());
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

	public List<ProgramDetailsBean> list() {
		List<ProgramDetailsBean> listOfProgramDetails = new ArrayList<ProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM ProgramDetails";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				ProgramDetailsBean programDetails = null;
				while (rs.next()) {
					programDetails = new ProgramDetailsBean();
					programDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					programDetails.setProgramName(rs.getString("programName"));
					programDetails.setIsAvailable(rs.getInt("isAvailable"));
					listOfProgramDetails.add(programDetails);
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
		return listOfProgramDetails;
	}

	public boolean delete(String programDetailsId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String deleteSQL = "update programDetails set isAvailable = 0 WHERE programDetailsId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, programDetailsId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;
					String query = "update programDetails set isAvailable = 0 WHERE programDetailsId="
							+ programDetailsId;
					result = new LogDetailsDAO().insert(adminEmail, query, "programDetails");

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
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	public ProgramDetailsBean getByPK(String programDetailsId) {

		connection = DBConnection.getConnection();
		ProgramDetailsBean programDetails = new ProgramDetailsBean();
		if (connection != null) {
			String selectSQL = "SELECT * FROM ProgramDetails where programDetailsId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, programDetailsId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					programDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					programDetails.setProgramName(rs.getString("programName"));
					programDetails.setIsAvailable(rs.getInt("isAvailable"));
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
		return programDetails;
	}

	public boolean update(ProgramDetailsBean programDetailsBean, String adminEmail) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE programDetails set programName=?,isAvailable=? " + "WHERE ProgramDetailsId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(updateSQL);

				pstmt.setString(1, programDetailsBean.getProgramName());
				pstmt.setInt(2, programDetailsBean.getIsAvailable());
				pstmt.setString(3, programDetailsBean.getProgramDetailsId());

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

					String query = "UPDATE programDetails set programName=" + programDetailsBean.getProgramName()
							+ ",isAvailable=" + programDetailsBean.getIsAvailable() + " WHERE ProgramDetailsId="
							+ programDetailsBean.getProgramDetailsId();
					result = new LogDetailsDAO().insert(adminEmail, query, "programDetails");

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

	public String getName(String programDetailsId) {

		connection = DBConnection.getConnection();
		if (connection != null) {
			String deleteSQL = "select * from programdetails WHERE programDetailsId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, programDetailsId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getString("programName");
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
		return null;

	}
}
