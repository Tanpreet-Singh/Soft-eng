import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalRef
{
	@JsonProperty("source_name")
	String source_name;
	@JsonProperty("external_id")
	String external_id;
	@JsonProperty("url")
	String url;
	
	public ExternalRef()
	{
		
	}
	
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
