package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class UsersController {

	@FXML
	private Text wrongLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
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
	private TableView<Users> table_Users;
	@FXML
	private TableColumn<Users, Integer> col_level;
	@FXML
	private TableColumn<Users, String> col_password;
	@FXML
	private TableColumn<Users, String> col_username;
	@FXML
	private TextField level;

	
	ObservableList<Users> listM;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public UsersController() {
		
	}
	
	DatabaseTest db = new DatabaseTest();
	
//	@FXML
//	public void initialize() throws IOException {
//		col_username.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));
//		col_level.setCellValueFactory(new PropertyValueFactory<Users, Integer>("access_level"));
//
//		listM = db.getDatausers();
//		table_Users.setItems(listM);
//	}

	@FXML
	public void addUser(ActionEvent event) throws IOException {
		DatabaseTest databaseTest = new DatabaseTest();
		//DatabaseTest db = new DatabaseTest();
		//databaseTest.addUser(col_username.getText(), col_password.getText(), 1);
		int code = databaseTest.addUser(username.getText(), password.getText(), 1);
		
		if (code == 1) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Member");
			alert.setContentText(username.getText() + " was added to the system");
			alert.showAndWait();
			
			username.setText("");
			password.setText("");
			username.requestFocus();
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
