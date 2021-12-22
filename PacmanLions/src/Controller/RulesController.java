package Controller;

import Model.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/*@authors Hatem and Moran*/
public class RulesController {
	
	   @FXML
	    private ImageView exit;
	    
	
	
	public void back(MouseEvent  event) throws Exception {
		Config.MainStage.setScene(Config.HomeScene);
	}
	
}
