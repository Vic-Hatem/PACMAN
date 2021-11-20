package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainPageController extends Application{


    @Override
	public void start(Stage primaryStage) {
    	Stage window = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
			Scene scene = new Scene(root,637,546);
			window.setScene(scene);
			window.setTitle("Pacman");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
