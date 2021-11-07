package gui;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.*;

/**
 * This class will pull data from a text/JSON file, and will parse the information and map it onto Threat objects.
 * These objects will later be used to populate the database and manipulate GUI components. 
 * 
 * @author 
 * @version 10/13/2021
 */
public class ParseFunction {	

	 public JSONBundle parseJSON() throws IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		 byte[] jsonData = Files.readAllBytes(Paths.get("bigTest.json"));
		 JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
	
		 JSONBundle bundle = objectMapper.convertValue(jsonNodeRoot, JSONBundle.class);
//		 Left this block of code to demonstrate how to loop through bundle for objects
//		 for (Threat threat : bundle.getObjects().subList(1, 10)) {
//				System.out.println("---Threat Info---");
//				System.out.println(threat);
//				System.out.println("   ------External References------   ");
//				threat.printExternalReferences();
//				System.out.println("   ------Kill Chain Phases------   ");
//				threat.printKillChainPhases();
//				System.out.println("---------------------\n\n");
//			}
	
		 return bundle;
	 }
	 
	 public JSONBundle parseJSON(String pathToFile) throws IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		 byte[] jsonData = Files.readAllBytes(Paths.get(pathToFile));
		 JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
	
		 JSONBundle bundle = objectMapper.convertValue(jsonNodeRoot, JSONBundle.class);
//		 Left this block of code to demonstrate how to loop through threat bundle
//		 for (Threat threat : bundle.getObjects().subList(1, 10)) {
//				System.out.println("---Threat Info---");
//				System.out.println(threat);
//				System.out.println("   ------External References------   ");
//				threat.printExternalReferences();
//				System.out.println("   ------Kill Chain Phases------   ");
//				threat.printKillChainPhases();
//				System.out.println("---------------------\n\n");
//			}
	
		 return bundle;
	  }
}
