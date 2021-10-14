import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.*;


public class ParseFunction {
	
	public static void main(String[] args) throws IOException {
//		ThreatCollection collection = new ThreatCollection();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		byte[] jsonData = Files.readAllBytes(Paths.get("bigTest.json"));
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
		
		JSONBundle bundle = objectMapper.convertValue(jsonNodeRoot, JSONBundle.class);

//		for (Threat threat : bundle.getObjects()) {
//			System.out.println(threat.getID());
//		}
		
		for (Threat threat : bundle.getObjects().subList(1, 5)) {
			System.out.println("---Threat Instance---");
			System.out.println(threat);
			System.out.println("\n-----Threat Info-----");
			System.out.println("Threat Type:    " + threat.getType());
			System.out.println("Threat ID:      " + threat.getID());
			System.out.println("Created by Ref: " + threat.getCreated_by_ref());
			System.out.println("Date Created:   " + threat.getDateCreated());
			System.out.println("Date Modified:  " + threat.getDateModified());
			System.out.println("Threat Name:    " + threat.getName());
			System.out.println("\nDescription:    \n" + threat.getDescription());
			System.out.println("Spec Version:   " + threat.getCreated_by_ref());
			System.out.println("   ------External References------   ");
			threat.printExternalReferences();
			System.out.println("   ------Kill Chain Phases------   ");
			threat.printKillChainPhases();
			System.out.println("---------------------");
		}
	}

}
