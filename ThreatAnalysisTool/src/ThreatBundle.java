import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the JSON file that contains all the threat object bundled together
 * as one collection.
 */
public class ThreatBundle
{
	private String name;
	private int id;
	private List<Threat> threatCollection;  // This is the collection inside the bundle

	public ThreatBundle(String name, int id)
	{
		this.name = name;
		this.id = id;
		threatCollection = new ArrayList<Threat>();
	}

	public String getName()
	{
		return name;
	}

	public int getID()
	{
		return id;
	}

	public void add(Threat threatObj)
	{
		threatCollection.add(threatObj);
	}
}
