package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

////////////  Just a test will deltete this file at the end and use DatabaseTest instead


public class mysqlconnect {

	Connection conn = null;

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/market", "root", "");
			// JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

	public static ObservableList<Users> getDatausers(){
	        Connection conn = ConnectDb();
	        ObservableList<Users> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from users");
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()){   
	       //         list.add(new Users(Integer.parseInt(rs.getString("username"), rs.getString("password"), rs.getString("level")));               
	            }
	        } catch (Exception e) {
	        }
	        return list;
	    }

}
