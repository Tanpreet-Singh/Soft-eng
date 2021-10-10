import java.util.ArrayList;

public class ThreatCollection {
	private ArrayList<Threat> viewerThreats;
	private ArrayList<Threat> engineerThreats;
	private ArrayList<Threat> adminThreats;
	
	public ThreatCollection() {
		viewerThreats = new ArrayList<Threat>();
		engineerThreats = new ArrayList<Threat>();
		adminThreats = new ArrayList<Threat>();
	}
	
	public ArrayList<Threat> getUserThreats(int accessLevel) {
		ArrayList<Threat> userThreats = new ArrayList<Threat>();
		switch (accessLevel) {
			case 1:
				userThreats.addAll(viewerThreats);
				break;
			case 2:
				userThreats.addAll(viewerThreats);
				userThreats.addAll(engineerThreats);
				break;
			case 3:
				userThreats.addAll(viewerThreats);
				userThreats.addAll(engineerThreats);
				userThreats.addAll(adminThreats);
				break;
		}
		return userThreats;
	}

	public ArrayList<Threat> getViewerThreats() {
		return viewerThreats;
	}

	public void addViewerThreat(Threat threat) {
		viewerThreats.add(threat);
	}
	
	public ArrayList<Threat> getEngineerThreats() {
		return engineerThreats;
	}

	public void addEngineerThreat(Threat threat) {
		engineerThreats.add(threat);
	}
	
	public ArrayList<Threat> getAdminThreats() {
		return adminThreats;
	}

	public void addAdminThreat(Threat threat) {
		adminThreats.add(threat);
	}
	
}
