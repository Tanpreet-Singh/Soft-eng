package gui;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONBundle {
	@JsonProperty("type")
	private String type;
	@JsonProperty("id")
	private String id;
	@JsonProperty("spec_version")
	private String spec_version;
	private ArrayList<Threat> objects;
	
	JSONBundle() {
		
	}

	public ArrayList<Threat> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<Threat> objects) {
		this.objects = objects;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSpecVersion() {
		return spec_version;
	}
	
	public void setSpecVersion(String spec_version) {
		this.spec_version = spec_version;
	}
}
