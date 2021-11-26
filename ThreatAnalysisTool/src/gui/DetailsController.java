package gui;

import java.io.IOException;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

			externalRefList.setItems(externalRefs);
			killChainList.setItems(killChainPhases);

			save.setDisable(true);
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
		Scene scene = new Scene(root, 700, 500);

		Stage stg = new Stage();
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
		m.changeScene("Main.fxml");
	}
}
