import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseTest {
	//connection credentials for testing purposes
	static final String DB_URL = "jdbc:mysql://174.57.254.128/ThreatAnalysisTool";
	static final String DB_USER = "testUser";
	static final String DB_PASS = "VulturesA";

	
	public static void main(String[] args) {
		System.out.println(authenticateUser("testUser3", "password123"));
		//System.out.println(addUser("testUser3", "password123", 3));
	}
	
	public static boolean addUser(String username, String password, int accessLevel) {
		boolean userAdded = false;
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String databaseQuery = "INSERT INTO user_credentials"
				+ "(username, password, last_login, access_level)"
				+ "VALUES"
				+ "(\"" + username + "\", SHA1(\"" + password + "\"), \"" + dateFormatter.format(date) + "\", \"" + accessLevel + "\")";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();) {
				statement.executeUpdate(databaseQuery);
				statement.close();
				connection.close();
				userAdded = true;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("User Not Added");
			}
		
		
		return userAdded;
	}
	
	public static int authenticateUser(String username, String password) {
		//function will return  1 if user was authenticated
		//function will return  2 if username/password combo was incorrect
		//function will return -1 for any other error (db-related)
		int returnCode = -1;
		String databaseQuery = "SELECT * FROM user_credentials WHERE "
				+ "username=\"" + username + "\""
				+ "AND password=SHA1(\"" + password + "\")";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {
				
			//Parse result set
				if (resultSet.next()) {
					//found user/password combo in database
					returnCode = 1;
					System.out.println("ID: " + resultSet.getInt("user_id"));
					System.out.println("Username: " + resultSet.getString("username"));
					System.out.println("Password: " + resultSet.getString("password"));
					System.out.println("Last Login: " + resultSet.getString("last_login"));
					System.out.println("Access Level: " + resultSet.getInt("access_level"));
				} else {
					//did not find user/password combo in database
					returnCode = 2;
					System.out.println("Incorrect username/password combination");
				}
				
				statement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return returnCode;
	}
}
