package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class ThreatListView {

	StackPane createLayout() {
		
		// Title that says "Listview Example"
		Text title = new Text("Listview Example");

		ListView<String> listView = new ListView<String>();
		listView.getItems().add("Threat 1\nThreat Name\nThreat ID");
		listView.getItems().add("Threat 2\nThreat Name\nThreat ID");
		listView.getItems().add("Threat 3\nThreat Name\nThreat ID");
		listView.getItems().add("Threat 4\nThreat Name\nThreat ID");
		
		// Align these elements vertically
		VBox mainElements = new VBox(
				2.0,
				title,
				listView);
		mainElements.setAlignment(Pos.CENTER);
		return new StackPane(mainElements);
	}
}