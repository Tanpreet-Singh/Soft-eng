package gui;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KillChainPhase {
	@JsonProperty("kill_chain_name")
	private String kill_chain_name;
	@JsonProperty("phase_name")
	private String phase_name;

	public KillChainPhase() {
		kill_chain_name = "Not Specified";
		phase_name = "Not Specified";
	}

	public KillChainPhase(String kill_chain_name, String phase_name) {
		this.kill_chain_name = kill_chain_name;
		this.phase_name = phase_name;
	}

	String getKillChainName() {
		return kill_chain_name;
	}

	String getPhaseName() {
		return phase_name;
	}

	void setKillChainPhase(String kill_chain_phase) {
		this.kill_chain_name = kill_chain_phase;
	}

	void setExternalId(String phase_name) {
		this.phase_name = phase_name;
	}

	public String printKillChain() {
		String outputString = "";

		if (kill_chain_name != null) {
			outputString += "Kill Chain Name: " + kill_chain_name + "\n";
		}
		if (phase_name != null) {
			outputString += "Phase Name: " + phase_name + "\n";
		}

		if (outputString.equals("")) {
			outputString = "Not specified";
		}

		return outputString;
	}


	public String toString() {
		String res = "";
		if (kill_chain_name != null && phase_name == null) {
			res = "Phase name Not specified " + kill_chain_name;
		} else if (phase_name != null && kill_chain_name == null) {
			res = "Kill chain Not specified " + phase_name;
		}
		else if (phase_name == null && kill_chain_name == null) {
			res = "Not specified";
		}
		else {
			res = kill_chain_name + ", " + phase_name;
		}
		return res;
	}
}