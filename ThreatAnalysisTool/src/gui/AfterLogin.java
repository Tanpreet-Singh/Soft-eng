package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;

public class AfterLogin {

	@FXML
	private Button logout;
	
	public void userLogout(ActionEvent event) throws IOException {
		Test m = new Test();
		//m.changeScene("login.fxml");
	}
}
