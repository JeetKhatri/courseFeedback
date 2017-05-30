package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.AdminBean;
import courseFeedback.util.DBConnection;
import courseFeedback.util.GenrateMathodsUtils;

public class AdminDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public String isEmailExists(String emailId) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT * FROM ADMIN where adminemail=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, emailId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getString("adminId");
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
		return "";
	}

	public boolean checkPassword(String adminId, String oldPassword) {

		connection = DBConnection.getConnection();
		if (connection != null) {
			String selectSQL = "SELECT * FROM ADMIN where adminId=? and adminPassword=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, adminId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(oldPassword));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
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
		return false;
	}

	public boolean insert(AdminBean adminBean) {
		connection = DBConnection.getConnection();
		if (connection != null) {
			String insertSQL = "INSERT INTO ADMIN(adminId,adminEmail,adminPassword,isSuper)" + " values(?,?,?,?)";
			try {
				pstmt = connection.prepareStatement(insertSQL);
				pstmt.setString(1, adminBean.getAdminId());
				pstmt.setString(2, adminBean.getAdminEmail());
				pstmt.setString(3, adminBean.getAdminPassword());
				pstmt.setString(4, adminBean.getIsSuper());
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

	public List<AdminBean> list() {
		List<AdminBean> listOfAdmin = new ArrayList<AdminBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM ADMIN order by adminEmail";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				AdminBean admin = null;
				while (rs.next()) {
					admin = new AdminBean();
					admin.setAdminId(rs.getString("adminId"));
					admin.setAdminEmail(rs.getString("adminEmail"));
					admin.setAdminPassword(rs.getString("adminPassword"));
					admin.setIsSuper(rs.getString("isSUper"));
					admin.setIsAvailable(rs.getInt("isAvailable"));
					listOfAdmin.add(admin);
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
		return listOfAdmin;
	}

	public boolean delete(String adminId, String adminEmail) {

		connection = DBConnection.getConnection();

		if (connection != null) {
			String deleteSQL = "update admin set isAvailable = 0  WHERE adminId=?";

			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(deleteSQL);
				pstmt.setString(1, adminId);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					result = true;
					String query = "delete from admin where adminId=" + adminId;
					result = new LogDetailsDAO().insert(adminEmail, query, "admin");

					if (result == false) {
						connection.rollback();
					}

				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
					connection.commit();
				} catch (Exception e) {
				}
			}

		}
		return result;
	}

	public AdminBean getByPK(String adminId) {

		connection = DBConnection.getConnection();
		AdminBean admin = new AdminBean();
		if (connection != null) {
			String selectSQL = "SELECT * FROM ADMIN where adminId=?";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, adminId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					admin.setAdminId(rs.getString("adminId"));
					admin.setAdminEmail(rs.getString("adminEmail"));
					admin.setAdminPassword(rs.getString("adminPassword"));
					admin.setIsSuper(rs.getString("isSUper"));
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

	public boolean update(AdminBean adminBean) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE admin set " + "adminEmail=?,adminPassword=?,isSuper=? " + "WHERE ADMINID=?";
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(updateSQL);
				pstmt.setString(1, adminBean.getAdminEmail());
				pstmt.setString(2, adminBean.getAdminPassword());
				pstmt.setString(3, adminBean.getIsSuper());
				pstmt.setString(4, adminBean.getAdminId());
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {
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
		return result;
	}

	public boolean changePassword(String adminId, String newPassword) {
		connection = DBConnection.getConnection();

		if (connection != null) {
			String updateSQL = "UPDATE admin set " + "adminPassword= ?" + "WHERE ADMINID=?";
			try {
				connection.setAutoCommit(false);
				pstmt = connection.prepareStatement(updateSQL);
				pstmt.setString(1, GenrateMathodsUtils.makeSHA512(newPassword));
				pstmt.setString(2, adminId);
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					result = true;

				} else {
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
