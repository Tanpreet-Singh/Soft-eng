import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Threat objects which can be of type: Threat-Actor, Campaigns, Intrusion Sets.
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
	
	ExternalRef external_references;
	@JsonProperty("x_mitre_permissions_required")
	private List<String> x_mitre_permissions_required;
	@JsonProperty("spec_version")
	private String spec_version;
	
	private KillChainPhase kill_chain_phases;

	Threat() {
	}

	String getType()
	{
		return type;
	}

	String getID()
	{
		return id;
	}

	String getCreated_by_ref()
	{
		return created_by_ref;
	}	

	String getDateCreated()
	{
		return created;
	}

	String getDateModified()
	{
		return modified;
	}

	String getName()
	{
		return name;
	}

	String getDescription()
	{
		return description;
	}

	ExternalRef getExernalRef()
	{
		return external_references;
	}

	List<String> getx_mitre_permissions_required()
	{
		return x_mitre_permissions_required;
	}

	String getSpecVersion()
	{
		return spec_version;
	}

	KillChainPhase getKillChains()
	{
		return kill_chain_phases;
	}

	void setType(String type)
	{
		this.type = type;
	}

	void setID(String id)
	{
		this.id = id;
	}

	void setCreatedBy(String created_by_ref)
	{
		this.created_by_ref = created_by_ref;
	}

	void setCreated(String created)
	{
		this.created = created;
	}

	void setDateModified(String modified)
	{
		this.modified = modified;
	}

	void setKillChainPhase(KillChainPhase kill_chain_phases)
	{
		this.kill_chain_phases = kill_chain_phases;
	}

	void setName(String name)
	{
		this.name = name;
	}

	void setDescription(String description)
	{
		this.description = description;
	}

	void setExernalRef(ExternalRef external_references)
	{
		this.external_references = external_references;
	}

	void setx_mitre_permissions_required(List<String> x_mitre_permissions_required )
	{
		this.x_mitre_permissions_required =  x_mitre_permissions_required;
	}

	void setSpecVersion(String spec_version)
	{
		this.spec_version =  spec_version;
	}

}
