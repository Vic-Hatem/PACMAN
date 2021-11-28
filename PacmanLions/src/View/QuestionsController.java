package View;

import Model.Question;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controller.SysData;
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
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuestionsController implements Initializable{

    @FXML
    private Button addQuestion;

    @FXML
    private Button editQuestion;

    @FXML
    private Button deleteQuestion;
    
    @FXML
    private ImageView exit;
    
    @FXML
    private ListView<Question> listOfQuestions = new ListView<Question>();
    
    public ListView<Question> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(ListView<Question> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}



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
    private ComboBox<Difficulty> difficulty = new ComboBox<Difficulty>();

    @FXML
    private Button add;
    
    
    
    
    ////////////////////////////// EDITTTTTTT 
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
    
	static Question updatedQ;
	private HashMap<Difficulty, ArrayList<Question>> questions;
	SysData sysData = SysData.getInstance();
	private ArrayList<Question> level = new ArrayList<Question>();

	public void AddQues(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/AddQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("add question");
		primaryStage.show();
		System.out.println("moran im here");
	}
    
	@SuppressWarnings("unlikely-arg-type")
	public void EditQues(ActionEvent event) throws Exception {
		this.editques.clear();
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/EditQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("edit question");
		updatedQ = listOfQuestions.getSelectionModel().getSelectedItem();	
		
		System.out.println(updatedQ);
		editques.setText(updatedQ.getQuestion());
		System.out.println(editques.getText());
		editanswer1.setText(updatedQ.getAnswer1());
		editanswer2.setText(updatedQ.getAnswer2());
		editanswer3.setText(updatedQ.getAnswer3());
		editanswer4.setText(updatedQ.getAnswer4());
		String rightAnswer = updatedQ.getCorrect_ans();
		if (rightAnswer.equals(editanswer1)) {
			redit1.setSelected(true);
		} else if (rightAnswer.equals(editanswer2)) {
			redit2.setSelected(true);
		} else if (rightAnswer.equals(editanswer3) ){
			redit3.setSelected(true);
		} else {
			redit4.setSelected(true);
		}
		editDifficulty.setValue(updatedQ.getLevel());
		ObservableList<Difficulty> editlist=FXCollections.observableArrayList(Difficulty.values());
		editDifficulty.setItems(editlist);
	
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
	
	public void back1(MouseEvent  event) throws Exception {
		((Stage) exit.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
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
				
				System.out.println(QuestionsController.updatedQ.getQuestion());
				SysData.getInstance().addQuestion(q1);
				SysData.getInstance().removeQuestion(QuestionsController.updatedQ);
				//SysData.getInstance().saveQuestions(null);
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
	
    @FXML
    public void addQuestion(MouseEvent event) throws Exception {
		String ques = question.getText();
		String a1 = answer1.getText();
		String a2 = answer2.getText();
		String a3 = answer3.getText();
		String a4 = answer4.getText();
		String rightAnswer;
		
		if (ranswer1.isSelected()) {
			rightAnswer = a1;
		} else if (ranswer2.isSelected()) {
			rightAnswer = a2;
		} else if (ranswer3.isSelected()) {
			rightAnswer = a3;
		} else {
			rightAnswer = a4;
		}
		
		if(!ranswer1.isSelected() && !ranswer2.isSelected() && !ranswer3.isSelected()&&!ranswer4.isSelected())
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("right answer");
			alert.setContentText("You must select the right answer!");
			alert.show();
		}
		else
		{
			if(difficulty.getValue()==null)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("difficulty");
				alert.setContentText("You must select difficulty of the question");
				alert.show();
				
			}
			
		
			else
			{
				Difficulty d = difficulty.getValue();
				Question q = new Question(ques,a1,a2,a3,a4,rightAnswer,d,"LIONS");
                System.out.println("this is the question");
                System.out.println(q);
					if(a1.equals(a2) || a1.equals(a3) || a1.equals(a4) || a2.equals(a3) || a2.equals(a4) || a3.equals(a4))
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("same answer");
						alert.setContentText("You must enter different answers!");
						alert.show();
						
					}
					
					boolean isAdded=SysData.getInstance().addQuestion(q);
					System.out.println(SysData.getInstance().getQuestions());
					if(!isAdded)
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("same question exists");
						alert.setContentText("You must enter different question!");
						alert.show();
						
					}
					else
					{
					
				
			
				//SysData.getInstance().loadQuestions(null);
				//SysData.getInstance().saveQuestions(null);
						closeWindow();
						Stage primaryStage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
						Scene scene = new Scene(root,637,546);
						primaryStage.setScene(scene);
						primaryStage.setTitle("Questions Management");
						primaryStage.show();
					}
				}
			}
			
			
		}

	
	public void back(MouseEvent  event) throws Exception {
		((Stage) exit.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		System.out.println(FXCollections.observableArrayList(Difficulty.values()));
		ObservableList<Difficulty> list=FXCollections.observableArrayList(Difficulty.values());
		difficulty.setItems(list);
		
		SysData.getInstance().loadQuestions(null);

		questions = sysData.getQuestions();
		for (Difficulty d : questions.keySet()) {
			for (Question q : questions.get(d)) {
				if (!level.contains(q)) {
					level.add(q);
				}

			}
		}

		ObservableList<Question> question = FXCollections.observableArrayList(level);
		listOfQuestions.setItems(question);

		
		
//		Question q = QuestionsController.updatedQ;	
//		editques.setText(q.getQuestion());
//		System.out.println(editques.getText());
//		editanswer1.setText(q.getAnswer1());
//		editanswer2.setText(q.getAnswer2());
//		editanswer3.setText(q.getAnswer3());
//		editanswer4.setText(q.getAnswer4());
//		String rightAnswer = q.getCorrect_ans();
//		if (rightAnswer.equals(editanswer1)) {
//			redit1.setSelected(true);
//		} else if (rightAnswer.equals(editanswer2)) {
//			redit2.setSelected(true);
//		} else if (rightAnswer.equals(editanswer3) ){
//			redit3.setSelected(true);
//		} else {
//			redit4.setSelected(true);
//		}
//		editDifficulty.setValue(q.getLevel());
//		ObservableList<Difficulty> editlist=FXCollections.observableArrayList(Difficulty.values());
//		editDifficulty.setItems(editlist);

		
		

	}
}
