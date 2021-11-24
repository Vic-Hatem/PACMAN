package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class mainPageControl {

    @FXML
    private Button startGame;

    @FXML
    private Button history;

    @FXML
    private Button rules;

    @FXML
    private Button questions;

    @FXML
    private ImageView exit;
    
    
    
    
	public void StartGame(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/NickName.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("enter your NickName");
		primaryStage.show();
	}
	
	public void History(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/History.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("View history of Games");
		primaryStage.show();
	}
	
	public void Rules(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Rules.fxml"));
		Scene scene = new Scene(root,782,694);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Read the Rules");
		primaryStage.show();
	}
	
	public void Questions(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Questions Management");
		primaryStage.show();
	}
	
	
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}
}
