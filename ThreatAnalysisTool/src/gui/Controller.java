package gui;


public class Controller {
	public int level;
	public Threat threat;
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setThreat(Threat threat) {
		this.threat = threat;
	}
	
	public Threat getThreat() {
		return threat;
	}
}
