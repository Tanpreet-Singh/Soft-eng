package gui;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Test extends Application {

	private static Stage stg;
	private int level;

	public Test(int level) {
		this.level = level;
	}

	
	public Test() {
		this.level = 0;
	}

	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 750, 600);
			stg.setMinWidth(750);
			stg.setMinHeight(600);
			//stg.setMaximized(true);
			
			stg.setScene(scene);
			stg.setTitle("Threat Analysis Tool");
			stg.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeScene(String fxml) throws IOException {	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));     

		Parent root = (Parent)fxmlLoader.load();          
		Controller controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 750, 600); 
		stg.setMinWidth(750);
		stg.setMinHeight(600);
		
		stg.setScene(scene);
		
		stg.show();
	}
	
	public void changeSceneToMain() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		MainController controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 750, 600);
		stg.setMinWidth(750);
		stg.setMinHeight(600);
		
		stg.setScene(scene);    

		controller.initData(new DatabaseTest().getUserLevelThreats(level), level);
		stg.show();
	}
	
	public void changeSceneToEngineer() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Engineer.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		EngineerController controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 750, 600);
		stg.setMinWidth(750);
		stg.setMinHeight(600);
		
		stg.setScene(scene);    

		controller.initData(new DatabaseTest().getUserLevelThreats(level), level);
		stg.show();
	}
	
	public void changeSceneToViewer() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Viewer.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		ViewerController controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 750, 600);
		stg.setMinWidth(750);
		stg.setMinHeight(600);
		
		stg.setScene(scene);    

		controller.initData(new DatabaseTest().getUserLevelThreats(level), level);
		stg.show();
	}
	
	public void changeSceneToDetails(Threat threat) throws IOException {

		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Details.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		DetailsController controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 750, 600);
		stg.setMinWidth(750);
		stg.setMinHeight(600);

		stg.setScene(scene); 

		controller.initData(threat, level);
		stg.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
