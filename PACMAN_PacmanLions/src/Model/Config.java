/**
 * 
 */
package Model;

import java.net.URL;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/*
 * 
 * This is the Configration File for the game it handles all the initialiation and global variables that needs to be shared across the game
 * 
 * for example fxml files assets music files etc
 * this file is used for seamless scene transistion
 * */
public class Config {

	public static int SizeX = 1200; //Width OF game window
	public static int SizeY = 920;	//Height Of game window
	public static Game GManager;		//global object of Game class
	public static Stage MainStage;		//global object of Main Stage which will be shared across the entire game

	
	public static BorderPane root,nickroot,QandAroot,Historyroot,Rulesroot,gmroot; //Fxml files will be loaded into these objects 
	/*
		root = Start page  when game starts
		nickroot = Nickname page after user clicks on Start game
		QandAroot = Question and answer page when user clicks on Questions
		Historyroot = History root which opens when user clicks on history
		Rulesroot = Rule root which opens when user clicks on rules
		gmroot= the root which loads the Maze.fxml file and displays the game
	*/
  public static Scene HomeScene,NickScene,GameScene,QandAScene,HistoryScene,RulesScene; //creating global scenes
  
  public static FXMLLoader gmloader,nicloader; //specific loaders for Gmloader and nicloader to handle stage restart(when user clicks on restart the entire stage is reintialized)
  
  
  public static MediaPlayer mplayer;//Initializing Music Player
  //music
  public  static URL F_Die =Config.class.getResource("/View/pacman_death.wav");//pacman death sound
  public static URL  F_eat =Config.class.getResource( "/View/pacman_eat.wav"); //pacman eat sound
  public static URL  F_Start =Config.class.getResource("/View/pacman_intro.wav"); //pacman intro sound
  public static URL  F_GhostDie=Config.class.getResource("/View/pacman_eatghost.wav");//pacman killing ghost sound
  public static URL  F_Finish = Config.class.getResource("/View/pacman_intermission.wav"); //pacman winning sound
  public static URL  F_Siren = Config.class.getResource("/View/Pac_siren.wav");//pacman siren sound
   
  public static Media PacDie; //this  loads the file 
  public static Media PacEat;
  public static Media PacEatGhost;
  public static Media Pacintro;
  public static Media PacWin;
  public static Media PacSiren;
	
  public static Image Y_LEFT_PACMAN ;
  public static Image Y_RIGHT_PACMAN ;
  public static Image Y_UP_PACMAN ;
  public static Image Y_DOWN_PACMAN ;
  
  public static Image B_LEFT_PACMAN ;
  public static Image B_RIGHT_PACMAN ;
  public static Image B_UP_PACMAN ;
  public static Image B_DOWN_PACMAN ;
  public static Image Tunnel;
  public static Image Ghost1;
  public static Image Ghost2;
  public static Image Ghost3;
  public static Image Energizer;
  public static Image Pellet_img;
  public static Image Question_img;
  public static Image Wall_img;

	public static void Init() throws Exception //this constructor is used to initialize game objects and files
	{
		System.out.println("Initilizing");
		//walls
		Wall_img  = new Image("View/wall.png");
		//Question 
		Question_img = new Image("View/questionmark.png");
		//Power Pellet
		Pellet_img  = new Image("View/powepellet.png");
		//energizer
		Energizer = new Image("View/energizer.png");
		//Ghost initilization
		Ghost1 = new Image("View/ghost1.png");
		Ghost2 = new Image("View/ghost2.png");
		Ghost3 = new Image("View/ghost3.png");
		
		//initializes Yellow Pacman images
		Y_LEFT_PACMAN = new Image("View/Y_pac_left.png");
		Y_RIGHT_PACMAN = new Image("View/Y_pac_right.png");
		
		Y_UP_PACMAN = new Image("View/Y_pac_top.png");
		Y_DOWN_PACMAN = new Image("View/Y_pac_bottom.png");
		//initializes blue pacman
		B_LEFT_PACMAN = new Image("View/B_pac_left.png");
		B_RIGHT_PACMAN = new Image("View/B_pac_right.png");
		B_UP_PACMAN = new Image("View/B_pac_top.png");
		B_DOWN_PACMAN = new Image("View/B_pac_bottom.png");
		
		Tunnel =  new Image("View/tunnel.png");
		
		//loading url to media to get the sound files
		PacDie =new Media(F_Die.toString()); 
		PacEat =new Media(F_eat.toString());
		PacEatGhost =new Media(F_GhostDie.toString());
		Pacintro =new Media(F_Start.toString());
		PacWin =new Media(F_Finish.toString());
		PacSiren =new Media(F_Siren.toString());

		
		//loading FXML for scenes
		
		gmloader = new FXMLLoader(Config.class.getResource("/View/Maze.fxml"));
		nicloader = new FXMLLoader(Config.class.getResource("/View/NickName.fxml"));
		
		root = FXMLLoader.load(Config.class.getResource("/View/mainPage.fxml"));
		
		nickroot = nicloader.load() ;
		
		gmroot = gmloader.load();
	
		QandAroot =  FXMLLoader.load(Config.class.getResource("/View/Questions.fxml"));
		Historyroot =  FXMLLoader.load(Config.class.getResource("/View/History.fxml"));
		Rulesroot =  FXMLLoader.load(Config.class.getResource("/View/Rules.fxml"));

	}
}
