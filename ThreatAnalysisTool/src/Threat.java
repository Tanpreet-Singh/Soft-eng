import java.util.List;

/**
 * The Threat objects which can be of type: Threat-Actor, Campaigns, Intrusion Sets.
 */
class Threat
{
	private String type;
	private String specVersion;
	private String id;
	private String created;
	private String modified;
	private String name;
	private String description;
	private List<ThreatActorTypes> thisListTypes;
	private List<ThreatRoles> roles;
	private List<ThreatGoals> goals;
	private boolean isFamily;
	private List<KillChain> killChainPhases;

	Threat() {}

	String getType()
	{
		return type;
	}

	String getVersion()
	{
		return specVersion;
	}

	String getID()
	{
		return id;
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

	List<ThreatActorTypes> getActorTypes()
	{
		return thisListTypes;
	}

	List<ThreatRoles> getRoles()
	{
		return roles;
	}

	List<ThreatGoals> getGoals()
	{
		return goals;
	}

	boolean isThisAFamily()
	{
		return isFamily;
	}

	List<KillChain> getKillChains()
	{
		return killChainPhases;
	}
}
