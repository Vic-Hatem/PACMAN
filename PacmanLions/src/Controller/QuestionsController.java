package Controller;

import Model.Config;
import Model.Question;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Utils.Difficulty;
import javafx.application.Platform;
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
/*@authors Hatem and Moran*/
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
    
    
    // static because we want to convey this parameter to another page (editController)
	static Question updatedQ;

	private HashMap<Difficulty, ArrayList<Question>> questions;
	
	SysData sysData = SysData.getInstance();
	
	private ArrayList<Question> level = new ArrayList<Question>();

	//This method is to open the add question page
	public void AddQues(ActionEvent event) throws Exception {
    	Config.MainStage.setScene(Config.addquest);

	}
    
	//This method is to open the edit question page
		@SuppressWarnings("unlikely-arg-type")
		public void EditQues(ActionEvent event) throws Exception {
			//updatedQ - parameter that holds the question we selected from the list to the update question page
			updatedQ = listOfQuestions.getSelectionModel().getSelectedItem();
			if(updatedQ == null) 
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Question");
				alert.setContentText("You must select a Question First!");
				alert.show();
				return;
			}
			editController ec = Config.EditQuestLoader.getController();;
			ec.PrepareEdit();
			Config.MainStage.setScene(Config.editquest);
		}
	

		public void closeWindow() {
			((Stage) exit.getScene().getWindow()).close();
		}
	
	
		//This method is to add new question to our collection
	    @FXML
	    public void addQuestion(MouseEvent event) throws Exception {
			String ques = question.getText();
			String a1 = answer1.getText();
			String a2 = answer2.getText();
			String a3 = answer3.getText();
			String a4 = answer4.getText();
			String rightAnswer;
			Difficulty d = difficulty.getValue();

			
			//Checking which answer is assigned as the correct answer
			if (ranswer1.isSelected()) {
				rightAnswer = "1";
			} else if (ranswer2.isSelected()) {
				rightAnswer = "2";
			} else if (ranswer3.isSelected()) {
				rightAnswer = "3";
			} else {
				rightAnswer = "4";
			}
			
			//Checking if a radio button was selected - because if not then we cannot add the question
			if(!ranswer1.isSelected() && !ranswer2.isSelected() && !ranswer3.isSelected()&&!ranswer4.isSelected())
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("right answer");
				alert.setContentText("You must select the right answer!");
				alert.show();
			}
			else {
				//Checking if the user chose one of the values from the combobox - difficulty(EASY MEDIUM HARD)
				if(difficulty.getValue()==null)
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("difficulty");
					alert.setContentText("You must select difficulty of the question");
					alert.show();
					
				}
				else {
				//We make sure that none of the fields in the "form" are empty 
				if(ques.isEmpty() || a1.isEmpty() || a2.isEmpty()|| a3.isEmpty()|| a4.isEmpty())
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Type Question");
					alert.setContentText("You didn't Fill all the Fields (question and answers)");
					alert.show();
					
				}
				else {
				//we make sure that the question has 4 distinct answers 
				if(a1.equals(a2) || a1.equals(a3) || a1.equals(a4) || a2.equals(a3) || a2.equals(a4) || a3.equals(a4))
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("same answer");
					alert.setContentText("You must enter different answers!");
					alert.show();
					
				}
				else
				{
					//Finally adding the question when all the checks was OK
					Question q = new Question(ques,a1,a2,a3,a4,rightAnswer,d,"LIONS");	
					
						//isAdded = returnes True if the question was added successfully
						boolean isAdded=SysData.getInstance().addQuestion(q);
						System.out.println(isAdded);
						if(!isAdded) //false then it found the same question in our collection
						{
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("same question exists");
							alert.setContentText("You must enter different question!");
							alert.show();
							
						}
						else
						{
							//inform the user that the question was added successfully
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Done");
							alert.setContentText("Your Question Was Added Succeccfully !");
							alert.show();
							

							SysData.getInstance().observableMethod();

							Config.MainStage.close();
							Platform.runLater(()->{
								try {
									new mainPageController().start(new Stage());
									Config.MainStage.setScene(Config.HomeScene);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});

						}
					}
				}
				}
			}
			}
    
	    public void back1(MouseEvent  event) throws Exception {
	    	Config.MainStage.setScene(Config.HomeScene);
		}

		
		public void back(MouseEvent  event) throws Exception {
			Config.MainStage.setScene(Config.QandAScene);
		}

		//This method is to delete a selected question from our collection
		public void deleteQuestion(ActionEvent event) throws Exception {
			//q - get the selected question from the list 
			Question q = listOfQuestions.getSelectionModel().getSelectedItem();
			
			//if the user didnt select anu of the questions !
			if(q == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("SELECT");
				alert.setContentText("You did not select any of the questions from the list , Please Choose one");
				alert.show();
				
			}
			else {
			listOfQuestions.getItems().remove(q);
			SysData.getInstance().removeQuestion(q);
			 
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Done");
			alert.setContentText("Your Question Was Deleted Succeccfully !");
			alert.show();
		
			SysData.getInstance().observableMethod();

			}
		}
		
			@Override
			public void initialize(URL location, ResourceBundle resources) {
				// TODO Auto-generated method stub
				
				ObservableList<Difficulty> list=FXCollections.observableArrayList(Difficulty.values());
				difficulty.setItems(list);
				
				SysData.getInstance().loadQuestions("src/QuestionsFormat.txt");

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
