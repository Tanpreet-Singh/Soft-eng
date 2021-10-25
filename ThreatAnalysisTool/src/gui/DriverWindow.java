
package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DriverWindow extends Application {
	StackPane root;
	StackPane layout;
	final int windowWidth = 600, windowHeight = 400;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Threat Analyzer Tool");
		LoginPage login = new LoginPage();
		Scene scene = new Scene(login.createLayout(primaryStage, windowWidth, windowHeight), windowWidth, windowHeight);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
