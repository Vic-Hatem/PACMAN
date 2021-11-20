package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class QuestionsController {

    @FXML
    private Button addQuestion;

    @FXML
    private Button editQuestion;

    @FXML
    private Button deleteQuestion;
    
    @FXML
    private ImageView exit;
    
    
	public void AddQues(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/AddQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("add question");
		primaryStage.show();
	}
    
	public void EditQues(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/EditQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("edit question");
		primaryStage.show();
	}
	
	public void DeleteQues(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/DeleteQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("delete question");
		primaryStage.show();
	}
    
    
	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
	}
    
}
