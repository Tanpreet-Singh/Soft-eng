import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.*;


public class ParseFunction {

	public static void main(String[] args) throws IOException {


		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("test.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		
		//convert json string to object
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
		Threat emp = objectMapper.convertValue(jsonNodeRoot, Threat.class);

		ExternalRef[] externalref = objectMapper.convertValue(jsonNodeRoot.path("external_references"), ExternalRef[].class);
		//ExternalRef ref = new ExternalRef(externalref[0].getSourceName(),externalref[0].getExternalId(), externalref[0].getURL());
		emp.setExernalRef(externalref);

		KillChainPhase[] killchain = objectMapper.convertValue(jsonNodeRoot.path("kill_chain_phases"), KillChainPhase[].class);
		
		//KillChainPhase kill = new KillChainPhase(killchain[0].getKillChainPhase(), killchain[0].getPhaseName());
		emp.setKillChainPhase(killchain);
	
		System.out.println("Type:\n"+emp.getType());
		System.out.println("Id:\n"+emp.getID());
		System.out.println("Created By:\n"+emp.getCreated_by_ref());
		System.out.println("Created:\n"+emp.getDateCreated());
		System.out.println("Modified:\n"+emp.getDateModified());
		System.out.println("Name:\n"+emp.getName());
		System.out.println("Description:\n"+emp.getDescription());
		System.out.println("\nExternal Refernces: ");
		emp.printExternalReferences();
		System.out.println("Permssions:\n"+emp.getx_mitre_permissions_required());
		System.out.println("\nKill Chain Phases: ");
		emp.printKillChainPhases();
		
	}

}
