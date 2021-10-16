package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class LoginPage {

	StackPane createLayout() {

		// Title that says "Login"
		Text title = new Text("Login");

		// Username and Password Fields
		GridPane credentialFields = new GridPane();
		credentialFields.setAlignment(Pos.CENTER);
		Text username = new Text("Username: ");
		Text password = new Text("Password: ");
		TextField typeUsername = new TextField();
		TextField typePassword = new TextField();

		// Add the Text to the Grid
		credentialFields.addColumn(1, username, password);
		credentialFields.addColumn(2, typeUsername, typePassword);

		// Align these elements vertically
		VBox mainElements = new VBox(
				2.0,
				title,
				credentialFields);
		mainElements.setAlignment(Pos.CENTER);
		return new StackPane(mainElements);
	}
}
