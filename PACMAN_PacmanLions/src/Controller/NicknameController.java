package Controller;


import Model.Config;
import Model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class NicknameController {

    @FXML
    private ImageView exit;

    @FXML
    public TextField nickname;

    @FXML
    private Button go;

    
	public void GoStartGame() throws Exception {
		
		//closeWindow();
		System.out.println("At NickName Starting Game");
		//Stage primaryStage = new Stage();
		//FXMLLoader loadr = new FXMLLoader(getClass().getResource("/View/Maze.fxml"));
		
		//was
		//Parent root = FXMLLoader.load(getClass().getResource("/View/Maze.fxml"));
		//modified by @epc for key events
		//Parent root = loadr.load();
		//Pac_move cont = loadr.getController();
		
		//Scene scene = new Scene(root,900,950);
		
		
		//primaryStage.setScene(scene);
		Config.GManager = new Game(nickname.getText(),1,0,3,0,0,0,0);
		MazeController mc = Config.gmloader.getController();;
		//mc.Level("TEST");
		mc.GameInstance(Config.GManager);

		Config.MainStage.setScene(Config.GameScene);
		
		Config.mplayer = new MediaPlayer(Config.Pacintro);
		Config.mplayer.play();
		
		//primaryStage.setTitle("Maze");
		//primaryStage.show();
	}
	public void nextlvl() 
	{
		
		MazeController mc = Config.gmloader.getController();;
		//mc.Level("TEST");
		mc.GameInstance(Config.GManager);
		
		Config.MainStage.close();
		Platform.runLater(()->{
			try {
				new mainPageController().start(new Stage());
				Config.MainStage.setScene(Config.GameScene);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	public void back(MouseEvent  event) throws Exception {
		//((Stage) exit.getScene().getWindow()).close();
		//Stage primaryStage = new Stage();
		//Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
		//Scene scene = new Scene(root,637,546);
		//primaryStage.setScene(scene);
		//primaryStage.setTitle("Pacman");
		//primaryStage.show();
		Config.MainStage.setScene(Config.HomeScene);

	}
	
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close(); 
	}
}
