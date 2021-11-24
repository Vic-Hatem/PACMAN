package View;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NicknameController {

    @FXML
    private ImageView exit;

    @FXML
    private TextField nickname;

    @FXML
    private Button go;

    
	public void GoStartGame(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Maze.fxml"));
		Scene scene = new Scene(root,900,950);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Maze");
		primaryStage.show();
	}
   
	public void back(MouseEvent  event) throws Exception {
		((Stage) exit.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pacman");
		primaryStage.show();

	}
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}
}
