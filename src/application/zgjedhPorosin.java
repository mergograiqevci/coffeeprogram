package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class zgjedhPorosin implements Initializable {
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static final String CONNECTDB="jdbc:mysql://localhost/caffee1";
    @FXML
    private Rectangle table3;

    @FXML
    private Rectangle table2;

    @FXML
    private Rectangle table1;

    @FXML
    private Text textid;
    @FXML
    private TableView<newItem> tableChoose;

    @FXML
    private TableColumn<newItem, String> itemtableChoose;

    @FXML
    private TableColumn<newItem, Double> pricetableChoose;

    @FXML
    private TableColumn<newItem, String> quantitytableChoose;

    @FXML
    private TableColumn<newItem, String> addtableChoose;

    @FXML
    private TableView<porositItem> tableOrder;
    @FXML
    private TableColumn<porositItem, String> itemOrder;

    @FXML
    private TableColumn<porositItem, Double> priceOrder;

    @FXML
    private TableColumn<porositItem, String> removeOrder;
    @FXML
    private Pane pane;

    ObservableList<porositItem> orderList=FXCollections.observableArrayList();
	 private static Connection getConn() throws SQLException {
			
		Connection connection;
		connection=(Connection) DriverManager.getConnection(CONNECTDB,USERNAME,PASSWORD);
			
			
		return connection;
			
	}
	public void shkruajeTavolinen(String emri)
	{
		textid.setText(emri);
	}
    @FXML
    void ontableClicked(MouseEvent event) throws SQLException {
    	tableChoose.getItems().clear();
    	if(event.getSource()==table1)	
    	{
    		table3.setFill(javafx.scene.paint.Color.WHITE);
    		table2.setFill(javafx.scene.paint.Color.WHITE);
    		table1.setFill(javafx.scene.paint.Color.CADETBLUE);
    		itemtableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("name"));
    		pricetableChoose.setCellValueFactory(new PropertyValueFactory<newItem,Double>("price"));
    		quantitytableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("quantity"));
    		java.sql.Connection connection=null;
    		connection=getConn();
    		java.sql.Statement st = connection.createStatement();
    		String queryexsists = ("select * from kafe");
    		ResultSet existsresult = st.executeQuery(queryexsists);
    		while(existsresult.next())
    		{
    		tableChoose.getItems().addAll(new newItem(existsresult.getString("emriProduktit"),existsresult.getString("sasiaProduktit"),existsresult.getDouble("cmimiProduktit")));
    		}
    		
    		
    	}
    	else if(event.getSource()==table2)
    	{
    		table3.setFill(javafx.scene.paint.Color.WHITE);
    		table1.setFill(javafx.scene.paint.Color.WHITE);
    		table2.setFill(javafx.scene.paint.Color.CADETBLUE);
    		itemtableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("name"));
    		pricetableChoose.setCellValueFactory(new PropertyValueFactory<newItem,Double>("price"));
    		quantitytableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("quantity"));
    		java.sql.Connection connection=null;
    		connection=getConn();
    		java.sql.Statement st = connection.createStatement();
    		String queryexsists = ("select * from soups");
    		ResultSet existsresult = st.executeQuery(queryexsists);
    		while(existsresult.next())
    		{
    		tableChoose.getItems().addAll(new newItem(existsresult.getString("emriProduktit"),existsresult.getString("sasiaProduktit"),existsresult.getDouble("cmimiProduktit")));
    		}
    	}
    	else if(event.getSource()==table3)
    	{
    		table1.setFill(javafx.scene.paint.Color.WHITE);
    		table2.setFill(javafx.scene.paint.Color.WHITE);
    		table3.setFill(javafx.scene.paint.Color.CADETBLUE);
    		itemtableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("name"));
    		pricetableChoose.setCellValueFactory(new PropertyValueFactory<newItem,Double>("price"));
    		quantitytableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("quantity"));
    		java.sql.Connection connection=null;
    		connection=getConn();
    		java.sql.Statement st = connection.createStatement();
    		String queryexsists = ("select * from cocktail");
    		ResultSet existsresult = st.executeQuery(queryexsists);
    		while(existsresult.next())
    		{
    		tableChoose.getItems().addAll(new newItem(existsresult.getString("emriProduktit"),existsresult.getString("sasiaProduktit"),existsresult.getDouble("cmimiProduktit")));
    		}
    	}
    }


    @FXML
    void ontablechooseClicked(MouseEvent event) {
    	if(event.getClickCount()==2) {
    	int index = tableChoose.getSelectionModel().getSelectedIndex();
        newItem itemi = tableChoose.getItems().get(index);
       // textid.setText(itemi.getName());
        
       // orderList=FXCollections.observableArrayList(
	    //		new porositItem(itemi.getName(),itemi.getPrice(),"REMOVE"));
       
        orderList.add(new porositItem(itemi.getName(),itemi.getPrice(),"REMOVE"));
        tableOrder.getItems().add(new porositItem(itemi.getName(),itemi.getPrice(),"REMOVE"));
    	}
       
    }

    @FXML
    void removeitemonClicked(MouseEvent event) {
    	if(event.getClickCount()==2) {
    	porositItem selectedItem = tableOrder.getSelectionModel().getSelectedItem();
    	tableOrder.getItems().remove(selectedItem);
    	}
    }
    @FXML
    void onorderClicked(MouseEvent event) throws IOException {
    	 shenoFilen();
         FXMLLoader loader=new FXMLLoader(getClass().getResource("order.fxml"));
         Parent root=(Parent)loader.load();
         orderController ordercontroller=loader.getController();
         ordercontroller.cilaTable(textid.getText());
         for (porositItem item : tableOrder.getItems()) {
             ordercontroller.mbushTabelen(itemOrder.getCellObservableValue(item).getValue()
            		 , priceOrder.getCellObservableValue(item).getValue());
         }
         
         Stage stage=new Stage();
         stage.setResizable(false);
         stage.setScene(new Scene(root));
         stage.show();
     
         
    }
    public void shenoFilen() throws IOException {
    	File file=new File("cilatable.txt");
    	if(!file.exists())
    	{
    		file.createNewFile();
    	}
    	else
    	{
    		PrintWriter pw=new PrintWriter(file);
    		pw.println(textid.getText());
    		pw.close();
    	}
    }
    @FXML
    void onbackClicked(MouseEvent event) throws IOException {
		Pane pane1=FXMLLoader.load(getClass().getResource("zgjedhtavolin.fxml"));
		pane.getChildren().setAll(pane1);
    }
    public void defaulttableContent() throws SQLException {
    	itemtableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("name"));
		pricetableChoose.setCellValueFactory(new PropertyValueFactory<newItem,Double>("price"));
		quantitytableChoose.setCellValueFactory(new PropertyValueFactory<newItem,String>("quantity"));
		java.sql.Connection connection=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String queryexsists = ("select * from kafe");
		ResultSet existsresult = st.executeQuery(queryexsists);
		while(existsresult.next())
		{
		tableChoose.getItems().addAll(new newItem(existsresult.getString("emriProduktit"),existsresult.getString("sasiaProduktit"),existsresult.getDouble("cmimiProduktit")));
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textid.setVisible(false);
		tableChoose.setEditable(true);
		itemOrder.setCellValueFactory(new PropertyValueFactory<porositItem,String>("name"));
	    priceOrder.setCellValueFactory(new PropertyValueFactory<porositItem,Double>("price"));
	    removeOrder.setCellValueFactory(new PropertyValueFactory<porositItem,String>("remove"));
	    try {
			defaulttableContent();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
