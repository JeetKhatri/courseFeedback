package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import courseFeedback.bean.DateProgramDetailsBean;
import courseFeedback.util.DBConnection;
import courseFeedback.util.GenrateMathodsUtils;

public class DateProgramDetailsDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(DateProgramDetailsBean dateProgramDetailsBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO dateProgramDetails(dateProgramDetailsId,programDetailsId,date,userName)"
					+ " values(?,?,?,?)";
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, dateProgramDetailsBean.getDateProgramDetailsId());
				pstmt.setString(2, dateProgramDetailsBean.getProgramDetailsId());
				pstmt.setString(3, dateProgramDetailsBean.getDate());
				pstmt.setString(4, dateProgramDetailsBean.getUserName());
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {

					String sqlSelect = "select * from passwordPool where password = ?";
					String sqlInsert = "insert into passwordPool(password,programDetailsId) values (?,?)";
					int r = 0;
					for (int i = 0; i < Integer.parseInt(dateProgramDetailsBean.getNoOfPassword()); i++) {
						String password = GenrateMathodsUtils
								.getRandomPass(Integer.parseInt(dateProgramDetailsBean.getPasswordLength()) - 1)
								+ GenrateMathodsUtils.getRandomSpecialString(1);
						connection.setAutoCommit(false);
						pstmt = connection.prepareStatement(sqlSelect);
						pstmt.setString(1, password);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							i--;
						} else {
							pstmt = connection.prepareStatement(sqlInsert);
							pstmt.setString(1, password);
							pstmt.setString(2, dateProgramDetailsBean.getProgramDetailsId());
							r = pstmt.executeUpdate();
							if (r == 0) {
								connection.rollback();
								result = false;
							} else {
								result = true;
							}
						}
					}
				} else {
					connection.rollback();
					result = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.commit();
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return result;
	}

	public List<DateProgramDetailsBean> list() {
		List<DateProgramDetailsBean> listOfDateProgramDetails = new ArrayList<DateProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM dateProgramDetails,programDetails " + "where "
					+ "dateProgramDetails.programDetailsId=programDetails.programDetailsId";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				DateProgramDetailsBean dateProgramDetails = null;
				while (rs.next()) {
					dateProgramDetails = new DateProgramDetailsBean();
					dateProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					dateProgramDetails.setDateProgramDetailsId(rs.getString("dateProgramDetailsId"));
					dateProgramDetails.setDate(rs.getString("date"));
					dateProgramDetails.setProgramName(rs.getString("programName"));
					dateProgramDetails.setIsAvailable(rs.getString("isAvailable"));
					dateProgramDetails.setUserName(rs.getString("userName"));
					listOfDateProgramDetails.add(dateProgramDetails);
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
		return listOfDateProgramDetails;
	}

	public boolean delete(String dateProgramDetailsId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String deleteSQL = "update dateProgramDetails set isAvailable=0 WHERE dateProgramDetailsId=?";

			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, dateProgramDetailsId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update dateProgramDetails set isAvailable=0 WHERE dateProgramDetailsId="
							+ dateProgramDetailsId;
					result = new LogDetailsDAO().insert(adminEmail, query, "dateProgramDetails");

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

	public DateProgramDetailsBean getByPK(String dateProgramDetailsId) {

		connection = DBConnection.getConnection();
		DateProgramDetailsBean dateProgramDetails = new DateProgramDetailsBean();
		if (connection != null) {
			String selectSQL = "SELECT * FROM DateProgramDetails where dateProgramDetailsId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, dateProgramDetailsId);
				rs = pstmt.executeQuery();
				while (rs.next()) {

					dateProgramDetails.setDateProgramDetailsId(dateProgramDetailsId);
					dateProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					dateProgramDetails.setDateProgramDetailsId(rs.getString("dateProgramDetailsId"));
					dateProgramDetails.setDate(GenrateMathodsUtils.convertDate(rs.getString("date")));
					dateProgramDetails.setIsAvailable(rs.getString("isAvailable"));
					dateProgramDetails.setUserName(rs.getString("UserName"));
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
		return dateProgramDetails;
	}

	public boolean update(DateProgramDetailsBean dateProgramDetailsBean, String adminEmail) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE dateProgramDetails set " + "programDetailsId=?,date=?,isAvailable=?, username=? "
					+ "WHERE dateProgramDetailsId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(updateSQL);
				pstmt.setString(1, dateProgramDetailsBean.getProgramDetailsId());
				pstmt.setString(2, dateProgramDetailsBean.getDate());
				pstmt.setString(3, dateProgramDetailsBean.getIsAvailable());
				pstmt.setString(4, dateProgramDetailsBean.getUserName());
				pstmt.setString(5, dateProgramDetailsBean.getDateProgramDetailsId());
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {

					String sqlSelect = "select * from passwordPool where password = ?";
					String sqlInsert = "insert into passwordPool(password,programDetailsId) values (?,?)";
					int r = 0;
					for (int i = 0; i < Integer.parseInt(dateProgramDetailsBean.getNoOfPassword()); i++) {
						String password = GenrateMathodsUtils
								.getRandomPass(Integer.parseInt(dateProgramDetailsBean.getPasswordLength()) - 1)
								+ GenrateMathodsUtils.getRandomSpecialString(1);
						connection.setAutoCommit(false);
						pstmt = connection.prepareStatement(sqlSelect);
						pstmt.setString(1, password);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							i--;
						} else {
							pstmt = connection.prepareStatement(sqlInsert);
							pstmt.setString(1, password);
							pstmt.setString(2, dateProgramDetailsBean.getProgramDetailsId());
							r = pstmt.executeUpdate();
							if (r == 0) {
								connection.rollback();
								result = false;
							} else {
								result = true;
							}
						}
					}

					String query = "UPDATE dateProgramDetails set programDetailsId="
							+ dateProgramDetailsBean.getProgramDetailsId() + ",date=" + dateProgramDetailsBean.getDate()
							+ " WHERE dateProgramDetailsId=" + dateProgramDetailsBean.getDateProgramDetailsId();
					result = new LogDetailsDAO().insert(adminEmail, query, "dateProgramDetails");

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

	public ArrayList<DateProgramDetailsBean> listToday() {

		List<DateProgramDetailsBean> listOfDateProgramDetails = new ArrayList<DateProgramDetailsBean>();
		connection = DBConnection.getConnection();

		Date curr = new Date();
		String date = curr.getDate() + "";
		String month = (curr.getMonth() + 1) + "";
		String year = (curr.getYear() + 1900) + "";

		if (date.length() == 1) {
			date = "0" + date;
		}
		if (month.length() == 1) {
			month = "0" + month;
		}
		String d = year + "-" + month + "-" + date;

		if (connection != null) {
			String selectSQL = "SELECT * FROM dateProgramDetails,programDetails " + "where "
					+ "dateProgramDetails.programDetailsId=programDetails.programDetailsId and date=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, d);
				rs = pstmt.executeQuery();
				DateProgramDetailsBean dateProgramDetails = null;
				while (rs.next()) {
					dateProgramDetails = new DateProgramDetailsBean();
					dateProgramDetails.setProgramDetailsId(rs.getString("programDetailsId"));
					dateProgramDetails.setDateProgramDetailsId(rs.getString("dateProgramDetailsId"));
					dateProgramDetails.setDate(rs.getString("date"));
					dateProgramDetails.setProgramName(rs.getString("programName"));
					dateProgramDetails.setIsAvailable(rs.getString("isAvailable"));

					listOfDateProgramDetails.add(dateProgramDetails);
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
		return (ArrayList<DateProgramDetailsBean>) listOfDateProgramDetails;
	}

	public String loginStudent(String userName, String adminPassword) {

		connection = DBConnection.getConnection();

		Date curr = new Date();
		String date = curr.getDate() + "";
		String month = (curr.getMonth() + 1) + "";
		String year = (curr.getYear() + 1900) + "";

		if (date.length() == 1) {
			date = "0" + date;
		}
		if (month.length() == 1) {
			month = "0" + month;
		}
		String d = year + "-" + month + "-" + date;
		if (connection != null) {
			String selectSQL = "SELECT * FROM dateProgramDetails,passwordpool " + "where "
					+ "dateProgramDetails.programDetailsId=passwordpool.programDetailsId and date=? and username=? and password=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, d);
				pstmt.setString(2, userName);
				pstmt.setString(3, adminPassword);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getString("dateProgramDetails.programDetailsId");
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
		return null;
	}
}
