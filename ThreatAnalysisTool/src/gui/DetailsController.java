package gui;

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

public class DetailsController {

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
    private Threat threat;
    private ObservableList<String> externalRefs;
    private ObservableList<String> killChainPhases;

    public DetailsController(Threat threat) {
    	this.threat = threat;
    	externalRefs = FXCollections.observableArrayList();
    	killChainPhases = FXCollections.observableArrayList();
    	
    	for (ExternalRef exRef : threat.getExernalRef()) {
    		if (exRef.printRef() != null) {
    			externalRefs.add(exRef.printRef());
    		}
    	}
    }
    
    public void initialize() {
    	id.setText(threat.getID());
    	type.setPromptText(threat.getType());
    	name.setPromptText(threat.getName());
    	platforms.setPromptText(threat.getPlatforms());
    	created.setPromptText(threat.getDateCreated());
    	modified.setPromptText(threat.getDateModified());
    	description.setPromptText(threat.getDescription());
    	
    	externalRefList.setItems(externalRefs);
    	killChainList.setItems(killChainPhases);
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
