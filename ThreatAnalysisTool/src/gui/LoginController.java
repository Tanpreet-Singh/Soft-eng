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
		checkLogin();
	}

	private void checkLogin() throws IOException {
		Test m = new Test();
		if (username.getText().toString().equals("admin") && password.getText().equals("123")) {
			wrongLogin.setText("success");

			m.changeScene("Main.fxml");
			
		}

		else if (username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Please enter username and password");
		}

		else {
			wrongLogin.setText("Wrong username and password");
		}
	}
}
