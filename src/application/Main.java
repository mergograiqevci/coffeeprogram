package application;
	
import java.io.IOException;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		
		primaryStage.setResizable(false);
		Scene scene=new Scene(parent);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


	 public static void main(String[] args) {
	 Application.launch(args);
	 }
}