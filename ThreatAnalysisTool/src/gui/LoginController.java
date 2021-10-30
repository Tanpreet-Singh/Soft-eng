package gui;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private Button userLogin;

    @FXML
    private Label wrongLogin;

    @FXML
    void userLogin(ActionEvent event) {

    }

}

	
//	
//	public void userLogin(ActionEvent event) throws IOException {
//		checkLogin();
//	}
//	
//	public void checkLogin() throws IOException {
//		Test m = new Test();
//		if(username.getText().toString().equals("Tan") && password.getText().equals("test")) {
//			wrongLogin.setText("success");
//			
//			m.changeScene("Main.fxml");
//		}
//		
//		else if(username.getText().isEmpty() && password.getText().isEmpty()) {
//			wrongLogin.setText("Please enter username and password");
//		}
//		
//		else {
//			wrongLogin.setText("Wrong username and password");
//		}
//	}

