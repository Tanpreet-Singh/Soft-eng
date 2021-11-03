package gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainPage {

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
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("login.fxml");
	}
}