package gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
<<<<<<< Updated upstream:ThreatAnalysisTool/src/gui/MainPage.java
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
=======
import javafx.scene.control.Menu;
import javafx.scene.control.TreeTableColumn;
>>>>>>> Stashed changes:ThreatAnalysisTool/src/gui/MainController.java

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
	private Button logout;
	@FXML
<<<<<<< Updated upstream:ThreatAnalysisTool/src/gui/MainPage.java
	private ListView<String> listView;
	@FXML
	private TextField searchField;

	private ParseFunction parser;
	private ObservableList<String> threats;
	private FilteredList<String> threatList;
	
	public MainPage() throws IOException {
		parser = new ParseFunction();
		threats = FXCollections.observableArrayList();
		for (Threat threat : parser.parseJSON().getObjects()) {
			threats.add(threat.toString());
		}
		
		threatList = new FilteredList<>(threats);
	}
	
	@FXML
	public void initialize() throws IOException {
		listView.setItems(threatList);
		
		searchField.textProperty().addListener((observable, oldValue, newValue) ->  {
    		if (newValue.isEmpty()) {
        		threatList.setPredicate(null);
    		} else {
        		final String searchString = newValue.toUpperCase();
        		threatList.setPredicate(s -> s.toUpperCase().contains(searchString));
    		}
});
	}
	
=======
	private Button help;

	@FXML
	private Button users;

	@FXML
	private TreeTableColumn<Threat, String> threat_details;

>>>>>>> Stashed changes:ThreatAnalysisTool/src/gui/MainController.java
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("login.fxml");
	}
	
	@FXML
	public void users(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Users.fxml");
	}
	
	@FXML
	public void help(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Help.fxml");
	}
}