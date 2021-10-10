import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.*;


public class ParseFunction {

	public static void main(String[] args) throws IOException {
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("test.txt"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//convert json string to object
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
		Threat emp = objectMapper.convertValue(jsonNodeRoot, Threat.class);

		String created_by = jsonNodeRoot.get("created_by_ref").toString();
		emp.setCreatedBy(created_by);

		String modified = jsonNodeRoot.get("modified").toString();
		emp.setDateModified(modified);

		JsonNode jsonNodeExRef = jsonNodeRoot.get("external_references");
		String[] string = jsonNodeExRef.toString().split(",");
		ExternalRef ref = new ExternalRef(string[0], string[1], string[2]);
		emp.setExernalRef(ref);
		
		JsonNode jsonNodeKillChain = jsonNodeRoot.get("kill_chain_phases");
		String[] kill = jsonNodeKillChain.toString().split(",");
		KillChainPhase kill_chain = new KillChainPhase(kill[0], kill[1]);
		emp.setKillChainPhase(kill_chain);
		
		System.out.println("Type:\n"+emp.getType());
		System.out.println("Id:\n"+emp.getID());
		System.out.println("Created By:\n"+emp.getCreated_by_ref());
		System.out.println("Created:\n"+emp.getDateCreated());
		System.out.println("Modified:\n"+emp.getDateModified());
		System.out.println("Name:\n"+emp.getName());
		System.out.println("Description:\n"+emp.getDescription());
		System.out.println("Source Name:\n"+emp.getExernalRef().getSourceName());
		System.out.println("Url:\n"+emp.getExernalRef().getURL());
		System.out.println("External Id:\n"+emp.getExernalRef().getExternalId());
		System.out.println("Type:\n"+emp.getx_mitre_permissions_required());
		System.out.println("Kill Chain Name:"+emp.getKillChains().getKillChainPhase());
		System.out.println("Phase name:"+emp.getKillChains().getPhaseName());
		
	}

}
