package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.FileDialog;
import java.awt.Frame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class MainController {

	@FXML
	private Button add;
	@FXML
	private Button delete;
	@FXML
	private Button edit;
	@FXML
	private Button genPdf;
	@FXML
	private Button importButton;
	@FXML
	private Button addToPDF;
	@FXML
	private Button remove;
	@FXML
	private MenuItem help;
	@FXML
	private MenuItem users;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem theme;
	@FXML
	private ListView<String> listView;
	@FXML
	private ListView<String> listViewPDF;
	@FXML
	private TextField searchField;
	
	private boolean darkMode;
	private ParseFunction parser;
	private JSONBundle threatBundle;
	private ObservableList<String> threats;
	private FilteredList<String> threatList;
	
	ObservableList<String> genPDFThreats;
	
	
	public MainController() throws IOException {
		genPDFThreats = FXCollections.observableArrayList();
		
		darkMode = false;
		parser = new ParseFunction();
		threatBundle = parser.parseJSON();
		threats = FXCollections.observableArrayList();
		for (Threat threat : threatBundle.getObjects()) {
			threats.add(threat.toString());
		}

		threatList = new FilteredList<>(threats);
	}

	@FXML
	public void initialize() throws IOException {
		//initialize listView, items and click event
		listView.setItems(threatList);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				threatList.setPredicate(null);
			} else {
				final String searchString = newValue.toUpperCase();
				threatList.setPredicate(s -> s.toUpperCase().contains(searchString));
			}
		});
		
		
	}

	@FXML
	public void logoutFunction(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("login.fxml");
	}

	@FXML
	public void importJSON(ActionEvent event) throws IOException, ParseException, SQLException {
		FileDialog fileDialog = new FileDialog(new Frame(), "Select JSON file", FileDialog.LOAD);
		fileDialog.setVisible(true);

		if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
			String pathToImportFile = fileDialog.getDirectory() + fileDialog.getFile();
			DatabaseTest databaseConnection = new DatabaseTest();
			databaseConnection.importThreats(parser.parseJSON(pathToImportFile));

		}
	}

	@FXML
	public void usersFunction(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Users.fxml");
	}

	@FXML
	public void helpFunction(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Help.fxml");
	}
	
	@FXML
	public void darkModeFunction(ActionEvent event) throws IOException {
		if (!darkMode) {
			theme.setText("Light Mode");
			add.getScene().getStylesheets().add(getClass().getResource("darkmode.css").toExternalForm());
		}else {
			theme.setText("Dark Mode");
			add.getScene().getStylesheets().remove(getClass().getResource("darkmode.css").toExternalForm());
		}
		
		darkMode = !darkMode;
	}
	
	@FXML
	public void addToPdfFunction(ActionEvent event) throws IOException {
		for (String selectedThreat: listView.getSelectionModel().getSelectedItems()) {
			Threat threat = getThreatFromString(selectedThreat);
			String threatName = threat.getName();
			if (!genPDFThreats.contains(threatName)) {
				genPDFThreats.add(threatName);
			}
		}
		listViewPDF.setItems(genPDFThreats);
	}
	
	@FXML
	public void removeFromPdfFunction(ActionEvent event) throws IOException {
		String selectedThreat = listViewPDF.getSelectionModel().getSelectedItem();
		genPDFThreats.remove(selectedThreat);
		
		listViewPDF.setItems(genPDFThreats);
	}
	
	public Threat getThreatFromString(String threatString) {
		String threatID = "";
		Threat returnThreat = null;
		for (String string : threatString.split("\\r?\\n")) {
			if (string.contains("   ID:           ")) {
				threatID = string.substring(17);
			}
		}
		
		for (Threat threat : threatBundle.getObjects()) {
			if (threat.getID().equals(threatID)) {
				returnThreat = threat;
			}
		}
		return returnThreat;
	}
}