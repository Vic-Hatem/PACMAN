package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class mainPageControl{

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
    
    @FXML
    private Button Options;
    
    
	public void StartGame(ActionEvent event) throws Exception {
	//	closeWindow();
		System.out.println("Click on Start Game");
	//	Stage primaryStage = new Stage();
	//	Parent root = FXMLLoader.load(getClass().getResource("/View/NickName.fxml"));
	//	Scene scene = new Scene(root,Config.SizeX,Config.SizeY);
		//primaryStage.setScene(scene);
		//primaryStage.setTitle("enter your NickName");
		//primaryStage.show();
		

		Config.MainStage.setScene(Config.NickScene);
	}
	//added by @epicon
	public void Options(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Options.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Options");
		primaryStage.show();
	}
	public void History(ActionEvent event) throws Exception {
//		closeWindow();
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource(""));
//		Scene scene = new Scene(root,637,546);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("View history of Games");
//		primaryStage.show();
		Config.MainStage.setScene(Config.HistoryScene);


	}
	
	public void Rules(ActionEvent event) throws Exception {
//		closeWindow();
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource(""));
//		Scene scene = new Scene(root,782,694);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Read the Rules");
//		primaryStage.show();
		Config.MainStage.setScene(Config.RulesScene);

	}
	
	public void Questions(ActionEvent event) throws Exception {
		//closeWindow();
		//Stage primaryStage = new Stage();
	//	Parent root = FXMLLoader.load(getClass().getResource(""));
	//	Scene scene = new Scene(root,637,546);
	//	primaryStage.setScene(scene);
		//primaryStage.setTitle("Questions Management");
		//primaryStage.show();
		Config.MainStage.setScene(Config.QandAScene);

	}
	
	
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}

}
