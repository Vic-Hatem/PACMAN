/**
 * 
 */
package Model;

import java.net.URL;
import java.nio.file.Paths;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
/*
 * 
 * This is the Configration File for the game it handles all the initialiation and global variables that needs to be shared across the game
 * 
 * for example fxml files assets music files etc
 * this file is used for seamless scene transistion
 * 
 * 	@AUTHORS - Nahawand & Grace
 * */
public class Config {
	
	static Screen screen =Screen.getPrimary();;
	static Rectangle2D bounds = screen.getVisualBounds();
//	
	public static double SizeX = bounds.getWidth(); //Width OF game window
	public static double SizeY = bounds.getHeight();	//Height Of game window
	
	//public static double SizeX = 1200; //Width OF game window
	//public static double SizeY = 950;//Height Of game window
	public static Game GManager;		//global object of Game class
	public static Stage MainStage;		//global object of Main Stage which will be shared across the entire game

	
	public static BorderPane root,nickroot,QandAroot,Historyroot,Rulesroot,gmroot,Add_quest,Edit_quest; //Fxml files will be loaded into these objects 
	/*
		root = Start page  when game starts
		nickroot = Nickname page after user clicks on Start game
		QandAroot = Question and answer page when user clicks on Questions
		Historyroot = History root which opens when user clicks on history
		Rulesroot = Rule root which opens when user clicks on rules
		gmroot= the root which loads the Maze.fxml file and displays the game
	*/
  public static Scene HomeScene,NickScene,GameScene,QandAScene,HistoryScene,RulesScene,addquest,editquest; //creating global scenes
  
  public static FXMLLoader gmloader,nicloader,EditQuestLoader; //specific loaders for Gmloader and nicloader to handle stage restart(when user clicks on restart the entire stage is reintialized)
  
  
  public static MediaPlayer mplayer = null;//Initializing Music Player
  //music
  public  static URL F_Die =Config.class.getResource("/View/pacman_death.wav");//pacman death sound
  public static URL  F_eat =Config.class.getResource( "/View/pacman_eat.wav"); //pacman eat sound
  public static URL  F_Start =Config.class.getResource("/View/pacman_intro.wav"); //pacman intro sound
  public static URL  F_GhostDie=Config.class.getResource("/View/pacman_eatghost.wav");//pacman killing ghost sound
  public static URL  F_Finish = Config.class.getResource("/View/pacman_intermission.wav"); //pacman winning sound
  public static URL  F_Siren = Config.class.getResource("/View/Pac_siren.wav");//pacman siren sound
  public static URL  F_Corr_Ans = Config.class.getResource("/View/CorrectAnswer.wav");//pacman siren sound
  public static URL  F_Wron_Ans = Config.class.getResource("/View/WrongAnswer.wav");//pacman siren sound
  public static URL  F_Question = Config.class.getResource("/View/QuestionBonus.wav");//pacman siren sound
  public static URL  F_Question_Wait = Config.class.getResource("/View/QuestionWait.wav");//pacman siren sound

  public static Media PacDie; //this  loads the file 
  public static Media PacEat;
  public static Media PacEatGhost;
  public static Media Pacintro;
  public static Media PacWin;
  public static Media PacSiren;
  public static Media CorrectAnswer;
  public static Media WrongAnswer;
  public static Media Question;
  public static Media Question_Wait;

  //pacman Images in yellow
  public static Image Y_LEFT_PACMAN ;
  public static Image Y_RIGHT_PACMAN ;
  public static Image Y_UP_PACMAN ;
  public static Image Y_DOWN_PACMAN ;
  
//pacman Images in blue for powerpellet
  public static Image B_LEFT_PACMAN ;
  public static Image B_RIGHT_PACMAN ;
  public static Image B_UP_PACMAN ;
  public static Image B_DOWN_PACMAN ;
  
  //ghosts pictures
  public static Image Ghost1;
  public static Image Ghost2;
  public static Image Ghost3;
  
  //another elements of maze pictures
  public static Image Tunnel;
  public static Image Energizer;
  public static Image Pellet_img;
  public static Image Question_img_easy;
  public static Image Question_img_medium;
  public static Image Question_img_hard;
  public static Image Audio_img;
  public static Image Audio_img_mute;
  public static Image Wall_img;
  
  //Variable that controls maze size
  public static final int Scale = 20;
  
  
	public static void Init() throws Exception //this constructor is used to initialize game objects and files
	{
		System.out.println("Initilizing");
		//walls
		Wall_img  = new Image("View/wall.png");
		//Question 
		Question_img_easy = new Image("View/easy.png");
		Question_img_medium = new Image("View/medium.png");
		Question_img_hard = new Image("View/hard.png");

		//Power Pellet
		Pellet_img  = new Image("View/powepellet.png");
		//energizer
		Energizer = new Image("View/energizer.png");
		//Ghost initilization
		Ghost1 = new Image("View/ghost1.png");
		Ghost2 = new Image("View/ghost2.png");
		Ghost3 = new Image("View/ghost3.png");
		
		//initializes Yellow Pacman images
		Y_LEFT_PACMAN = new Image("View/Y_Pac_left.png");
		Y_RIGHT_PACMAN = new Image("View/Y_Pac_Right.png");
		
		Y_UP_PACMAN = new Image("View/Y_Pac_Up.png");
		Y_DOWN_PACMAN = new Image("View/Y_Pac_Down.png");
		
		//initializes blue pacman
		B_LEFT_PACMAN = new Image("View/B_Pac_left.png");
		B_RIGHT_PACMAN = new Image("View/B_Pac_Right.png");
		B_UP_PACMAN = new Image("View/B_Pac_Up.png");
		B_DOWN_PACMAN = new Image("View/B_Pac_Down.png");
		
		Tunnel =  new Image("View/tunnel.png");
		Audio_img = new Image("View/unmute.png");
		Audio_img_mute = new Image("View/mute.png");

		
		//loading url to media to get the sound files
		PacDie =new Media(F_Die.toString()); 
		PacEat =new Media(F_eat.toString());
		PacEatGhost =new Media(F_GhostDie.toString());
		Pacintro =new Media(F_Start.toString());
		PacWin =new Media(F_Finish.toString());
		PacSiren =new Media(F_Siren.toString());
		CorrectAnswer = new Media(F_Corr_Ans.toString());
		WrongAnswer = new Media(F_Wron_Ans.toString());
		Question= new Media(F_Question.toString());
		Question_Wait= new Media(F_Question_Wait.toString());

		//loading FXML for scenes
		
		gmloader = new FXMLLoader(Config.class.getResource("/View/Maze.fxml"));
		nicloader = new FXMLLoader(Config.class.getResource("/View/Nickname.fxml"));
		EditQuestLoader = new FXMLLoader(Config.class.getResource("/View/EditQuestion.fxml"));

		root = FXMLLoader.load(Config.class.getResource("/View/mainPage.fxml"));
		
		nickroot = nicloader.load() ;
		gmroot = gmloader.load();
		Edit_quest=EditQuestLoader.load();
		QandAroot =  FXMLLoader.load(Config.class.getResource("/View/Questions.fxml"));
		Add_quest =  FXMLLoader.load(Config.class.getResource("/View/AddQuestion.fxml"));
		Historyroot =  FXMLLoader.load(Config.class.getResource("/View/History.fxml"));
		Rulesroot =  FXMLLoader.load(Config.class.getResource("/View/Rules.fxml"));

	}
	
}
