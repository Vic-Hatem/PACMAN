package Controller;

import Model.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author Nahawand and Grace
 *
 */
public class mainPageController extends Application{

	
    @Override
	public void start(Stage primaryStage) throws Exception {
    	Config.Init(); //initializes node Definations
		try {
			Config.MainStage = new Stage();
			Config.HomeScene = new Scene(Config.root,Config.SizeX,Config.SizeY);
			Config.QandAScene = new Scene(Config.QandAroot,Config.SizeX,Config.SizeY);
			Config.RulesScene= new Scene(Config.Rulesroot,Config.SizeX,Config.SizeY);
			Config.HistoryScene = new Scene(Config.Historyroot,Config.SizeX,Config.SizeY);
			Config.NickScene = new Scene(Config.nickroot,Config.SizeX,Config.SizeY);
			Config.addquest = new Scene(Config.Add_quest,Config.SizeX,Config.SizeY);
			Config.editquest = new Scene(Config.Edit_quest,Config.SizeX,Config.SizeY);
			Config.GameScene = new Scene(Config.gmroot,Config.SizeX,Config.SizeY);

			
			Config.MainStage.setMaximized(true);
			Config.MainStage.setScene(Config.HomeScene);
			Config.MainStage.setTitle("Pacman");
			
			Config.MainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static void main(String[] args) 
	{
		
			launch(args);

		
		
	}

}
