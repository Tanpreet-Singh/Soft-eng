package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UsersController extends Controller{

	@FXML
	private Text wrongLogin;
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private TextField userLevel;
	
	@FXML
	private Button adduser;
	
	@FXML
	private TextField delete;
	
	@FXML
	private Button deleteuser;
	
	@FXML
	private Text wrong;
	
	@FXML
	private Button back;
	
	@FXML
	private ListView<String> table_Users;

	
	ObservableList<String> listM;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public UsersController() {
		
	}
	
	DatabaseTest db = new DatabaseTest();
	
	@FXML
	public void initialize() throws IOException {

		listM = db.getDatausers();
		table_Users.setItems(listM);
	}

	@FXML
	public void addUser(ActionEvent event) throws IOException {
		DatabaseTest databaseTest = new DatabaseTest();

		int code = databaseTest.addUser(username.getText(), password.getText(), Integer.parseInt(userLevel.getText()));
		
		if (code == 1) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Member");
			alert.setContentText(username.getText() + " was added to the system");
			alert.showAndWait();
			
			username.setText("");
			password.setText("");
			userLevel.setText("");
			username.requestFocus();
			listM = db.getDatausers();
			table_Users.setItems(listM);
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Failure");
			alert.setHeaderText(username.getText() + " was not added");
			alert.setContentText("User added failed");
			alert.showAndWait();
		}
	}

	@FXML
	public void deleteUser(ActionEvent event) throws IOException {
		DatabaseTest databaseTest = new DatabaseTest();
		int code = databaseTest.deleteUser(delete.getText());
		
		if (code == 1) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(delete.getText() + " do not have access anymore");
			alert.setContentText("User was deleted");
			alert.showAndWait();
			
			delete.setText("");
			delete.requestFocus();
			listM = db.getDatausers();
			table_Users.setItems(listM);
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Failed");
			alert.setHeaderText("Failure");
			alert.setContentText("Action failed");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void back(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Main.fxml");
	}

}
