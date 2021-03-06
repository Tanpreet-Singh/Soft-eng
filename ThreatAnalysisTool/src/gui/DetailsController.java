package gui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailsController extends Controller {

	// GUI components
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
	private TextField tags;
	@FXML
	private TextField platforms;
	@FXML
	private RadioButton adminLevel;
	@FXML
	private RadioButton engineerLevel;
	@FXML
	private RadioButton viewerLevel;
	@FXML
	private ToggleGroup toggle1;
	@FXML
	private DatePicker created;
	@FXML
	private DatePicker modified;
	@FXML
	private TextArea comments;
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

	private boolean darkMode;
	

	// object that is used to populate GUI components
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
			tags.setText(threat.getTags());
			comments.setText(threat.getComments());
			int access_level = threat.getAccessLevel();
			if (access_level == 1) {
				adminLevel.setSelected(true);
			} else if (access_level == 2) {
				engineerLevel.setSelected(true);
			} else if (access_level == 3) {
				viewerLevel.setSelected(true);
			} else {
				
			}
			externalRefList.setItems(externalRefs);
			killChainList.setItems(killChainPhases);

			save.setDisable(true);
			
			type.textProperty().addListener((observable, oldValue, newValue) -> {
			    save.setDisable(false);
			});
			name.textProperty().addListener((observable, oldValue, newValue) -> {
				save.setDisable(false);
			});
			platforms.textProperty().addListener((observable, oldValue, newValue) -> {
				save.setDisable(false);
			});
			tags.textProperty().addListener((observable, oldValue, newValue) -> {
				save.setDisable(false);
			});
			comments.textProperty().addListener((observable, oldValue, newValue) -> {
				save.setDisable(false);
			});
			toggle1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
				save.setDisable(false);
			});
		});
	}

	public void initData(Threat threat, int level) {
		this.setLevel(level);
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
	public void darkModeFunction(ActionEvent event) throws IOException {
		if (!darkMode) {
			theme.setText("Light Mode");
			save.getScene().getStylesheets().add(getClass().getResource("darkmode.css").toExternalForm());
		} else {
			theme.setText("Dark Mode");
			save.getScene().getStylesheets().remove(getClass().getResource("darkmode.css").toExternalForm());
		}

		darkMode = !darkMode;
	}

	@FXML
	public void helpFunction(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
		Scene scene = new Scene(root, 750, 600);

		Stage stg = new Stage();
		stg.setMinWidth(750);
		stg.setMinHeight(600);
		stg.setScene(scene);
		stg.setTitle("Help Page");
		stg.show();

	}

	@FXML
	public void logoutFunction(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout");
		alert.setContentText("Are you sure you want to logout?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			Test m = new Test();
			m.changeScene("login.fxml");
		}
	}

	@FXML
	public void usersFunction(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Users.fxml");
	}

	@FXML
	public void returnFunction(ActionEvent event) throws IOException {
		Test m = new Test(level);
		if (level == 1) {
			m.changeSceneToMain();
		} else if (level == 2) {
			m.changeSceneToEngineer();
		} else if (level == 3) {
			m.changeSceneToViewer();
		}
	}
	
	@FXML
	public void saveThreatEdits(ActionEvent event) {
		ArrayList<String> editedThreatInfo = new ArrayList<String>();
		editedThreatInfo.add(type.getText());
		editedThreatInfo.add(name.getText());
		editedThreatInfo.add(platforms.getText());
		editedThreatInfo.add(tags.getText());
		editedThreatInfo.add(comments.getText());
		editedThreatInfo.add(description.getText());
		
		int level = 3;
		RadioButton radioSelected = (RadioButton) toggle1.getSelectedToggle();
		String radioSelectedText = radioSelected.getText();
		if (radioSelectedText.equals("Admin (1)")) {
			level = 1;
		} else if (radioSelectedText.equals("Engineer (2)")) {
			level = 2;
		}
		
		DatabaseTest database = new DatabaseTest();
		database.updateThreat(editedThreatInfo, id.getText(), level);
	}
	
	
}
