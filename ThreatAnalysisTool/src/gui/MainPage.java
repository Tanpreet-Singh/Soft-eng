package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class MainPage {

	StackPane createLayout() {

		// Left Column
		Text loginAs = new Text("Log In As:");
		Text role = new Text("Role");
		Button importBtn = new Button("Import");
		Button saveBtn = new Button("Save");
		Button delBtn = new Button("Delete");
		VBox leftColumn = new VBox(
				2.0,
				loginAs,
				role,
				importBtn,
				saveBtn,
				delBtn);

		// Right Column	
		Button usersBtn = new Button("Users");
		Button settingsBtn = new Button("Settings");
		HBox userSection = new HBox(
				2.0,
				usersBtn,
				settingsBtn);
		Button genPdfBtn = new Button("Gen PDF");
		VBox rightColumn = new VBox(
				2.0,
				userSection,
				genPdfBtn);

		// Main Arrangment
		HBox mainElements = new HBox(
				2.0,
				leftColumn,
				rightColumn);
		mainElements.setAlignment(Pos.CENTER);

		return new StackPane(mainElements);
	}
}
