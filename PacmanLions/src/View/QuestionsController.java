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
	}
    
	@SuppressWarnings("unlikely-arg-type")
	public void EditQues(ActionEvent event) throws Exception {
		updatedQ = listOfQuestions.getSelectionModel().getSelectedItem();
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/EditQuestion.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.setTitle("edit question");
		primaryStage.show();
		
	}
	

	public void closeWindow() {
		((Stage) exit.getScene().getWindow()).close();
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
			rightAnswer = "1";
		} else if (ranswer2.isSelected()) {
			rightAnswer = "2";
		} else if (ranswer3.isSelected()) {
			rightAnswer = "3";
		} else {
			rightAnswer = "4";
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
					if(a1.equals(a2) || a1.equals(a3) || a1.equals(a4) || a2.equals(a3) || a2.equals(a4) || a3.equals(a4))
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("same answer");
						alert.setContentText("You must enter different answers!");
						alert.show();
						
					}
					
					boolean isAdded=SysData.getInstance().addQuestion(q);
					if(!isAdded)
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("same question exists");
						alert.setContentText("You must enter different question!");
						alert.show();
						
					}
					else
					{
					
						SysData.getInstance().writeJSON(false);
						SysData.getInstance().loadQuestions();
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
    
    public void back1(MouseEvent  event) throws Exception {
		((Stage) exit.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	
	public void back(MouseEvent  event) throws Exception {
		((Stage) exit.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/Questions.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void deleteQuestion(ActionEvent event) throws Exception {

		Question q = listOfQuestions.getSelectionModel().getSelectedItem();
		listOfQuestions.getItems().remove(q);
		SysData.getInstance().removeQuestion(q);
		
		SysData.getInstance().writeJSON(false);
		SysData.getInstance().loadQuestions();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ObservableList<Difficulty> list=FXCollections.observableArrayList(Difficulty.values());
		difficulty.setItems(list);
		
		SysData.getInstance().loadQuestions();

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


		

	}
}
