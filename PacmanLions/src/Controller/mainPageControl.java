package Controller;

import java.awt.TextField;
import java.awt.event.MouseEvent;

import Model.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*@authors Moran, Nahawand and Grace*/
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
    
    @FXML
    private Button Options;
//    
//    @FXML
//    private Button go;
//    @FXML
//    private PasswordField password = new PasswordField();
//    
//    @FXML
//    private TextField userName = new TextField();
//    
//    @FXML
//    private AnchorPane adminModal;
//    
    
	public void StartGame(ActionEvent event) throws Exception {
		System.out.println("Click on Start Game");
		Config.MainStage.setScene(Config.NickScene);
		Config.MainStage.setMaximized(true);

	}

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
		Config.MainStage.setScene(Config.HistoryScene);
		Config.MainStage.setMaximized(true);

	}
	
	public void Rules(ActionEvent event) throws Exception {
		Config.MainStage.setScene(Config.RulesScene);
		Config.MainStage.setMaximized(true);

	}
	
	
	public void Questions(ActionEvent event) throws Exception {

					Config.MainStage.setScene(Config.QandAScene);
					Config.MainStage.setMaximized(true);

	}
	
	
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}
}
