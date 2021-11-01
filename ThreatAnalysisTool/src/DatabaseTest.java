import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
	//connection credentials for testing purposes
	static final String DB_URL = "jdbc:mysql://174.57.254.128/ThreatAnalysisTool";
	static final String DB_USER = "testUser";
	static final String DB_PASS = "VulturesA";
	static final String DB_QUERY = "SELECT * FROM user_credentials";
	
	public static void main(String[] args) {
		//start database connection
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(DB_QUERY);) {
				//Parse result set
				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("user_id"));
					System.out.println("Username: " + resultSet.getString("username"));
					System.out.println("Password: " + resultSet.getString("password"));
					System.out.println("Last Login: " + resultSet.getString("last_login"));
					System.out.println("Access Level: " + resultSet.getInt("access_level"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
