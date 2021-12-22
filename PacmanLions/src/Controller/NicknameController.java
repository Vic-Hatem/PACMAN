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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
    @FXML
    private Button change;

    @FXML
    private AnchorPane choseMode;

    @FXML
    private RadioButton flower;

    @FXML
    private RadioButton snowflake;

    @FXML
    private RadioButton fire;
    
    @FXML
    private ToggleGroup mode;
    
    static int flag = 0;
    
    
    
	public void ChangeMode(ActionEvent event) throws Exception {
    	choseMode.setVisible(true);
    	
	}
    public NicknameController() 
    {
    }
    
    
    
	public void GoStartGame() throws Exception {
		

		System.out.println("At NickName Starting Game");


		Config.GManager = new Game(nickname.getText(),1,1,3,0,0,0,0);
		
		MazeController mc = Config.gmloader.getController();;
		mc.GameInstance(Config.GManager); //calls the method from mazecontroller before its loaded

		
		/*getting mode*/
		
		if (flower.isSelected()) {
			NicknameController.flag = 1;
			System.out.println("flag = " + NicknameController.flag);
		} else if (snowflake.isSelected()) {
			NicknameController.flag = 2;
			System.out.println("flag = " + NicknameController.flag);
		} else if (fire.isSelected()) {
			NicknameController.flag = 3;
			System.out.println("flag = " + NicknameController.flag);
		} else {
			NicknameController.flag = 0;
			System.out.println("flag = " + NicknameController.flag);
		}

		
		/*end*/
		
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
	
	   
    public void initialize(URL location, ResourceBundle resources) 
	{
    	
    	choseMode.setVisible(false);
    	go.disableProperty().bind(Bindings.createBooleanBinding(()->nickname.getText().trim().isEmpty(), nickname.textProperty()));
    	error.visibleProperty().bind(Bindings.createBooleanBinding(()->nickname.getText().trim().isEmpty(), nickname.textProperty()));

	}
}
