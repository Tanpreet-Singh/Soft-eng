package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ListViewTest extends Application {
	StackPane root;
	StackPane layout;
	final int windowWidth = 600, windowHeight = 400;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Threat Analyzer Tool");
		ThreatListView listview = new ThreatListView();
		Scene scene = new Scene(listview.createLayout(), windowWidth, windowHeight);
		primaryStage.setScene(scene);
		scene.getStylesheets().add
 		(ThreatListView.class.getResource("Login.css").toExternalForm());
		primaryStage.show();
	}
}
