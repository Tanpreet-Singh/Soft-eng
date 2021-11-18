package gui;

import java.io.IOException;

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
			stg.setMinHeight(500);
			stg.setMinWidth(700);
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 700, 500);
			stg.setMaximized(true);
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
		Scene scene = new Scene(root, 700, 500); 

		stg.setScene(scene);    

		controller.setLevel(level);
		stg.show();
	}
	
	public void changeSceneToDetails(Threat threat) throws IOException {
//		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//		stg.getScene().setRoot(pane);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Details.fxml"));     

		Parent root = (Parent)fxmlLoader.load();          
		DetailsController controller = fxmlLoader.getController();
		Scene scene = new Scene(root, 700, 500); 

		stg.setScene(scene); 

		controller.initData(threat);
		stg.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
