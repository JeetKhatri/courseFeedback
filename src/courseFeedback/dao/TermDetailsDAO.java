package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.TermDetailsBean;
import courseFeedback.util.DBConnection;

public class TermDetailsDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(TermDetailsBean termDetailsBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO termDetails(yearId,termId,yearName,termName,cnt)" + " values(?,?,?,?,?)";
			try {
				pstmt = connection.prepareStatement("select * from termdetails order by cnt");
				rs = pstmt.executeQuery();
				int cnt=0;
				while (rs.next()) {
					cnt = Integer.parseInt(rs.getString("cnt"));
				}
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setInt(1, termDetailsBean.getYearId());
				pstmt.setInt(2, termDetailsBean.getTermId());
				pstmt.setString(3, termDetailsBean.getYearName());
				pstmt.setString(4, termDetailsBean.getTermName());
				pstmt.setInt(5, ++cnt);
				
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

	public List<TermDetailsBean> list() {
		List<TermDetailsBean> listOfTermDetails = new ArrayList<TermDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM termDetails";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				TermDetailsBean termDetailsBean = null;
				while (rs.next()) {
					termDetailsBean = new TermDetailsBean();
					termDetailsBean.setYearId(rs.getInt("yearId"));
					termDetailsBean.setTermId(rs.getInt("termId"));
					termDetailsBean.setYearName(rs.getString("yearName"));
					termDetailsBean.setTermName(rs.getString("termName"));
					termDetailsBean.setIsAvailable(rs.getString("isAvailable"));
					listOfTermDetails.add(termDetailsBean);
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
		return listOfTermDetails;
	}

	public boolean delete(String termDetailsId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String str[] = termDetailsId.split(" ");
			String deleteSQL = "update termdetails set isAvailable = 0  WHERE yearId=? and termId=?";
			try {
				connection.setAutoCommit(false);

				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, str[0]);
				pstmt.setString(2, str[1]);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;

					String query = "update termdetails set isAvailable = 0  WHERE yearId=" + str[0] + " and termId="
							+ str[1];
					result = new LogDetailsDAO().insert(adminEmail, query, "termdetails");

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

	public TermDetailsBean getByPK(String termDetailsId) {

		connection = DBConnection.getConnection();
		TermDetailsBean termDetailsBean = new TermDetailsBean();

		if (connection != null) {

			String selectSQL = "select * from termdetails where yearId=? and termId=?";
			String str[] = termDetailsId.split(" ");
			try {
				pstmt = connection.prepareStatement(selectSQL);

				pstmt.setString(1, str[0]);
				pstmt.setString(2, str[1]);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					termDetailsBean.setYearId(rs.getInt("yearId"));
					termDetailsBean.setTermId(rs.getInt("termId"));
					termDetailsBean.setYearName(rs.getString("yearName"));
					termDetailsBean.setTermName(rs.getString("termName"));
					termDetailsBean.setIsAvailable(rs.getString("isAvailable"));
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
		return termDetailsBean;
	}

}
