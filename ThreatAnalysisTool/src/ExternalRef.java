
public class ExternalRef
{
	String sourceName;
	String externalId;
	String url;
	
	public ExternalRef()
	{
		sourceName = "";
		externalId = "";
		url = "";
	}
	
	String getSourceName()
	{
		return sourceName;
	}
	
	String getExternalId()
	{
		return externalId;
	}
	
	String getURL()
	{
		return url;
	}
	
	void setSourceName(String sourceName)
	{
		this.sourceName = sourceName;
	}
	
	void setExternalId(String externalId)
	{
		this.externalId = externalId;
	}
	
	void setURL(String url)
	{
		this.url = url;
	}
}
