package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UsersController {

	@FXML
	private Button adduser;

	@FXML
	private Button back;

	@FXML
	private TableView<Users> table_Users;
	
	@FXML
	private TableColumn<Users, String> col_level;

	@FXML
	private TableColumn<Users, String> col_password;

	@FXML
	private TableColumn<Users, String> col_username;
	
	@FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField level;

	@FXML
	public void addUser(ActionEvent event) throws IOException {
		//DatabaseTest db = new DatabaseTest();
		DatabaseTest.addUser(col_username.getText(), col_password.getText(), col_level.getText());
	}

	@FXML
	public void back(ActionEvent event) throws IOException {
		Test m = new Test();
		m.changeScene("Main.fxml");
	}
	
	ObservableList<Users> listM;
    ObservableList<Users> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public void Add_Users (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into Users (username,password,email,type)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, col_username.getText());
            pst.setString(2, col_password.getText());
            pst.setString(3, col_level.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select Users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_Users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
    col_username.setText(col_username.getCellData(index).toString());
    col_password.setText(col_password.getCellData(index).toString());
    col_level.setText(col_level.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = col_username.getText();
            String value2 = col_password.getText();
            String value3 = col_level.getText();
            String sql = "update Users set user_id= '"+value1+"',username= '"+value2+"',password= '"+
                    value3+"',email= '"+value4+"',type= '"+value5+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from Users where user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        
        listM = mysqlconnect.getDatausers();
        table_Users.setItems(listM);
    }
    
    

    
 @FXML
    void search_user() {          
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        
        dataList = mysqlconnect.getDatausers();
        table_Users.setItems(dataList);
        FilteredList<Users> filteredData = new FilteredList<>(dataList, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Users> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_Users.comparatorProperty());  
  table_Users.setItems(sortedData);      
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_user();
    } 
}


