package gui;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalRef
{
	@JsonProperty("source_name")
	private String source_name;
	@JsonProperty("external_id")
	private String external_id;
	@JsonProperty("url")
	private String url;
	@JsonProperty("description")
	private String description;
	
	public ExternalRef()
	{
		
	}
	
	public ExternalRef(String source_name, String external_id, String url, String description)
	{
		this.source_name = source_name;
		this.external_id = external_id;
		this.url = url;
		this.description = description;
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
	
	String getDescription()
	{
		return description;
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
	
	void setDescription(String description)
	{
		this.description = description;
	}
}
