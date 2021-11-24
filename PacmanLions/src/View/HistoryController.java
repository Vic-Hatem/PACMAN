package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HistoryController {

	   @FXML
	    private ImageView exit;
	
	
		
		public void back(MouseEvent  event) throws Exception {
			((Stage) exit.getScene().getWindow()).close();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
			Scene scene = new Scene(root,637,546);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
		
}
