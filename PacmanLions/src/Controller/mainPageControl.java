package Controller;


import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class mainPageControl implements Initializable {

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

    @FXML
    private AnchorPane admin;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button go;
    

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
		admin.setVisible(true);
	}
	
	public void Go(ActionEvent event) throws Exception {
		
		String nameOfuser = name.getText();
		String pass = password.getText();
		
		if(!nameOfuser.equals("admin")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong Name");
			alert.setContentText("You Have Entered Wrong Name , Please Try Again");
			alert.show(); 
		}
		else {
			if(!pass.equals("admin")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong Password");
				alert.setContentText("You Have Entered Wrong Password , Please Try Again");
				alert.show(); 
			}
			else {
				Config.MainStage.setScene(Config.QandAScene);
				Config.MainStage.setMaximized(true);
			}
		}

	}
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		admin.setVisible(false);
		
	}
}
