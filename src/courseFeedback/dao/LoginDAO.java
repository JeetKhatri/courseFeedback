package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import courseFeedback.bean.AdminBean;
import courseFeedback.util.DBConnection;
import courseFeedback.util.GenrateMathodsUtils;

public class LoginDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private boolean result = false;

	public AdminBean login(String adminEmail, String adminPassword) {
		String sql = "select * from admin where adminEmail=? and adminPassword=? and isAvailable=1";
		connection = DBConnection.getConnection();
		AdminBean admin = null;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, adminEmail);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(adminPassword));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					admin = new AdminBean();
					admin.setAdminId(rs.getString("adminId"));
					admin.setAdminEmail(rs.getString("adminEmail"));
					admin.setAdminPassword(rs.getString("adminPassword"));
					admin.setIsSuper(rs.getString("isSUper"));
					admin.setIsAvailable(rs.getInt("isAvailable"));
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
		return admin;
	}

	public boolean logout(String password) {

		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "delete from passwordpool where password=?";

			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, password);
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

}
