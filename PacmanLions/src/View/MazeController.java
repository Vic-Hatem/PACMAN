package View;

import java.net.URL;
import java.util.ResourceBundle;
import Model.Energizer;
import Model.Maze;
import Model.PowerPellet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MazeController implements Initializable{

    @FXML
    private Label score;

    @FXML
    private Label level;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView sound;
    
    @FXML
    private AnchorPane boardPane;  
    
    @FXML
    private ImageView life1;

    @FXML
    private ImageView life2;

    @FXML
    private ImageView life3;
    
	@FXML
    private ImageView exit;
	
    @FXML
    private ImageView back;
	
    
    
    //this function returns an FX object - AnchorPane that include the MAZE we are building into it in this function
	public AnchorPane createBoardView() {
		Maze theMaze = new Maze();
		int i,j;
		int [][]maze = theMaze.getMaze();
		for(j=0 ; j<maze.length ; j++) {
			for(i=0 ; i<maze.length ; i++) {
				//We chose switch-case in order to handle any value on board (values we meet at this point)
				switch(maze[i][j]) {
				  case 1: //Wall
						Rectangle rec =new Rectangle(j*30,i*30,28,28);
						rec.setFill(Color.BLUE);
					    boardPane.getChildren().add(rec);
					    System.out.println("built wall");  
				    break;
				  case 3: //PowerPellet
//				        PowerPellet pp = new PowerPellet(i,j);
				        Circle circle =new Circle(j*30+15,i*30+15,8);
						circle.setFill(Color.YELLOW);
					    boardPane.getChildren().add(circle);
					    System.out.println("built PowePellet"); 
					break;
				  case 4: //Question
					   ImageView img = new ImageView("questionmark.png");
					   img.setLayoutX(j*30+5);
					   img.setLayoutY(i*30+5);
					   img.setFitWidth(25);
					   img.setFitHeight(25);
					    boardPane.getChildren().add(img);
					    System.out.println("built questionmark"); 
					break;
				  case 10: //Ghost1
				        //here we have to do new ghost 1
					   ImageView img1 = new ImageView("ghost1.png");
					   img1.setLayoutX(j*30+5);
					   img1.setLayoutY(i*30+5);
					   img1.setFitWidth(25);
					   img1.setFitHeight(25);
					    boardPane.getChildren().add(img1);
					    System.out.println("built ghost1"); 
					break;
				  case 11: //Ghost2
					     //here we have to do new ghost 2
					   ImageView img2 = new ImageView("ghost2.png");
					   img2.setLayoutX(j*30+5);
					   img2.setLayoutY(i*30+5);
					   img2.setFitWidth(25);
					   img2.setFitHeight(25);
					    boardPane.getChildren().add(img2);
					    System.out.println("built ghost2"); 
					break;
				  case 12: //Ghost3
					     //here we have to do new ghost 3
					   ImageView img3 = new ImageView("ghost3.png");
					   img3.setLayoutX(j*30+5);
					   img3.setLayoutY(i*30+5);
					   img3.setFitWidth(25);
					   img3.setFitHeight(25);
					    boardPane.getChildren().add(img3);
					    System.out.println("built ghost3"); 
					break;
				  case 5: //Pacman
					   ImageView pacman = new ImageView("pacman.png");
					   pacman.setLayoutX(j*30);
					   pacman.setLayoutY(i*30);
					   pacman.setFitWidth(28);
					   pacman.setFitHeight(28);
					    boardPane.getChildren().add(pacman);
					    System.out.println("built pacman"); 
					break;
				  case 0: //Energizer
//				        Energizer energy = new Energizer(i,j);
				        Circle miniCircle =new Circle(j*30+15,i*30+15,4);
				        miniCircle.setFill(Color.YELLOW);
					    boardPane.getChildren().add(miniCircle);
					    System.out.println("built Energizer");  
				    break;
				  default:
				    // keep empty - for when we start running the game then we have to check the empty option . 
				}
		
			}
		}
		//returning FULL MAZE !
		return boardPane;
	}

	
	public void back(MouseEvent  event) throws Exception {
		((Stage) back.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
		Scene scene = new Scene(root,637,546);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		createBoardView();
	}
    

	
    
}
