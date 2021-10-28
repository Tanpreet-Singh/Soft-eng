package gui;

import threat.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class ThreatListView {
	
	private TableView<Threat> table = new TableView<Threat>();
    private final ObservableList<Threat> data
            = FXCollections.observableArrayList();
    

    void set() throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		byte[] jsonData = Files.readAllBytes(Paths.get("bigTest.json"));
		JsonNode jsonNodeRoot = objectMapper.readTree(jsonData);
		
		JSONBundle bundle = objectMapper.convertValue(jsonNodeRoot, JSONBundle.class);
		
		for (Threat threat : bundle.getObjects()) {
			data.add(threat);
		}
    }
    
	VBox createLayout() throws IOException {
		
		set();
		
		table.setEditable(true);
        
//		Scene scene = new Scene(new Group());
//		stage.setTitle("Table View Sample");
//		stage.setWidth(450);
//		stage.setHeight(550);
		
		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Threat, String>("name"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Threat, String>("ID"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Threat, String>("date"));

        FilteredList<Threat> flPerson = new FilteredList(data, p -> true);//Pass the data to a filtered list
        table.setItems(flPerson);//Set the table's items using the filtered list
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        
        //Adding ChoiceBox and TextField here!
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("First Name", "Last Name", "Email");
        choiceBox.setValue("First Name");

        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "First Name":
                    flPerson.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Last Name":
                    flPerson.setPredicate(p -> p.getID().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
              
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });

        HBox hBox = new HBox(choiceBox, textField);//Add choiceBox and textField to hBox
        hBox.setAlignment(Pos.CENTER);//Center HBox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox);

        return vbox;
        
//        ((Group) scene.getRoot()).getChildren().addAll(vbox);
//
//        stage.setScene(scene);
//        stage.show();
    }
	
	public static class Person
    {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email)
        {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName()
        {
            return firstName.get();
        }

        public void setFirstName(String fName)
        {
            firstName.set(fName);
        }

        public String getLastName()
        {
            return lastName.get();
        }

        public void setLastName(String fName)
        {
            lastName.set(fName);
        }

        public String getEmail()
        {
            return email.get();
        }

        public void setEmail(String fName)
        {
            email.set(fName);
        }
    }
}

