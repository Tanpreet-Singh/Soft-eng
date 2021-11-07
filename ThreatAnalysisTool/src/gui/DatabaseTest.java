package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseTest {
	//Database credentials
	final String DB_URL = "jdbc:mysql://174.57.254.128/ThreatAnalysisTool";
	final String DB_USER = "testUser";
	final String DB_PASS = "VulturesA";

	
	public int addUser(String username, String password, int accessLevel) {
		//return code -1 - error occurred (database-related)
		//return code  1 - user added
		//return code  2 - error: user name already exists
		int returnCode = -1;
		if (!userExists(username)) {
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
					returnCode = 1;
				} catch (SQLException e) {
					e.printStackTrace();
					//System.out.println("User Not Added");
					returnCode = -1;
				}
		}else {
			returnCode = 2;
		}
		
		
		return returnCode;
	}
	
	public int authenticateUser(String username, String password) {
		//function will return  1 if user was authenticated
		//function will return  2 if user name/password combo was incorrect
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
				} else {
					//did not find user/password combo in database
					returnCode = 2;
				}
				
				statement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return returnCode;
	}
	
	private boolean userExists(String username) {
		boolean exists = false;
		String databaseQuery = "SELECT * FROM user_credentials WHERE username=\"" + username + "\"";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {
				
				//Parse result set
				if (resultSet.next()) {
					//found existing user in database
					exists = true;
				}
				
				statement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return exists;
	}
	
	public int importThreats(JSONBundle threatBundle) throws ParseException {
		//returnCode -1 - database error
		//returnCode 1  - successful import 
		int returnCode = -1;
		
		//store bundle information
//		String bundleType = threatBundle.getObjects().get(0).getType();
//		String bundleName = threatBundle.getObjects().get(0).getName();
//		String bundleID = threatBundle.getObjects().get(0).getID();
//		String bundleDescription = threatBundle.getObjects().get(0).getDescription();
		//remove bundle from loop, to only grab threats
		threatBundle.getObjects().remove(0);
		
		//prepare local variables for database query
		String databaseQuery = "INSERT INTO threats(threat_id, access_level, name, description, created, modified, type, created_by_ref, spec_version, x_mitre_platforms) values(?,1,?,?,?,?,?,?,?,?)";
//		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		java.sql.Date sqlCreatedDate;
//		java.sql.Date sqlModifiedDate;
		
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement statement = connection.prepareStatement(databaseQuery);) {

				//loop through threats
				for (Threat threat : threatBundle.getObjects().subList(0, 9)) {
					if (!threatExists(threat)) {
						statement.setString(1, threat.getID());
						statement.setString(2, threat.getName());
						statement.setString(3, threat.getDescription());
						
						//convert to different Date object (required to be from mySQL package)
						//sqlCreatedDate = new java.sql.Date(dateFormatter.parse(threat.getDateCreated()).getTime());
						//sqlModifiedDate = new java.sql.Date(dateFormatter.parse(threat.getDateCreated()).getTime());
						statement.setDate(4, null);
						statement.setDate(5, null);
						
						statement.setString(6, threat.getType());
						statement.setString(7, threat.getCreated_by_ref());
						statement.setString(8, threat.getSpecVersion());
						statement.setString(9, threat.getPlatforms());
						
						//execute query once all values are set
						statement.executeUpdate();
					}
				}
				statement.close();
				connection.close();
				returnCode = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("User Not Added");
				returnCode = -1;
			}
		
		
		
		
		return returnCode;
	}
	
	private boolean threatExists(Threat threat) {
		boolean exists = false;
		String databaseQuery = "SELECT * FROM threats WHERE threat_id=\"" + threat.getID() + "\"";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {
				
				//Parse result set
				if (resultSet.next()) {
					//found existing user in database
					exists = true;
				}
				
				statement.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return exists;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
