package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

	@FXML
	private PasswordField password;

	@FXML
	private Button userLogin;

	@FXML
	private TextField username;

	@FXML
	private Text wrongLogin;

	@FXML
	public void userLogin(ActionEvent event) throws IOException {
		Test m = new Test();
		DatabaseTest db = new DatabaseTest();
		if (db.authenticateUser(username.getText(), password.getText()) == 1) {
			wrongLogin.setText("success");

			m.changeScene("Main.fxml");
		}

		else if (db.authenticateUser(username.getText(), password.getText()) == 2) {
			wrongLogin.setText("Please enter correct username and password");
		}

		else {
			wrongLogin.setText("Connection error");
		}
	}
}
