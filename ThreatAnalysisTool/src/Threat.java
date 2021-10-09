import java.util.List;

/**
 * The Threat objects which can be of type: Threat-Actor, Campaigns, Intrusion Sets.
 */
class Threat
{
	private String type;
	private String id;
	private String created_by_ref;
	private String created;
	private String modified;
	private String name;
	private String description;
	private String external_reference;
	private List<String> x_mitre_permissions_required;
	private String spec_version;
	private List<KillChain> kill_chain_phases;

	Threat() {}

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

	String getExernalRef()
	{
		return external_reference;
	}

	List<String> getx_mitre_permissions_required()
	{
		return x_mitre_permissions_required;
	}

	String getSpecVersion()
	{
		return spec_version;
	}

	List<KillChain> getKillChains()
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

	void setCreated(String created)
	{
		this.created = created;
	}

	void setDateModified(String modified)
	{
		this.modified = modified;
	}

	void setName(String name)
	{
		this.name = name;
	}

	void setDescription(String description)
	{
		this.description = description;
	}

	void setExernalRef(String external_reference)
	{
		this.external_reference = external_reference;
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
