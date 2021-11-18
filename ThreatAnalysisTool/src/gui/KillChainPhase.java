package gui;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KillChainPhase {
	@JsonProperty("kill_chain_name")
    private String kill_chain_name;
	@JsonProperty("phase_name")
    private String phase_name;

	public KillChainPhase()
	{

	}
    
	public KillChainPhase(String kill_chain_name, String phase_name)
    {
        this.kill_chain_name = kill_chain_name;
        this.phase_name = phase_name;
    }

    String getKillChainName()
	{
		return kill_chain_name;
	}
	
	String getPhaseName()
	{
		return phase_name;
	}
	
	void setKillChainPhase(String kill_chain_phase)
	{
		this.kill_chain_name = kill_chain_phase;
	}
	
	void setExternalId(String phase_name)
	{
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
			outputString = null;
		}
		
		return outputString;
	}
	
	
}
