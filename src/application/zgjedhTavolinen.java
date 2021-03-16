package application;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class zgjedhTavolinen implements Initializable {
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static final String CONNECTDB="jdbc:mysql://localhost/caffee1";
    @FXML
    private Rectangle table6;
    @FXML
    private Pane pane;
    @FXML
    private Rectangle table5;

    @FXML
    private Rectangle table4;

    @FXML
    private Rectangle table3;

    @FXML
    private Rectangle table2;

    @FXML
    private Rectangle table1;
    @FXML
    private Text memberText;

    @FXML
    void ontableClicked(MouseEvent event) throws IOException {
    	if(event.getSource()==table1)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table1");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	else if(event.getSource()==table2)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table2");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	else if(event.getSource()==table3)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table3");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	else if(event.getSource()==table4)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table4");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	else if(event.getSource()==table5)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table5");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	else if(event.getSource()==table6)
    	{
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("zgjedhporosin.fxml"));
            Parent root=(Parent)loader.load();
            zgjedhPorosin zgjedhporosin=loader.getController();
            zgjedhporosin.shkruajeTavolinen("table6");
    		//Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhporosin.fxml"));
			pane.getChildren().setAll(root);
    	}
    	
    }
    public void mbushmemberText(String emri)
    {
    	memberText.setText(emri.toUpperCase());
    }
    @FXML
    void onbackClicked(MouseEvent event) throws IOException {
		Pane pane1=FXMLLoader.load(getClass().getResource("login.fxml"));
		pane.getChildren().setAll(pane1);
    }
    public void shikoTabelen(String emri,Rectangle table) throws SQLException
    {
    	Connection connection=getConn();
    	java.sql.Statement st = connection.createStatement();
		String queryexsists="select * from "+emri;
		ResultSet existsresult = st.executeQuery(queryexsists);
		if(existsresult.next())
		{
			table.setFill(javafx.scene.paint.Color.RED);
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			shikoTabelen("table1",table1);
			shikoTabelen("table2",table2);
			shikoTabelen("table3",table3);
			shikoTabelen("table4",table4);
			shikoTabelen("table5",table5);
			shikoTabelen("table6",table6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Connection getConn() throws SQLException {
		
		Connection connection;
		connection=(Connection) DriverManager.getConnection(CONNECTDB,USERNAME,PASSWORD);
			
			
		return connection;
			
	}

}
