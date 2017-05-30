package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.LogBean;
import courseFeedback.bean.LogDetailsBean;
import courseFeedback.util.DBConnection;

public class LogDetailsDAO {

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public boolean insert(String adminEmail, String query, String tableName) {
		LogDetailsBean logDetailsBean = termRetrive();
		connection = DBConnection.getConnection();
		if (connection != null && logDetailsBean != null) {
			try {
				pstmt = connection.prepareStatement(
						"insert into logdetails(yearId,termId,tableName,adminEmail,query)" + "values(?,?,?,?,?)");
				pstmt.setString(1, logDetailsBean.getYearId());
				pstmt.setString(2, logDetailsBean.getTermId());
				pstmt.setString(3, tableName);
				pstmt.setString(4, adminEmail);
				pstmt.setString(5, query);
				int rd = pstmt.executeUpdate();
				if (rd > 0) {
					result = true;
				} else {
					result = false;
				}
			} catch (Exception e) {
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
		return result;
	}

	public LogDetailsBean termRetrive() {
		LogDetailsBean logDetailsBean = new LogDetailsBean();
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement("select * from termdetails order by cnt");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					logDetailsBean.setTermId(rs.getString("termId") + "");
					logDetailsBean.setYearId(rs.getString("yearId") + "");
					result = true;
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
		return logDetailsBean;
	}

	public List<LogBean> list() {

		List<LogBean> listOfLogs = new ArrayList<LogBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM LogDetails";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				LogBean logBean = null;
				while (rs.next()) {
					logBean = new LogBean();
					logBean.setLogId(rs.getInt("logId"));
					logBean.setYearId(rs.getInt("yearId"));
					logBean.setTermID(rs.getInt("termId"));
					logBean.setAdminEmail(rs.getString("adminEmail"));
					logBean.setQuery(rs.getString("query"));
					logBean.setUpdatedAt(rs.getString("updatedAt"));
					logBean.setTableName(rs.getString("tableName"));
					listOfLogs.add(logBean);
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
		return listOfLogs;
	}
}
