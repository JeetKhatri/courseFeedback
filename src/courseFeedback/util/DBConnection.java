
package courseFeedback.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection connection = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		//	connection = DriverManager.getConnection("jdbc:mysql://10.100.81.38:3306/daiictFeedbackSystem","avnish", "avnish");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/daiictFeedbackSystem","root", "root");
			if (connection != null) {
				
			} else{
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		getConnection();
	}
}
