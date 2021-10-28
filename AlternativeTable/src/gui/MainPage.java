package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class MainPage {

	StackPane createLayout() throws IOException {

		// Left Column
		Text loginAs = new Text("Log In As:");
		Text role = new Text("Role");
		Button importBtn = new Button("Import");
		Button saveBtn = new Button("Save");
		Button delBtn = new Button("Delete");
		VBox leftColumn = new VBox(
				5.0,
				loginAs,
				role,
				importBtn,
				saveBtn,
				delBtn);
		leftColumn.setAlignment(Pos.TOP_LEFT);

		// Right Column	
		Button usersBtn = new Button("Users");
		
		MenuItem menuItem1 = new MenuItem("Undetermined");
        MenuItem menuItem2 = new MenuItem("Undetermined");
        MenuItem menuItem3 = new MenuItem("Log Out");

        MenuButton menuButton = new MenuButton("Settings", null, menuItem1, menuItem2, menuItem3);

        HBox settingsbutton = new HBox(menuButton);
		settingsbutton.setAlignment(Pos.TOP_RIGHT);
		
		// HBox userSection = new HBox(
		// 		2.0,
		// 		usersBtn,
		// 		settingsBtn);
		Button genPdfBtn = new Button("Gen PDF");
		VBox rightColumn = new VBox(
				2.0,
				usersBtn,
				settingsbutton,
				genPdfBtn);
		rightColumn.setAlignment(Pos.TOP_RIGHT);
		// Main Arrangment
		GridPane mainElements = new GridPane();
		mainElements.addColumn(0, leftColumn);
		mainElements.addColumn(2, rightColumn);
		ColumnConstraints alwaysGrow = new ColumnConstraints();
		alwaysGrow.setHgrow(Priority.ALWAYS);

		ThreatListView table = new ThreatListView();
		VBox list = table.createLayout();
		mainElements.add(list, 1, 1);

		mainElements.getColumnConstraints().addAll(
			alwaysGrow,
        	alwaysGrow,
        	alwaysGrow
        	);
		mainElements.setGridLinesVisible(true);
		return new StackPane(mainElements);
	}
}
