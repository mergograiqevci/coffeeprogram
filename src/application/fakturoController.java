package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fakturoController implements Initializable {
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static final String CONNECTDB="jdbc:mysql://localhost/caffee1";
    @FXML
    private Pane pane;

    @FXML
    private TableView<porositItem> orderedTable;

    @FXML
    private TableColumn<porositItem, String> itemColumn;

    @FXML
    private TableColumn<porositItem, Double> priceColumn;

    @FXML
    private Text totalText;

    @FXML
    private Button printbutton;

    @FXML
    private Button clearbutton;
    @FXML
    private Text texttable;

    @FXML
    void clearprintonClick(ActionEvent event) {
    	if(event.getSource()==printbutton)
    	{
    		 Stage stage = (Stage) printbutton.getScene().getWindow();
			 stage.close();
    	}
    	else if(event.getSource()==clearbutton)
    	{
    		try {
				clearData();
				 Stage stage = (Stage) clearbutton.getScene().getWindow();
				 stage.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    }

  
    public void clearData() throws SQLException {
    	java.sql.Connection connection=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String queryexsists="delete from "+texttable.getText();
		int existsresult = st.executeUpdate(queryexsists);
    }
    public void getData(String emri) throws SQLException {
    	itemColumn.setCellValueFactory(new PropertyValueFactory<porositItem,String>("name"));
    	priceColumn.setCellValueFactory(new PropertyValueFactory<porositItem,Double>("price"));
		java.sql.Connection connection=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String queryexsists="select * from "+emri;
		ResultSet existsresult = st.executeQuery(queryexsists);
		while(existsresult.next())
		{
			orderedTable.getItems().addAll(new porositItem(existsresult.getString("item"),existsresult.getDouble("price"),""));
		}
		List<Double> columnData = new ArrayList<>();
   	 	for (porositItem item : orderedTable.getItems()) {
   	 		columnData.add(priceColumn.getCellObservableValue(item).getValue());
   	 	}
   	 	double ab=0;
   	 	for(int i=0;i<columnData.size();i++){
   	 		ab+=(columnData.get(i));
   	 	}
   	 totalText.setText(Double.toString(ab)+" €");
	}
    public void shenoTable(String emri)
    {
    	texttable.setText(emri);
    }
    private static Connection getConn() throws SQLException {
		
		Connection connection;
		connection=(Connection) DriverManager.getConnection(CONNECTDB,USERNAME,PASSWORD);
			
			
		return connection;
			
	}
    public String getTable() throws FileNotFoundException {
    	String ab="";
    	File file=new File("cilatable.txt");
    	if(file.exists())
    	{
    		Scanner sc=new Scanner(file);
    		while(sc.hasNext())
    		{
    			ab+=sc.nextLine();
    		}
    	}
		return ab;
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		texttable.setVisible(false);
		try {	
			getData(getTable());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
