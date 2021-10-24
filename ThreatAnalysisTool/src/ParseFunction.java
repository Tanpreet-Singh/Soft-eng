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
		String pass = "USusus1!";
		String user = "us2";
		try {
			//Attempt connection
			Connection connection = DriverManager.getConnection(url, user, pass);
			System.out.println("connected");
			
			//Querires for known procedures
			String query = "{CALL getThreat(?)}";
			String query2 = "{CALL addThreat(?,?,?,?,?,?,?,?,?)}";
			CallableStatement stmt = connection.prepareCall(query);
			

			//Loop through every Threat objects in JSON file
			for (Threat threat : bundle.getObjects()) {
				System.out.println("---Threat Instance---");
				System.out.println(threat);
				
				//Check if threat ID exists
				stmt.setString(1, threat.getID());
				stmt.execute();
				ResultSet rs = stmt.executeQuery();
				rs.next();
				
				//Check if threat object exists. if not, new entry
				if(rs.next() == false) {
					System.out.println("new entry");
					
					//New call function for addThreat procedure
					CallableStatement stmt2 = connection.prepareCall(query2);
					
					//Set values/parameters before executing call
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
					
					//execute call
					stmt2.execute();
					
					
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
		
		
		
//		for (Threat threat : bundle.getObjects()) {
//			System.out.println(threat.getID());
//		}
		
		
	}

}
