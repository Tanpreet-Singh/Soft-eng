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
		rawData.add("identity\nidentity--c78cb6e5-0c4b-4611-8297-d1b8b55e40b5\nThe MITRE Corporation");
		rawData.add("marking-definition\nmarking-definition--fa42a846-8d90-4e51-bc29-71d5b4802168\nstatement");
		rawData.add("course-of-action\ncourse-of-action--4f170666-7edb-4489-85c2-9affa28a72e0\n.bash_profile and .bashrc Mitigation");
		rawData.add("attack-pattern\nattack-pattern--01df3350-ce05-4bdf-bdf8-0a919a66d4a8\n.bash_profile and .bashrc");
		rawData.add("course-of-action\ncourse-of-action--c61fee9f-16fb-4f8c-bbf0-869093fcd4a6\nAccess Token Manipulation Mitigation");
		rawData.add("attack-pattern\nattack-pattern--dcaa092b-7de9-4a21-977f-7fcb77e89c48\nAccess Token Manipulation");
		rawData.add("attack-pattern\nattack-pattern--9b99b83a-1aac-4e29-b975-b374950551a3\nAccessibility Features");
		rawData.add("course-of-action\ncourse-of-action--5c49bc54-9929-48ca-b581-7018219b5a97\nAccount Discovery Mitigation");
		rawData.add("identity\nidentity--c78cb6e5-0c4b-4611-8297-d1b8b55e40b5\nThe MITRE Corporation");
		rawData.add("marking-definition\nmarking-definition--fa42a846-8d90-4e51-bc29-71d5b4802168\nstatement");
		rawData.add("course-of-action\ncourse-of-action--4f170666-7edb-4489-85c2-9affa28a72e0\n.bash_profile and .bashrc Mitigation");
		rawData.add("attack-pattern\nattack-pattern--01df3350-ce05-4bdf-bdf8-0a919a66d4a8\n.bash_profile and .bashrc");
		rawData.add("course-of-action\ncourse-of-action--c61fee9f-16fb-4f8c-bbf0-869093fcd4a6\nAccess Token Manipulation Mitigation");
		rawData.add("attack-pattern\nattack-pattern--dcaa092b-7de9-4a21-977f-7fcb77e89c48\nAccess Token Manipulation");
		rawData.add("attack-pattern\nattack-pattern--9b99b83a-1aac-4e29-b975-b374950551a3\nAccessibility Features");
		rawData.add("course-of-action\ncourse-of-action--5c49bc54-9929-48ca-b581-7018219b5a97\nAccount Discovery Mitigation");
		rawData.add("identity\nidentity--c78cb6e5-0c4b-4611-8297-d1b8b55e40b5\nThe MITRE Corporation");
		rawData.add("marking-definition\nmarking-definition--fa42a846-8d90-4e51-bc29-71d5b4802168\nstatement");
		rawData.add("course-of-action\ncourse-of-action--4f170666-7edb-4489-85c2-9affa28a72e0\n.bash_profile and .bashrc Mitigation");
		rawData.add("attack-pattern\nattack-pattern--01df3350-ce05-4bdf-bdf8-0a919a66d4a8\n.bash_profile and .bashrc");
		rawData.add("course-of-action\ncourse-of-action--c61fee9f-16fb-4f8c-bbf0-869093fcd4a6\nAccess Token Manipulation Mitigation");
		rawData.add("attack-pattern\nattack-pattern--dcaa092b-7de9-4a21-977f-7fcb77e89c48\nAccess Token Manipulation");
		rawData.add("attack-pattern\nattack-pattern--9b99b83a-1aac-4e29-b975-b374950551a3\nAccessibility Features");
		rawData.add("course-of-action\ncourse-of-action--5c49bc54-9929-48ca-b581-7018219b5a97\nAccount Discovery Mitigation");
	
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
		VBox.setVgrow(listView, Priority.ALWAYS);
		return mainElements;

		
    }
}