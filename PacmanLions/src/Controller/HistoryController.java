package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import Model.Config;
import Model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/*@authors Hatem and Moran*/
public class HistoryController implements Initializable{

    ObservableList<Game> games = FXCollections.observableArrayList();

    @FXML
    private TableView<Game> gamesTbl;


    @FXML
    private TableColumn<Game, String> colNickname;

    @FXML
    private TableColumn<Game, Integer> colScore;

    @FXML
    private TableColumn<Game, Integer> colLevel;

    @FXML
    private TableColumn<Game,String> colQuestions;
	
	
    private void createTable() {
        //adding the data to the table columns structure
    	colNickname.setCellValueFactory(new PropertyValueFactory<Game, String>("Nickname"));
    	colScore.setCellValueFactory(new PropertyValueFactory<Game, Integer>("score"));
    	colLevel.setCellValueFactory(new PropertyValueFactory<Game, Integer>("level"));
    	colQuestions.setCellValueFactory(new PropertyValueFactory<Game, String>("totalQuest"));
    	gamesTbl.setItems(games);
    	System.out.println("Created Table with: "+games);
    }


    
  //Fill the Games in the table
	private void fillGames() {
		// TODO Auto-generated method stub
	
        ArrayList<Game> gamesData =new ArrayList<Game>();
        gamesData.addAll(SysData.getInstance().getGames());
       
      //Sorting the games by Score - descending
        Collections.sort(gamesData, new Comparator<Game>() {
        	  @Override
        	  public int compare(Game u1, Game u2) {
        	    return Integer.compare(u2.getScore(), u1.getScore());
        	  }
        	});
       
        games.clear();

        games.addAll(gamesData);
	}
    
    
    
    
		public void back(MouseEvent  event) throws Exception {

			Config.MainStage.setScene(Config.HomeScene);
		}



		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			//Filling the fx object when opening the page history
			SysData.getInstance().loadData();
	    	fillGames();
	    	createTable();
		}

}