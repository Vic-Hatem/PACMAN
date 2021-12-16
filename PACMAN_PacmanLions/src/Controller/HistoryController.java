package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Config;
import Model.Game;
import Model.Question;
import Utils.Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HistoryController implements Initializable{

//    @FXML
//    private ListView<Game> HistoryList = new ListView<Game>();
//    
//    public ArrayList<Game> games = new ArrayList<Game>();
//    
//	SysData sysData = SysData.getInstance();
//	
//		public ListView<Game> getHistoryList() {
//		return HistoryList;
//	}
//
//	public void setHistoryList(ListView<Game> historyList) {
//		HistoryList = historyList;
//	}
    ObservableList<Game> games = FXCollections.observableArrayList();

    @FXML
    private TableView<Game> gamesTbl;

//    @FXML
//    private TableColumn<Game, Integer> colRank;

    @FXML
    private TableColumn<Game, String> colNickname;

    @FXML
    private TableColumn<Game, Integer> colScore;

    @FXML
    private TableColumn<Game, Integer> colLevel;

    @FXML
    private TableColumn<Game,Integer> colQuestions;
	
	
//    private void setTableappearance() {
//    	gamesTbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//    }

    
    
    private void createTable() {
    	
    	System.out.println("im in CT before : " + games);

    	colNickname.setCellValueFactory(new PropertyValueFactory<Game, String>("Nickname"));
    	colNickname.setStyle("-fx-background-color: black ;-fx-text-fill: white ;");
    	colScore.setCellValueFactory(new PropertyValueFactory<Game, Integer>("score"));
    	colScore.setStyle("-fx-background-color: black ;-fx-text-fill: white ;");
    	colLevel.setCellValueFactory(new PropertyValueFactory<Game, Integer>("level"));
    	colLevel.setStyle("-fx-background-color: black ;-fx-text-fill: white ;");
    	colQuestions.setCellValueFactory(new PropertyValueFactory<Game, Integer>("totalQuest"));
    	colQuestions.setStyle("-fx-background-color: black ;-fx-text-fill: white ;");
    	System.out.println("im in CT : " + games);
    	gamesTbl.setItems(games);
    }

    
    
	private void fillGames() {
		// TODO Auto-generated method stub
	
        ArrayList<Game> gamesData = SysData.getInstance().getGames();
        Collections.sort(gamesData, new Comparator<Game>() {
        	  @Override
        	  public int compare(Game u1, Game u2) {
        	    return Integer.compare(u2.getScore(), u1.getScore());
        	  }
        	});
        System.out.println(gamesData);
        games.addAll(gamesData);
        System.out.println(games);
	}
    
    
    
    
		public void back(MouseEvent  event) throws Exception {
			//((Stage) exit.getScene().getWindow()).close();
			//Stage primaryStage = new Stage();
		//	Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
		//	Scene scene = new Scene(root,637,546);
		//	primaryStage.setScene(scene);
		//	primaryStage.show();
			Config.MainStage.setScene(Config.HomeScene);
		}



		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
//			SysData.getInstance().loadData();
//
//			games = sysData.getGames();
//			
//			ObservableList<Game> game = FXCollections.observableArrayList(games);
//			HistoryList.setItems(game);
			
//	        setTableappearance();
//			games.clear();
			SysData.getInstance().loadData();
	    	fillGames();


		}



		
}
