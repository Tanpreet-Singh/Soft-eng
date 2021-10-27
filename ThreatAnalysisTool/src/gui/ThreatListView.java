package gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class ThreatListView {

	VBox createLayout() {
		
		
		// Title that says "Listview Example"
		ObservableList<String> rawData= FXCollections.observableArrayList();
		rawData.add("Threat 1\nAPT28\nThreat ID");
		rawData.add("Threat 2\nThreat Name\nintrusion-set--bef4c620-0787-42a8-a96d-b7eb6e85917c");
		rawData.add("Threat 3\nThreat Name\nThreat ID");
		rawData.add("Threat 4\nThreat Name\nThreat ID");
		rawData.add("Threat 5\nThreat Name\nThreat ID");
		rawData.add("Threat 6\nThreat Name\nThreat ID");
		rawData.add("Threat 7\nThreat Name\nThreat ID");
		rawData.add("Threat 8\nThreat Name\nintrusion-set--899ce53f-13a0-479b-a0e4-67d46e241542");
	
		FilteredList<String> filteredList = new FilteredList<>(rawData);

		ListView<String> listView = new ListView<>(filteredList);

		TextField filterField = new TextField();
		filterField.textProperty().addListener((observable, oldValue, newValue) ->  {
    		if (newValue.isEmpty()) {
        		filteredList.setPredicate(null);
    		} else {
        		final String searchString = newValue.toUpperCase();
        		filteredList.setPredicate(s -> s.toUpperCase().contains(searchString));
    		}
});

		// Align these elements vertically
		VBox mainElements = new VBox(
				2.0,
				filterField,
				listView);
		mainElements.setAlignment(Pos.CENTER);
		mainElements.setVgrow(listView, Priority.ALWAYS);
		return mainElements;

		
    }
}