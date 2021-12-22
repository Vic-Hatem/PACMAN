package Controller;

/*@authors Hatem, Moran, Nahawand and Grace*/
import java.net.URL;
import java.util.ResourceBundle;

import Model.Config;
import Model.Game;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class NicknameController implements Initializable{

    @FXML
    private ImageView exit;

    @FXML
    public TextField nickname;

    @FXML
    private Button go;
    @FXML
    private Label error;
    
    public void initialize(URL location, ResourceBundle resources) 
	{
    	go.disableProperty().bind(Bindings.createBooleanBinding(()->nickname.getText().trim().isEmpty(), nickname.textProperty()));
    	error.visibleProperty().bind(Bindings.createBooleanBinding(()->nickname.getText().trim().isEmpty(), nickname.textProperty()));

	}
    public NicknameController() 
    {
    }
	public void GoStartGame() throws Exception {
		

		System.out.println("At NickName Starting Game");


		Config.GManager = new Game(nickname.getText(),1,1,3,0,0,0,0);
		
		MazeController mc = Config.gmloader.getController();;
		mc.GameInstance(Config.GManager); //calls the method from mazecontroller before its loaded

		Config.MainStage.setScene(Config.GameScene);
		
		Config.mplayer = new MediaPlayer(Config.Pacintro);
		Config.mplayer.play();
		
	}

	public void back(MouseEvent  event) throws Exception {
		Config.MainStage.setScene(Config.HomeScene);

	}
	
	public void closeWindow() 
	{
		((Stage) exit.getScene().getWindow()).close(); 
	}
	
	
}
