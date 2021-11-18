package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DetailsController extends Controller{

	//GUI components
    @FXML
    private MenuItem help;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuItem theme;
    @FXML
    private MenuItem users;
    @FXML
    private TextField type;
    @FXML
    private TextField name;
    @FXML
    private TextField platforms;
    @FXML
    private DatePicker created;
    @FXML
    private DatePicker modified;
    @FXML
    private TextArea description;
    @FXML
    private Text id;
    @FXML
    private ListView<String> externalRefList;
    @FXML
    private ListView<String> killChainList;
    @FXML
    private Button save;
    @FXML
    private Button returnToMain;
    
    //object that is used to populate GUI components
    private ObservableList<String> externalRefs;
    private ObservableList<String> killChainPhases;

    public DetailsController() {
    
    }
    
    @FXML
    public void initialize() {
    	Platform.runLater(() -> {
    		id.setText(threat.getID());
        	type.setText(threat.getType());
        	name.setText(threat.getName());
        	platforms.setText(threat.getPlatforms());
        	created.setPromptText(threat.getDateCreated());
        	modified.setPromptText(threat.getDateModified());
        	description.setText(threat.getDescription());
        	
        	externalRefList.setItems(externalRefs);
        	killChainList.setItems(killChainPhases);
        });
    }
    
    public void initData(Threat threat) {
    	setThreat(threat);
    	externalRefs = FXCollections.observableArrayList();
    	killChainPhases = FXCollections.observableArrayList();
    	
    	if (threat.getExernalRef() != null) {
    		for (ExternalRef exRef : threat.getExernalRef()) {
        		if (exRef.printRef() != null) {
        			externalRefs.add(exRef.printRef());
        		}
        	}
    	}
    	
    	if (threat.getKillChains() != null) {
    		for (KillChainPhase killChain : threat.getKillChains()) {
        		if (killChain.printKillChain() != null) {
        			killChainPhases.add(killChain.printKillChain());
        		}
        	}
    	}
    }
    
    @FXML
    void darkModeFunction(ActionEvent event) {

    }

    @FXML
    void helpFunction(ActionEvent event) {

    }

    @FXML
    void logoutFunction(ActionEvent event) {

    }

    @FXML
    void usersFunction(ActionEvent event) {

    }

}
