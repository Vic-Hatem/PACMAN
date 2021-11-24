package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    
    @FXML
    private ListView<?> listOfQuestions;
    
    @FXML
    private TextArea question;

    @FXML
    private TextField answer1;

    @FXML
    private TextField answer2;

    @FXML
    private TextField answer3;

    @FXML
    private TextField answer4;

    @FXML
    private RadioButton ranswer2;

    @FXML
    private RadioButton ranswer1;

    @FXML
    private RadioButton ranswer3;

    @FXML
    private RadioButton ranswer4;

    @FXML
    private ComboBox<?> difficulty;

    @FXML
    private Button add;
    @FXML
    private TextArea editques;

    @FXML
    private ComboBox<?> editDifficulty;

    @FXML
    private TextField editanswer1;

    @FXML
    private TextField editanswer2;

    @FXML
    private TextField editanswer3;

    @FXML
    private TextField editanswer4;

    @FXML
    private RadioButton redit2;

    @FXML
    private ToggleGroup addquestionradioanswers;
    
    @FXML
    private ToggleGroup editquestionradioanswer;
    
    @FXML
    private RadioButton redit1;

    @FXML
    private RadioButton redit3;

    @FXML
    private RadioButton redit4;

    @FXML
    private Button saveEdit;
    
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
