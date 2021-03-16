package application;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class orderController implements Initializable{

	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static final String CONNECTDB="jdbc:mysql://localhost/caffee1";
    @FXML
    private TableView<porositItem> orderedTable;

    @FXML
    private TableColumn<porositItem, String> itemColumn;

    @FXML
    private TableColumn<porositItem, Double> priceColumn;

    @FXML
    private Text totalText;
    @FXML
    private Text texttable;
    @FXML
    private Button invoicesbutton;

    @FXML
    private Button closebutton;

    @FXML
    private Button backbutton;
    @FXML
    private Pane pane;
    
    @FXML
    void invoicesonClick(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("fakturo.fxml"));
        Parent root=(Parent)loader.load();
        fakturoController fakturocontroller=loader.getController();
        fakturocontroller.shenoTable(texttable.getText());
		pane.getChildren().setAll(root);
    }
    public void mbushTabelen(String name,double price)
    {
    	itemColumn.setCellValueFactory(new PropertyValueFactory<porositItem,String>("name"));
    	priceColumn.setCellValueFactory(new PropertyValueFactory<porositItem,Double>("price"));
    	orderedTable.getItems().add(new porositItem(name,price,""));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		texttable.setVisible(false);
	}
    @FXML
    void closeTable(ActionEvent event) throws SQLException, IOException {
    	java.sql.Connection connection=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
	
   	 	for (porositItem item : orderedTable.getItems()) {
   	 	int existsresult = st.executeUpdate("insert into "+texttable.getText()+"(item, price) values ('"+itemColumn.getCellObservableValue(item).getValue()+"', '"+priceColumn.getCellObservableValue(item).getValue()+"')");
   	 	}
   	 Stage stage = (Stage) closebutton.getScene().getWindow();
	 stage.close();
    }
    public void cilaTable(String emri)
    {
    	texttable.setText(emri);
    }
	
	 private static Connection getConn() throws SQLException {
			
		Connection connection;
		connection=(Connection) DriverManager.getConnection(CONNECTDB,USERNAME,PASSWORD);
			
			
		return connection;
			
	}

}
