
public class ExternalRef
{
	String source_name;
	String external_id;
	String url;
	
	public ExternalRef(String source_name, String external_id, String url)
	{
		this.source_name = source_name;
		this.external_id = external_id;
		this.url = url;
	}
	
	String getSourceName()
	{
		return source_name;
	}
	
	String getExternalId()
	{
		return external_id;
	}
	
	String getURL()
	{
		return url;
	}
	
	void setSourceName(String sourceName)
	{
		this.source_name = sourceName;
	}
	
	void setExternalId(String externalId)
	{
		this.external_id = externalId;
	}
	
	void setURL(String url)
	{
		this.url = url;
	}
}
