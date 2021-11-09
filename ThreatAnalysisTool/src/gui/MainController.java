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
	private MenuItem help;
	@FXML
	private MenuItem users;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem theme;
	@FXML
	private Button importButton;
	@FXML
	private ListView<String> listView;
	@FXML
	private TextField searchField;
	
	private boolean darkMode;
	private ParseFunction parser;
	//private JSONBundle threatBundle;
	private ObservableList<String> threats;
	private FilteredList<String> threatList;
	
	
	public MainController() throws IOException {
		darkMode = false;
		parser = new ParseFunction();
		threats = FXCollections.observableArrayList();
		for (Threat threat : parser.parseJSON().getObjects()) {
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
		if (!darkMode) {
			theme.setText("Light Mode");
			add.getScene().getStylesheets().add(getClass().getResource("darkmode.css").toExternalForm());
		}else {
			theme.setText("Dark Mode");
			add.getScene().getStylesheets().remove(getClass().getResource("darkmode.css").toExternalForm());
		}
		
		darkMode = !darkMode;
	}
}