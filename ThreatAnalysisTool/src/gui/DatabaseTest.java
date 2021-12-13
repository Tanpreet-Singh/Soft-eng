package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class DatabaseTest {
	// Database credentials
	final String DB_URL = "jdbc:mysql://174.57.254.128/ThreatAnalysisTool?rewriteBatchedStatements=true";
	final String DB_USER = "testUser";
	final String DB_PASS = "VulturesA";

	public int addUser(String username, String password, int accessLevel) {
		// return code -1 - error occurred (database-related)
		// return code 1 - user added
		// return code 2 - error: user name already exists
		int returnCode = -1;
		if (!userExists(username)) {
			Date date = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String databaseQuery = "INSERT INTO user_credentials" + "(username, password, last_login, access_level)"
					+ "VALUES" + "(\"" + username + "\", SHA1(\"" + password + "\"), \"" + dateFormatter.format(date)
					+ "\", \"" + accessLevel + "\")";
			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
					Statement statement = connection.createStatement();) {
				statement.executeUpdate(databaseQuery);
				statement.close();
				connection.close();
				returnCode = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				// System.out.println("User Not Added");
				returnCode = -1;
			}
		} else {
			returnCode = 2;
		}

		return returnCode;
	}

	public int deleteUser(String username) {
		// function will return 1 if user was deleted
		// function will return 2 if username was not deleted
		// function will return -1 for any other error (db-related)
		int code = 2;

		String databaseQuery = "DELETE from user_credentials where username = '" + username + "'";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(databaseQuery);
			statement.close();
			connection.close();
			code = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			code = -1;
		}
		return code;
	}

	public int authenticateUser(String username, String password) {
		// function will return 1 if user was authenticated
		// function will return 2 if username/password combo was incorrect
		// function will return -1 for any other error (db-related)
		int level = -1;
		String databaseQuery = "SELECT access_level FROM user_credentials WHERE " + "username=\"" + username + "\""
				+ "AND password=SHA1(\"" + password + "\")";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {

			// Parse result set
			if (resultSet.next()) {
				level = resultSet.getInt("access_level");
			} else {
				// did not find user/password combo in database
				level = -2;
			}

			statement.close();
			resultSet.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}

	private boolean userExists(String username) {
		boolean exists = false;
		String databaseQuery = "SELECT * FROM user_credentials WHERE username=\"" + username + "\"";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {

			// Parse result set
			if (resultSet.next()) {
				// found existing user in database
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

	public int checkLevel(String username) {
		int level = 0;
		String databaseQuery = "SELECT access_level FROM user_credentials WHERE username=\"" + username + "\"";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {

			if (resultSet.next()) {
				level = resultSet.getInt("access_level");
			}

			statement.close();
			resultSet.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}

	public int importThreats(JSONBundle threatBundle) throws ParseException {
		// returnCode -1 - database error
		// returnCode 1 - successful import
		int returnCode = -1;

		// prepare local variables for database queries
		String threatQuery = "INSERT INTO threats(threat_id, access_level, name, description, created, modified, type, created_by_ref, spec_version, x_mitre_platforms) values(?,1,?,?,?,?,?,?,?,?)";
		String externalRefQuery = "INSERT INTO external_refs(threat_id, source_name, description, url, external_id) values(?,?,?,?,?)";
		String killChainQuery = "INSERT INTO kill_chain_phases(threat_id, kill_chain_name, phase_name) values(?,?,?)";
		int threatsAdded = 0;
		int externalRefsAdded = 0;
		int killChainsAdded = 0;
		Timestamp sqlCreatedTimestamp;
		Timestamp sqlModifiedTimestamp;

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement threatStatement = connection.prepareStatement(threatQuery);
				PreparedStatement externalRefStatement = connection.prepareStatement(externalRefQuery);
				PreparedStatement killChainStatement = connection.prepareStatement(killChainQuery);) {

			connection.setAutoCommit(false);

			// loop through threats
			for (Threat threat : threatBundle.getObjects()) {
				if (!threatExists(threat)) {
					// inserting values into threat query
					threatStatement.setString(1, threat.getID());
					threatStatement.setString(2, threat.getName());
					threatStatement.setString(3, threat.getDescription());

					// convert to different Date object (required to be from mySQL package)
					sqlCreatedTimestamp = convertToSqlDate(threat.getDateCreated());
					sqlModifiedTimestamp = convertToSqlDate(threat.getDateModified());
					threatStatement.setTimestamp(4, sqlCreatedTimestamp);
					threatStatement.setTimestamp(5, sqlModifiedTimestamp);

					threatStatement.setString(6, threat.getType());
					threatStatement.setString(7, threat.getCreated_by_ref());
					threatStatement.setString(8, threat.getSpecVersion());
					threatStatement.setString(9, threat.getPlatforms());

					// add threat query once all values are set
					threatStatement.addBatch();
					threatsAdded++;

					// inserting values into external_ref query, if exists
					if (threat.getExernalRef() != null) {
						// loop through external references on threat object
						for (ExternalRef exRef : threat.getExernalRef()) {
							externalRefStatement.setString(1, threat.getID());
							externalRefStatement.setString(2, exRef.getSourceName());
							externalRefStatement.setString(3, exRef.getDescription());
							externalRefStatement.setString(4, exRef.getURL());
							externalRefStatement.setString(5, exRef.getExternalId());

							// add threat query once all values are set
							externalRefStatement.addBatch();
							externalRefsAdded++;
						}
					}

					// inserting values into kill_chin_phase query, if exists
					if (threat.getKillChains() != null) {
						// loop through KillChainPhases on threat object
						for (KillChainPhase killChain : threat.getKillChains()) {
							killChainStatement.setString(1, threat.getID());
							killChainStatement.setString(2, killChain.getKillChainName());
							killChainStatement.setString(3, killChain.getPhaseName());

							// add threat query once all values are set
							killChainStatement.addBatch();
							killChainsAdded++;
						}
					}
				}
			}

			// check if any queries were added to each statement, execute if so, close
			// regardless
			if (threatsAdded > 0) {
				threatStatement.executeBatch();
			}
			threatStatement.close();

			if (externalRefsAdded > 0) {
				externalRefStatement.executeBatch();
			}
			externalRefStatement.close();

			if (killChainsAdded > 0) {
				killChainStatement.executeBatch();
			}
			killChainStatement.close();

			connection.commit();
			connection.setAutoCommit(true);
			connection.close();
			returnCode = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Threats not added");
			returnCode = -1;
		}

		return returnCode;
	}

	private boolean threatExists(Threat threat) {
		boolean exists = false;
		String databaseQuery = "SELECT * FROM threats WHERE threat_id=\"" + threat.getID() + "\"";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(databaseQuery);) {

			// Parse result set
			if (resultSet.next()) {
				// found existing user in database
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

	private Timestamp convertToSqlDate(String dateString) throws ParseException {
		if (dateString != null) {
			// handle error in date formatting
			// need to remove
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] dateStringSplit = dateString.split("T", 0);
			String dateStringProper = "";
			int count = 0;
			for (String s : dateStringSplit) {
				if (count == 0) {
					dateStringProper += s;
				} else {
					dateStringProper += " " + s;
				}
				count++;
			}
			dateStringProper.replaceFirst("\\.(\\d*)Z", "");

			return new Timestamp(dateFormatter.parse(dateStringProper).getTime());
		} else {
			return null;
		}
	}

	public ObservableList<String> getDatausers() {
		String databaseQuery = "select *from user_credentials";
		ObservableList<String> list = FXCollections.observableArrayList();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(databaseQuery);) {

			while (rs.next()) {
				list.add(rs.getString("username") + "   Access_level: " + rs.getInt("access_level"));
			}
		} catch (Exception e) {
		}
		return list;
	}

	public ArrayList<Threat> getUserLevelThreats(int access_level) {
		String databaseQuery = "select * from threats where access_level>=\"" + access_level + "\"";
		ArrayList<Threat> threats = new ArrayList<Threat>();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(databaseQuery);) {

			while (rs.next()) {
				Threat threat = new Threat();
				threat.setType(rs.getString("type"));
				threat.setName(rs.getString("name"));
				threat.setCreated(rs.getString("created"));
				threat.setDateModified(rs.getString("modified"));
				threat.setDescription(rs.getString("description"));
				threat.setID(rs.getString("threat_id"));
				threat.setCreatedBy(rs.getString("created_by_ref"));
				threat.setSpecVersion(rs.getString("spec_version"));
				threat.setExernalRef(getThreatExternalRefs(threat.getID()));
				threat.setKillChainPhase(getThreatKillChains(threat.getID()));
				threat.setTags(rs.getString("tag"));
				threat.setComments(rs.getString("comments"));
				ArrayList<String> platforms = new ArrayList<String>();
				platforms.add(rs.getString("x_mitre_platforms"));
				threat.setPlatforms(platforms);
				threats.add(threat);
			}
		} catch (Exception e) {
		}
		
		return threats;
	}
	
	public ArrayList<KillChainPhase> getThreatKillChains(String threatID) {
		ArrayList<KillChainPhase> killChainList = new ArrayList<KillChainPhase>();
		if (threatID != null) {
			String databaseQuery = "select * from kill_chain_phases where threat_id=\"" + threatID + "\"";
			
			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(databaseQuery);) {

				while (rs.next()) {
					KillChainPhase killChain = new KillChainPhase(rs.getString("kill_chain_name"), rs.getString("phase_name"));
					killChainList.add(killChain);
				}
			} catch (Exception e) {
			}
		}
		return killChainList;
	}
	
	public ArrayList<ExternalRef> getThreatExternalRefs(String threatID) {
		ArrayList<ExternalRef> externalRefList = new ArrayList<ExternalRef>();
		if (threatID != null) {
			String databaseQuery = "select * from external_refs where threat_id=\"" + threatID + "\"";
			
			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(databaseQuery);) {

				while (rs.next()) {
					ExternalRef externalRef = new ExternalRef(rs.getString("source_name"), rs.getString("external_id"), rs.getString("url"), rs.getString("description"));
					externalRefList.add(externalRef);
				}
			} catch (Exception e) {
			}
		}
		return externalRefList;
	}
	
	public void removeThreatByID(String ID) {
		String databaseQuery = "DELETE from threats where threat_id=?";
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement statement = connection.prepareStatement(databaseQuery);) {
			statement.setString(1, ID);
			statement.executeUpdate();
		} catch (Exception e) {
		}
	}
	
	public void updateThreat(ArrayList<String> editedThreatInfo, String ID) {
		String databaseQuery = "UPDATE threats set type=?, name=?, x_mitre_platforms=?, tag=?, comments=?, description=? where threat_id=?";
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement statement = connection.prepareStatement(databaseQuery);) {
			statement.setString(1, editedThreatInfo.get(0));
			statement.setString(2, editedThreatInfo.get(1));
			statement.setString(3, editedThreatInfo.get(2));
			statement.setString(4, editedThreatInfo.get(3));
			statement.setString(5, editedThreatInfo.get(4));
			statement.setString(6, editedThreatInfo.get(5));
			statement.setString(7, ID);
			statement.executeUpdate();
		} catch (Exception e) {
		}
	}
}
