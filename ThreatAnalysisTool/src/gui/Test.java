package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {
	
	private static Stage stg;
	
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			//BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("Main.fxml")); 	// Uncomment this to test MainPage gui
			Scene scene = new Scene(root, 700, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
