package gui;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Threat objects which can be of type: Threat-Actor, Campaigns, Intrusion Sets.
 * These objects hold the values of individual threats within the JSON files that have been parsed.
 * These will be held in an array, and be used to populate the database and manipulate GUI.
 * 
 * @author
 * @version 10/13/2021
 * @param <KillChainPhase>
 */
class Threat
{
	@JsonProperty("type")
	private String type;
	@JsonProperty("id")
	private String id;
	@JsonProperty("created_by_ref")
	private String created_by_ref;
	@JsonProperty("created")
	private String created;
	@JsonProperty("modified")
	private String modified;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("external_references")
	private ArrayList<ExternalRef> external_references;
	@JsonProperty("kill_chain_phases")
	private ArrayList<KillChainPhase> kill_chain_phases;
	@JsonProperty("x_mitre_permissions_required")
	private List<String> x_mitre_permissions_required;
	@JsonProperty("spec_version")
	private String spec_version;
	

	Threat() {
	}

    /**
	 * Accessor method for field type
	 * 
	 * @return String value of field type
	 */
	String getType()
	{
		return type;
	}

	/**
	 * Accessor method for field id
	 * 
	 * @return String value of field id
	 */
	String getID()
	{
		return id;
	}

	/**
	 * Accessor method for field created_by_ref
	 * 
	 * @return String value of field created_by_ref
	 */
	String getCreated_by_ref()
	{
		return created_by_ref;
	}	

	/**
	 * Accessor method for field created
	 * 
	 * @return String value of created field
	 */
	String getDateCreated()
	{
		return created;
	}
	
	/**
	 * Accessor method for field modified
	 * 
	 * @return String value of modified field
	 */
	String getDateModified()
	{
		return modified;
	}

	/**
	 * Accessor method for field name
	 * 
	 * @return String value of the field name
	 */
	String getName()
	{
		return name;
	}

	/**
	 * Accessor method for description field
	 * 
	 * @return String value of description field
	 */
	String getDescription()
	{
		return description;
	}

	/**
	 * Accessor method for external_references field
	 * 
	 * @return ArrayList of external_references
	 */
	ArrayList<ExternalRef> getExernalRef()
	{
		return external_references;
	}

	/**
	 * Accessor method for x_mitre_permissions_required field
	 * 
	 * @return The list of String values stored in the x_mitre_permissions_required field
	 */
	List<String> getx_mitre_permissions_required()
	{
		return x_mitre_permissions_required;
	}

	/**
	 * Accessor method for spec_version field
	 * 
	 * @return String value of spec_version field
	 */
	String getSpecVersion()
	{
		return spec_version;
	}

	/**
	 * Accessor method for kill_chain_phases field
	 * 
	 * @return ArrayList of kill_chain_phases field
	 */
	ArrayList<KillChainPhase> getKillChains()
	{
		return kill_chain_phases;
	}

	/**
	 * Mutator method for type field
	 * 
	 * @param	type	new String value to assign to type field
	 */
	void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Mutator method for id field
	 * 
	 * @param	id	new String value to assign to id field
	 */
	void setID(String id)
	{
		this.id = id;
	}

	/**
	 * Mutator method for created_by_ref field
	 * 
	 * @param	created_by_ref	new String value to assign to created_by_ref field
	 */
	void setCreatedBy(String created_by_ref)
	{
		this.created_by_ref = created_by_ref;
	}

	/**
	 * Mutator method for created field
	 * 
	 * @param	created	new String value to assign to created field
	 */
	void setCreated(String created)
	{
		this.created = created;
	}

	/**
	 * Mutator method for modified field
	 * 
	 * @param	modified	new String value to assign to modified field
	 */
	void setDateModified(String modified)
	{
		this.modified = modified;
	}

	/**
	 * Mutator method for kill_chain_phases field
	 * 
	 * @param	kill_chain_phases	new ArrayList<KillChainPhase> to assign to kill_chain_phases field
	 */
	void setKillChainPhase(ArrayList<KillChainPhase> kill_chain_phases)
	{
		this.kill_chain_phases = kill_chain_phases;
	}

	/**
	 * Mutator method for name field
	 * 
	 * @param	name	new String value to assign to name field
	 */
	void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Mutator method for description field
	 * 
	 * @param	description	new String value to assign to description field
	 */
	void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Mutator method for external_references field
	 * 
	 * @param	external_references	new ArrayList<ExternalRef> to assign to external_references field
	 */
	void setExernalRef(ArrayList<ExternalRef> external_references)
	{
		this.external_references = external_references;
	}

	/**
	 * Mutator method for x_mitre_permissions_required field
	 * 
	 * @param	x_mitre_permissions_required	new List<String> to assign to x_mitre_persmissions_required field
	 */
	void setx_mitre_permissions_required(List<String> x_mitre_permissions_required )
	{
		this.x_mitre_permissions_required =  x_mitre_permissions_required;
	}

	/**
	 * Mutator method for spec_version field
	 * 
	 * @param	spec_version	new String value to assign to spec_version field
	 */
	void setSpecVersion(String spec_version)
	{
		this.spec_version =  spec_version;
	}

	/**
	 * Print method for external_references field
	 * Nicely formats the Source Name, External ID, and URL of all external references in a Threat Object
	 * 
	 */
	void printExternalReferences()
	{
		if(external_references!= null)
		{
			for (ExternalRef externalRef : external_references) {
				System.out.println("\nSource Name:   "+externalRef.getSourceName());
				System.out.println("External Id:   "+externalRef.getExternalId());
				System.out.println("URL:           "+externalRef.getURL()+"\n");
			
			}
		}
	}

	/**
	 * Print method for kill_chain_phases field
	 * Nicely formats the Kill-Chain-Phase, and Phase Name of all kill_chain_phases in a Threat Object
	 * 
	 */
	void printKillChainPhases()
	{
		if(kill_chain_phases!=null)
		{
			for (KillChainPhase kill : kill_chain_phases) {
				System.out.println("\nKill Chain Phase:   "+kill.getKillChainPhase());
				System.out.println("Phase Name:         "+kill.getPhaseName()+"\n");
			}
		}
	}
	
	@Override
	public String toString() {
		String outputString = "Threat Name:    " + getName() + "\n";
		outputString += "Threat ID:      " + getID() + "\n";
		return outputString;
		
	}
}
