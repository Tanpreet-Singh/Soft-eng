package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;

public class MainPage {

	@FXML
	private Button add;

	@FXML
	private Button delete;

	@FXML
	private Button edit;

	@FXML
	private Button genPdf;

	@FXML
	private Button logout;

	@FXML
	private TreeTableColumn<Threat, String> threat_details;

	@FXML
	public void logout(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("login.fxml");
	}
}