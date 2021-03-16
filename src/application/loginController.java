package application;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class loginController {
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static final String CONNECTDB="jdbc:mysql://localhost/caffee1";
	 @FXML
	 private TextField loginLabel;
	 @FXML
	 private Pane pane;
	 @FXML
	 private PasswordField passwordLabel;

	 @FXML
	 void onloginClicked(MouseEvent event) throws IOException, SQLException {
		if(validoteDhenat(loginLabel.getText(),passwordLabel.getText())) {
		java.sql.Connection connection=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String queryexsists = ("select * from users where emri='"+loginLabel.getText()+"' and password='"+passwordLabel.getText()+"'");
		ResultSet existsresult = st.executeQuery(queryexsists);
		if(existsresult.next())
		{
	        FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhtavolin.fxml"));
	        Parent root=(Parent)loader.load();
			//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhtavolin.fxml"));
			pane.getChildren().setAll(root);
		}
		else {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setContentText("Useri "+loginLabel.getText().toUpperCase()+" nuk ekziston");
			alert.show();
			}
		}
		else 
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setContentText("Emri&Password duhet te shkruhen");
			alert.show();
		}
		

	 }
	 public boolean validoteDhenat(String emri,String password)
	 {
		 if(emri.length() >=1 && password.length()>=2)
		 {
			 return true;
		 }
		 return false;
	 }
	 private static Connection getConn() throws SQLException {
			
		Connection connection;
		connection=(Connection) DriverManager.getConnection(CONNECTDB,USERNAME,PASSWORD);
			
			
		return connection;
			
	}
}
