package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelpController {

	@FXML
	private Button goBack;

	@FXML
	public void back(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Main.fxml");
	}

}
