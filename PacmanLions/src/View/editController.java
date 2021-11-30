package View;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.SysData;
import Model.Question;
import Utils.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class editController implements Initializable{
	   @FXML
	    private TextArea editques  = new TextArea();

	    @FXML
	    private ComboBox<Difficulty> editDifficulty = new ComboBox<Difficulty>();

	    @FXML
	    private TextField editanswer1 = new TextField();

	    @FXML
	    private TextField editanswer2 = new TextField();

	    @FXML
	    private TextField editanswer3 = new TextField();

	    @FXML
	    private TextField editanswer4 = new TextField();

	    @FXML
	    private RadioButton redit2= new RadioButton();

	    @FXML
	    private ToggleGroup addquestionradioanswers;
	    
	    @FXML
	    private ToggleGroup editquestionradioanswer;
	    
	    @FXML
	    private RadioButton redit1= new RadioButton();

	    @FXML
	    private RadioButton redit3= new RadioButton();

	    @FXML
	    private RadioButton redit4= new RadioButton();
	    
	    @FXML
	    private ImageView back;
	    
	    @FXML
	    private Button saveEdit;
	    

		
		public void back(MouseEvent  event) throws Exception {
			((Stage) saveEdit.getScene().getWindow()).close();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
			Scene scene = new Scene(root,637,546);
			primaryStage.setScene(scene);
			primaryStage.show();
		}

		
		
		public void editQuestion(ActionEvent event) throws Exception {
			
			String ques = editques.getText();
			String answer1 = editanswer1.getText();
			String answer2 = editanswer2.getText();
			String answer3 = editanswer3.getText();
			String answer4 = editanswer4.getText();
			String rightAnswer = null ;
			int flag=0;
			Difficulty d = editDifficulty.getValue();
			if (redit1.isSelected()) {
				rightAnswer = answer1;
			} else if (redit2.isSelected()) {
				rightAnswer = answer2;
			} else if (redit3.isSelected()) {
				rightAnswer = answer3;
			} else if (redit4.isSelected()){
				rightAnswer = answer4;
			}
			else
			{
				flag=1;
			}
			
			if(ques.isEmpty()||flag==1||editDifficulty.getValue()==null
					|| editanswer1.getText().isEmpty()||editanswer2.getText().isEmpty()|| editanswer3.getText().isEmpty()|| 
					editanswer4.getText().isEmpty())
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("missing fields");
				alert.setContentText("You must fill all the fields!");
				alert.show();
				 
			}
			else
			{
				Question q1 = new Question(ques,answer1,answer2,answer3,answer4,rightAnswer,d,"LIONS");
				if(answer1.equals(answer2) || answer1.equals(answer3) || answer1.equals(answer4) || answer2.equals(answer3) || answer2.equals(answer4) || answer3.equals(answer4)) 
				{ 
					Alert alert = new Alert(AlertType.ERROR); 
				alert.setTitle("same answer");
				alert.setContentText("You must enter different answers!"); 
				alert.show(); 
				}
				else
				{
					q1.setAnswer1(answer1);
					q1.setAnswer2(answer2);
					q1.setAnswer3(answer3);
					q1.setAnswer4(answer4);
					

					SysData.getInstance().editQuestion(QuestionsController.updatedQ,q1);
					SysData.getInstance().writeJSON(false);
					SysData.getInstance().loadQuestions();
					
					
					((Stage) back.getScene().getWindow()).close();
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
					Scene scene = new Scene(root,637,546);
					primaryStage.setScene(scene);
					primaryStage.setTitle("questions");
					primaryStage.show();
				}
			
		
				
			}
			
		}
		@SuppressWarnings("unlikely-arg-type")
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
		

			Question q = QuestionsController.updatedQ;

			editques.setText(q.getQuestion());
			editanswer1.setText(q.getAnswer1());
			editanswer2.setText(q.getAnswer2());
			editanswer3.setText(q.getAnswer3());
			editanswer4.setText(q.getAnswer4());
			String rightAnswer = q.getCorrect_ans();
			if (rightAnswer.equals("1")) {
				redit1.setSelected(true);
			} else if (rightAnswer.equals("2")) {
				redit2.setSelected(true);
			} else if (rightAnswer.equals("3") ){
				redit3.setSelected(true);
			} else {
				redit4.setSelected(true);
			}
			editDifficulty.setValue(q.getLevel());
			ObservableList<Difficulty> editlist=FXCollections.observableArrayList(Difficulty.values());
			editDifficulty.setItems(editlist);
		
		}
}
