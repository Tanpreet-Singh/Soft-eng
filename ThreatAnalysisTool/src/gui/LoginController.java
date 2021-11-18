package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController extends Controller{

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
		
		
		DatabaseTest db = new DatabaseTest();
		
		int level = db.authenticateUser(username.getText(), password.getText());
		
		if (level == 1) {
			wrongLogin.setText("success");
			Test m = new Test(level);
			m.changeScene("Main.fxml");
		}
		
		if (level == 2) {
			wrongLogin.setText("success");
			Test m = new Test(level);
			m.changeScene("Engineer.fxml");
		}
		
		if (level == 3) {
			wrongLogin.setText("success");
			Test m = new Test(level);
			m.changeScene("Viewer.fxml");
		}

		else if (level == -2) {
			wrongLogin.setText("Please enter correct username and password");
		}

		else {
			wrongLogin.setText("Connection error");
		}
	}
}
