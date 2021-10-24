import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.prefs.Preferences;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Type;
import com.fasterxml.jackson.databind.*;

/**
 * This class will pull data from a text/JSON file, and will parse the information and map it onto Threat objects.
 * These objects will later be used to populate the database and manipulate GUI components. 
 * 
 * @author 
 * @version 10/13/2021
 */
public class ParseFunction {
	
	private static Preferences preferences = Preferences.userNodeForPackage(ParseFunction.class);
	
	public static void main(String[] args) throws IOException {
//		ThreatCollection collection = new ThreatCollection();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		byte[] jsonData = Files.readAllBytes(Paths.get("bigTest.json"));
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
		
		JSONBundle bundle = objectMapper.convertValue(jsonNodeRoot, JSONBundle.class);
		
		
		
		//Connect to MySQL Database
		String url = "jdbc:mysql://13.82.146.37:3306/THREAT_TOOL_ANALYSIS";
		//String pass = "USusus1!";
		//String user = "us2";
		
		//setCred(user,pass);
		
		try {
			//Attempt connection
			Connection connection = DriverManager.getConnection(url, getUsername(), getPassword());
			System.out.println("connected");
			
			//Querires for known procedures
			String query = "CALL getThreat(?)";
			String query2 = "CALL addThreat(?,?,?,?,?,?,?,?,?)";
			String query3 = "CALL addExternalRef(?,?,?,?,?)";
			String query4 = "CALL addKillChainPhase(?,?,?)";
			CallableStatement stmt = connection.prepareCall(query);
			CallableStatement stmt2 = connection.prepareCall(query2);
			CallableStatement stmt3 = connection.prepareCall(query3);
			CallableStatement stmt4 = connection.prepareCall(query4);
			
			
			//Loop through every Threat objects in JSON file
			for (Threat threat : bundle.getObjects().subList(1, 10)) {
				System.out.println("---Threat Instance---");
				System.out.println(threat);
				
				stmt.setString(1, threat.getID());
				stmt.execute();
				ResultSet rs = stmt.executeQuery();
				//System.out.println(rs.getString("ID"));
				if(rs.next() == false) {

					System.out.println("new Threat entry");
					
					stmt2.setString(1, threat.getID());
					stmt2.setString(2, "VIEWER");
					stmt2.setString(3, threat.getName());
					stmt2.setString(4, threat.getDescription());
					String modifiedDateCreated = threat.getDateCreated().substring(0,10) + " " + threat.getDateCreated().substring(11,19);
					stmt2.setString(5, modifiedDateCreated);
					String modifiedDateModified = threat.getDateModified().substring(0,10) + " " + threat.getDateModified().substring(11,19);
					stmt2.setString(6, modifiedDateModified);
					stmt2.setString(7, threat.getType());
					stmt2.setString(8, threat.getCreated_by_ref());
					stmt2.setString(9, "1");
					
					stmt2.execute();
					
					if(threat.getExernalRef()!= null)
					{
						for (ExternalRef externalRef : threat.getExernalRef()) {
							System.out.println("new ExternalRef entry");
							stmt3.setString(1,threat.getID());
							stmt3.setString(2,externalRef.getSourceName());
							stmt3.setString(3,"description");
							stmt3.setString(4,externalRef.getURL());
							stmt3.setString(5,externalRef.getExternalId());
							stmt3.execute();
							
//							System.out.println("\nSource Name:   "+externalRef.getSourceName());
//							System.out.println("External Id:   "+externalRef.getExternalId());
//							System.out.println("URL:           "+externalRef.getURL()+"\n");
						
						}
					}
					
					if(threat.getKillChains()!=null)
					{
						for (KillChainPhase kill : threat.getKillChains()) {
							System.out.println("new KillChain entry");
							stmt4.setString(1,threat.getID());
							stmt4.setString(2,kill.getKillChainPhase());
							stmt4.setString(3,kill.getPhaseName());
							stmt4.execute();
							
//							System.out.println("\nKill Chain Phase:   "+kill.getKillChainPhase());
//							System.out.println("Phase Name:         "+kill.getPhaseName()+"\n");
						}
					}
				}
				
//				System.out.println("\n-----Threat Info-----");
//				System.out.println("Threat Type:    " + threat.getType());
//				System.out.println("Threat ID:      " + threat.getID());
//				System.out.println("Created by Ref: " + threat.getCreated_by_ref());
//				System.out.println("Date Created:   " + threat.getDateCreated());
//				System.out.println("Date Modified:  " + threat.getDateModified());
//				System.out.println("Threat Name:    " + threat.getName());
//				System.out.println("\nDescription:    \n" + threat.getDescription());
//				System.out.println("Spec Version:   " + threat.getCreated_by_ref());
//				System.out.println("   ------External References------   ");
//				threat.printExternalReferences();
//				System.out.println("   ------Kill Chain Phases------   ");
//				threat.printKillChainPhases();
//				System.out.println("---------------------\n\n");
			}
			
		} catch(SQLException e) {
			System.out.println("MySQL Error");
			e.printStackTrace();
		}
		
	}
	
//	public static void setCred(String u, String p) {
//		preferences.put("db_username", u);
//		preferences.put("db_password", p);
//		
//	}
	
	public static String getUsername() {
	    return preferences.get("db_username", null);
	  }

	  public static String getPassword() {
	    return preferences.get("db_password", null);
	  }


}
