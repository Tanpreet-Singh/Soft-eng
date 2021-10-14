import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.*;


public class ParseFunction {

	public static void main(String[] args) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		byte[] jsonData = Files.readAllBytes(Paths.get("test.txt"));
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);

		for (JsonNode jsonNode : jsonNodeRoot) {
			Threat emp = objectMapper.convertValue(jsonNode, Threat.class);
			if(jsonNode.path("external_references")!= null)
			{
				ExternalRef[] externalref = objectMapper.convertValue(jsonNode.path("external_references"), ExternalRef[].class);
				emp.setExernalRef(externalref);
			}
			if(jsonNode.path("kill_chain_phases")!= null)
			{
				KillChainPhase[] killchain = objectMapper.convertValue(jsonNode.path("kill_chain_phases"), KillChainPhase[].class);
				emp.setKillChainPhase(killchain);
			}
			
	
			System.out.println("Type:\n"+emp.getType());
			System.out.println("Id:\n"+emp.getID());
			System.out.println("Created By:\n"+emp.getCreated_by_ref());
			System.out.println("Created:\n"+emp.getDateCreated());
			System.out.println("Modified:\n"+emp.getDateModified());
			System.out.println("Name:\n"+emp.getName());
			System.out.println("Description:\n"+emp.getDescription());
		
			if(emp.getExernalRef()!=null)
			{
				System.out.println("\nExternal Refernces: ");
				emp.printExternalReferences();
			}
			System.out.println("Permssions:\n"+emp.getx_mitre_permissions_required());
			
			if(emp.getKillChains()!=null)
			{
				System.out.println("\nKill Chain Phases: ");
				emp.printKillChainPhases();
			}
		}
	}

}
