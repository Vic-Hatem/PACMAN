package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import Model.Config;
import Model.Energizer;
import Model.Game;
import Model.GameObject;
import Model.Ghost;
import Model.Maze;
import Model.Pacman;
import Model.PowerPellet;
import Model.Question;
import Model.Tunnel;
import Model.Walls;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Utils.Difficulty;
public class MazeController implements Initializable{
	/**
	 * @author Nahawand and Grace
	 *
	 */
	
	
    @FXML
    private Label score; //Score Indicator at top of screen

    @FXML
    private  Label level; //Level Indicator at top of screen
    @FXML
	private BorderPane MainContainer; //Main parent of all the other sub components of theLayout 
    @FXML
    private ImageView pause; //Pause button which pauses game on press

    @FXML
    private ImageView sound; //Sound toggle which toggles sound to mute and unmute
   
    
	
    @FXML
    private ImageView back; //back button which 
	@FXML
	private Label nickname;//NickName Indiactor
	@FXML
	private Label lives; //Lives indicator
	
	@FXML 
	private TilePane Tile;// Holds the GameOver Screen To display game over screen
	@FXML
	private Label ReadyBox; //displays "Start" OR "Ready" At the starting of each level
	@FXML
	private Button restart;//Restarts Button at gameover and main menu screen
	@FXML  
	private	Button MainMenu; //Main menu button for gameover or win Screen 
	@FXML  
	private	Button ch1; //Questionmark Answers Chioce 1
	@FXML  
	private	Button ch2;//Questionmark Answers Chioce 2
	@FXML  
	private	Button ch3;//Questionmark Answers Chioce 3
	@FXML  
	private	Button ch4;//Questionmark Answers Chioce 4
	@FXML
	private TilePane Win;//Holds the win screen for game
	 @FXML
	 private TilePane PopUp;//holds the question answer screen
	 @FXML
	 private Label Quest;//holds the question for question answer screen
 
    @FXML
    private Canvas boardPane;  //Main canvas where game is rendered
    
	 
    String Pac_dir="Left";//starting direction where pacman will go
    
    Maze  theMaze; //Creating the maze object from the maze class
    Pacman pcm; //creates a object pcm from pacman class
    Tunnel Tun_1,Tun_2; //creates Tun1 and Tun2 Object From tunnel class
    Ghost Ghost1,Ghost2,Ghost3; //Creates 3 ghost object
    Energizer Ener;//Energizer OBject
    PowerPellet pellet;//powerpellet object
    Question Question_easy,Question_mid,Question_hard;//Question objects 
    Walls Wall ;//wall Object
    
    
    GraphicsContext gc ;//GRaphics Context for Rendering in canvas this is used to draw on canvas
    //used to store or make a organized list of Objects 
    ArrayList<Walls> WallsList = new ArrayList<Walls>();//Collectoin Of walls
    ArrayList<PowerPellet> PowerPelletList = new ArrayList<PowerPellet>();//collectoin of powerpellets
    ArrayList<Question> QuestionMarkList = new ArrayList<Question>();//collection of questions
    ArrayList<Energizer> EnergizerList = new ArrayList<Energizer>();//collectoin of energizer
    ArrayList<Ghost> GhostList = new ArrayList<Ghost>();//collection of ghosts
    ArrayList<Tunnel> TunnelList = new ArrayList<Tunnel>();//collectoin of tunnels
    
    Game Gmm;//Game Object for storing and reading data from game class
    String CorrAnsewer;//Temprarly stores which is the correct answer from QnA Screen
    boolean looppause = false;//Takes care of pausing and unpausing the media
    boolean pausebtn = false;//Pause button toggle it changes its state each time pause button is pressed
	
    
    
    int PointPerEnergizer =1;//decide how much points you want to assign to each energizer
    
    AnimationTimer gameloop ;//main game loop which handles refreshing of the game
    
    int localscore=0;//this stores how much score did player got in each level that is 50 each level
    
    int locallvl=1;//stores current level value and stores it localy in class

   public int [][]maze = Maze.getMaze();//gets the first maze and stores it in maze array list
   
    int bombs=0;//numbers of bombs pacman has by default
    boolean DeployBomb = false;//states for if the pacman is armed with a bomb or not
    boolean levelcomplete = false;//state for if the current level is completed or not 

	public boolean intunel = false;//checks if pacman is in tunnel or not, we use it to avoid being stuck inside tunnel
	public boolean soundtoggle = false;//changes state each time pause button is pressed
	
	public Difficulty Diff_ques;//difficulty enum
	
	//pauses state change so that it can be vissible for few seconds for the Question pop up
	PauseTransition hidePopUp = new PauseTransition(Duration.seconds(1));
	//pauses state change so that it can be vissible for few seconds for the Button Color change
	PauseTransition Btn_Q = new PauseTransition(Duration.seconds(1));
	
	
	@FXML
	int [][] tempmap; //temporary map for pacman in console
	boolean maptoggle = true;//controls if pacman in console should be displayed or not

	/*@authors Moran, Nahawand and Grace*/
	public Canvas createBoardView(int[][] m)  {
		
		
		localscore = 0;//sets local score to 0 at the start of each level
		maze = m;//stores the recived maze at m
		gc= boardPane.getGraphicsContext2D(); //get graphic context to draw on canvas
		gc.setFill(Color.BLACK);//set it to black
		gc.fillRect(0,0,Config.SizeX, Config.SizeY);//add a rectangle to canvas
		Tile.setVisible(false);//sets gameover screen to false
		PopUp.setVisible(false);
		int i,j;
		tempmap = maze;
//		printMaze(theMaze);

		for(i=0 ; i<maze.length ; i++) {
			for(j=0 ; j<maze.length ; j++) {
				switch(maze[i][j]) {
				  case 1:{
						Wall = new Walls(Config.Wall_img, j*Config.Scale, i*Config.Scale, boardPane);	//create walls from gameobject class
						WallsList.add(Wall);//add walls to wall list
				    break;
				    }
				  case 3:
				     
						 pellet = new PowerPellet(Config.Pellet_img, j*Config.Scale, i*Config.Scale, boardPane);	//create powerpellet from gameobject class
						PowerPelletList.add(pellet);//add powerpellet to list
					  
					break;
				  case 4:
					  Question_easy = new Question(Config.Question_img_easy, j*Config.Scale, i*Config.Scale, boardPane,Difficulty.EASY);	//create question mark from gameobject class
						QuestionMarkList.add(Question_easy);//add question mark to questionmark list
					break;
				  case 6:
					  Question_mid = new Question(Config.Question_img_medium, j*Config.Scale, i*Config.Scale, boardPane,Difficulty.MEDIUM);	//create question mark from gameobject class
						QuestionMarkList.add(Question_mid);//add question mark to questionmark list
					  break;
				  case 16:
					  Question_hard = new Question(Config.Question_img_hard, j*Config.Scale, i*Config.Scale, boardPane,Difficulty.HARD);	//create question mark from gameobject class
						QuestionMarkList.add(Question_hard);//add question mark to questionmark list
					  break;
				  case 10:
				        //here we have to do new ghost 1
					    Ghost1 = new Ghost(Config.Ghost1,j*Config.Scale,i*Config.Scale,boardPane,maze);//create ghost  from gameobject class
					    GhostList.add(Ghost1); //add it too ghost list
					break;
				  case 11:
					
					    Ghost2 = new Ghost(Config.Ghost2,j*Config.Scale,i*Config.Scale,boardPane,maze);//create ghost  from gameobject class
					    GhostList.add(Ghost2); //add it too ghost list
					break;
				  case 12:
				
					   Ghost3 = new Ghost(Config.Ghost3,j*Config.Scale,i*Config.Scale,boardPane,maze);//create ghost  from gameobject class
					   GhostList.add(Ghost3); //add it too ghost list

					break;
				  case 5:
					  
					  //create pacman object from gameobject class
					  	pcm =new Pacman(Config.Y_LEFT_PACMAN, j*Config.Scale,  i*Config.Scale, boardPane);
					break;
				  case 8:
				  {
					  
					  Tun_1 = new Tunnel(Config.Tunnel, j*Config.Scale, i*Config.Scale,boardPane);//create tunnel object from gameobject class
					  TunnelList.add(Tun_1);//add that too tunnel list 
					  break;  
				  }
				  case 9:
				  {
					
					  
					  Tun_2 = new Tunnel(Config.Tunnel, j*Config.Scale, i*Config.Scale,boardPane);//create tunnel object from gameobject class
					  TunnelList.add(Tun_2);//add that too tunnel list 

					  break;
				  }
				  case 0:
					   Ener = new Energizer(Config.Energizer, j*Config.Scale, i*Config.Scale, boardPane); //create the energizer object from gameobject class
						EnergizerList.add(Ener);//add the obejct to energizer list
				    break;
				  default:
				    // keep empty 
				}
		
			}
		}
		ReadyBoxx("Start",true);//shows "start" at the start of level
		return  boardPane;
	}
	
	
	public void DisplayMap(int x,int y) 
	{
		//mini map of maze at console
		x=x/Config.Scale; //scales down the current posx
		y=y/Config.Scale;//scales down the current posy
		for(int i=0;i<maze.length;i++) //ittrate through y
		{
			for(int j=0;j<maze.length;j++) //ittrate through x
			{
				
				if(x==j && y ==i) 
				{
					System.out.print("O"); //represents pacman
				}
				switch(maze[i][j]) 
				{
					case 1:{
						System.out.print("1");
						break;
					}
					case 3:
						System.out.print("3");
						break;
					case 2:
						System.out.print("?");
						break;
					case 4:
						System.out.print("?");
						break;
					case 5:
						System.out.print("5");
						break;
					case 6:
						System.out.print("?");
						break;
					case 16:
						System.out.print("?");
						break;
					case 7:
						System.out.print(" ");
						break;
					case 8:
						System.out.print("?");
						break;
					case 9:
						System.out.print("?");
						break;
					case 10:
						System.out.print("G");
						break;
					case 11:
						System.out.print("G");
						break;
					case 12:
						System.out.print("G");
						break;
					case 0:{
						System.out.print(" ");
						break;
					}
					
					default:
					{

						break;
					}
					
				}
				
			}
			System.out.print("\n");
		}
	}
	
	
	@FXML
	public void restart() throws Exception //handles restart button press from game over screen
	{
		soundHandler(null,-2);
		
		System.out.println("Restarting");
		
		ArrayList<Game> gameAdd=SysData.getInstance().getGames(); //storing game history before we restart
		gameAdd.add(Config.GManager);
		Gmm.setLive(Gmm.getLive()-1);
		lives(Gmm.getLive());
		SysData.getInstance().setGames(gameAdd);
		SysData.getInstance().observableMethodForGame();
		gameloop.stop();
		Config.MainStage.close();
		Platform.runLater(()->{ //closing current instance and starting a new Stage instance so that we reload everything
			try {
				new mainPageController().start(new Stage());
				Config.MainStage.setScene(Config.NickScene); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
	
	@FXML
	public void mainmenu() //handles main menu button press from game over screen
	{

		System.out.println("Pressed Main Menu");
		//stores game history
		ArrayList<Game> gameAdd=SysData.getInstance().getGames();
		gameAdd.add(Config.GManager);
		//Grace added 2 following lines
		Gmm.setLive(Gmm.getLive()-1);
		lives(Gmm.getLive());
		SysData.getInstance().setGames(gameAdd);
		SysData.getInstance().observableMethodForGame();
		gameloop.stop();
		Config.MainStage.close();
		Config.mplayer.stop();
		soundHandler(null,-2);
		Platform.runLater(()->{
			try {
				new mainPageController().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Config.MainStage.setScene(Config.HomeScene);
	}
	
	
	public void score()//updates the score 
	{
		
		score.setText("Score: "+Config.GManager.getScore());
	} 
	public void GameInstance(Game G) //gets instance of gamemanager from nickname class
	{
		Gmm = G;
		lives(G.getLive());
		Level(G.getLevel()); 
		nickname(G.getNickname());		 
	}
	public void lives(int  live) //sets lives
	{
		lives.setText("Lives: "+live);
	}
	public void Level(int i) //sets level
	{
		level.setText("Level: "+i);
	}
	public void nickname(String str)//sets nickname
	{
		nickname.setText("NickName: "+str);
	}
	
	public void pause(MouseEvent event) throws InterruptedException  //handles sound mute button at top right corner
	{
		if(pausebtn == false) 
		{
				  gameloop.stop();
				  soundHandler(null,-2);
				  ReadyBoxx("Paused",true);
			      
		}else if(pausebtn == true) 
		{

			  gameloop.start();
			  soundHandler(Config.PacSiren,0);
			  ReadyBoxx("",false);
		}
		pausebtn = !pausebtn; //toggles the sound flag each time button is pressed
		
	}
	
	public void back(MouseEvent  event) throws Exception {


		System.out.println("Pressed Back");
		
		ArrayList<Game> gameAdd=SysData.getInstance().getGames(); //storing game history before we restart
		gameAdd.add(Config.GManager);
		//Grace added 2 following lines
		Gmm.setLive(Gmm.getLive()-1);
		lives(Gmm.getLive());
		SysData.getInstance().setGames(gameAdd);
		SysData.getInstance().observableMethodForGame();
		
		Config.MainStage.close();//closes the main stage
		Config.mplayer.stop();
		gameloop.stop();
		Platform.runLater(()->{//invokes the stage thread in background
			try {
				new mainPageController().start(new Stage()); //starts a new main stage
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Config.MainStage.setScene(Config.HomeScene);//sets the scene to the new stage
	}
	
	public void ReadyBoxx(String str,boolean state) //handles the tile that shows up before starting level
	{
		
		ReadyBox.setText(str);
		ReadyBox.setVisible(state);
	}
	void SwitchLevel(int lvl) //handles level switching 
	{
		if(lvl>4) 
		{
			soundHandler(Config.PacWin,0); //if more then lvl4 then u finished the game and it will display trophy thing
			Win.setVisible(true);
			
			return;
		}
		if(lvl == 4) //if level 4 increase ghost speed by 1 
		{
		   Ghost1.speed = Ghost1.speed +1;
		}
		if(lvl == 3) //if level 3 then increase pacman speed by 2  
		{
			pcm.speed = pcm.speed+1;	
		}
		//preparing canvas for next level
		Config.GManager.setLevel(lvl); //sets current level at game class
		//reseting everything for next level

		localscore = 0;
		Config.GManager.gamestarted = false;
		soundHandler(Config.Pacintro,1);
		//Clears Previous Maze From canvas
		GhostList.clear();
		EnergizerList.clear();
		PowerPelletList.clear();
		WallsList.clear();
		System.out.println("Preparing For next level");
		QuestionMarkList.clear();
		TunnelList.clear();
		pcm.ObjPow = false; //sets blue pacman to false
		DeployBomb = false;
		bombs=0;
		
		
		try {
			
			if(Config.GManager.getLevel() ==2)
			{
				createBoardView(Maze.getMaze2()); //if lvl at gamemanger is 2 then get the 2nd maze
			}
			if(Config.GManager.getLevel() ==3)
			{
				createBoardView(Maze.getMaze3());//if lvl at gamemanger is 3 then get the 3rd maze
			}
			if(Config.GManager.getLevel() ==4)
			{
				createBoardView(Maze.getMaze4());//if lvl at gamemanger is 4 then get the 4th maze
			}
			if(Config.GManager.getLevel() > 4)
			{
				System.out.println("You Won a Trophy");//if lvl at gamemanger is more then 2 then u finished the game
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void soundHandler(Media play_M,int loop) //plays background sound
	{
		System.out.println("========[Changing The Sound]");
		Config.mplayer.stop();
		
			
		
			if(loop == -2) //if loop is set to -2 then music player will stop playing and returns
			{
				
				return;
			}
			if(loop == 3) //if loop is set to 3 then it stops the music and continues
			{
			
				Config.mplayer = new MediaPlayer(play_M);
				Config.mplayer.setAutoPlay(true);
	
				Config.mplayer.play();
				return;
				
			}
			
			
			Config.mplayer = new MediaPlayer(play_M);
			Config.mplayer.play();
			Config.mplayer.setAutoPlay(true);
	
			
			if(loop == 0) 
			{
				Config.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
			}
		
			return;
		
		
	}
	public void delay(int del) //adds delay at gameloop for a specific amount of time
	{
		try {
			synchronized(this.gameloop) //synchonesly add delay to gameloop to avoid errors
			{
				
				gameloop.wait(del);
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void gameloop()
	{
		Gmm.gamestarted = true;//sets game started to true
		
		
		 gameloop = new AnimationTimer()  //its the main game loop 
		{ 
				
			@Override
			public void handle(long nanosec) 
			{
				
				//clear canvas
				//checks if score is greater or equal too (if level 1 then 50) (if level 2 then 100) (if level 3 then 150) (if level 4 then 200)
				if(localscore >=  50) //handles level switching if score cretieria is met
				{	
					levelcomplete = true;
					System.out.println("Level Complete Moving To Next Level");
					gameloop.stop();
					++locallvl;
					SwitchLevel(locallvl);
					
					Level(Gmm.getLevel());
					
				}
				gc.setFill(Color.BLACK); //fills the canvas with black color
				gc.fillRect(0,0,MainContainer.getWidth(), MainContainer.getHeight()); //sets  a background rectangle, it also clears the screen after each frame
				
				//continues movement
				Config.mplayer.setMute(soundtoggle);
				
				
				if(Pac_dir == "Up" ) //when up arrow key is pressed
				{	
						pcm.moveUP(maze);
				}
				if(Pac_dir == "Down") //when down arrow key is pressed
				{	
					
					pcm.moveDown(maze);

				}
				if(Pac_dir == "Left" ) //when left arrow key is pressed
				{
					pcm.moveLeft(maze);
				}	
				
				if(Pac_dir == "Right") //when right arrow key is pressed
				{
					pcm.moveRight(maze);
				}
				
				if( bombs > 0 && (DeployBomb == true))	//if pacman has more then 0 bomb and space bar is pressed
				{
					System.out.println("Bomb Deployed");
					soundHandler(Config.PacEatGhost,3);
					Config.mplayer.setOnEndOfMedia(()->soundHandler(Config.PacSiren,0));
					for(Ghost Ghost : GhostList) //for every ghost in ghost list  
					{
						
						pcm.DeployBomb(Ghost); //deploys bomb against ghost
						DeployBomb = false;
						pcm.ObjPow = false;
						bombs =0;//resets bomb after use

						
					}
					
				}
				for(int s = 0; s < PowerPelletList.size();s++)
				{
					GameObject powpell = PowerPelletList.get(s);//gets powerpellet from powerpellet list
					if(pcm.collide(powpell)) //when pacman collides with powerpellet 
					{
						//PowerPelletList.remove(powpell);
						powpell.Hide(powpell,30000);//hides powerpellet for 30 seconds
						bombs=1;//sets number of bombs pacman can detonate to 1
						pcm.ObjPow=true;//sets flag for blue pacman to true

						//sets yellow pacman image to blue when powerpellet collected
						CorrectDir(1);
						//pacman collected a power pellet
					}
				}
				
				for(int s = 0; s < EnergizerList.size();s++)//traverses through energizer list which has all the energizer
				{
					
					Energizer Ener = EnergizerList.get(s);
					if(pcm.collide(Ener)) //if pacman collides with energizer
					{
						//EnergizerList.remove(Energizer);
						
						Ener.Hide(Ener,30000);//hides energizer for 30 seconds
							score();//updates the score label on scene
							Config.GManager.addScore(PointPerEnergizer);
							++localscore;//increases score for each energizer collected
							System.out.println(localscore);

					}
				}
				for(int s = 0; s < QuestionMarkList.size();s++) //renders questoinmarks in questionmarklist
				{
					Question Questionn = QuestionMarkList.get(s);//gets each question mark from questionmark list
					if(pcm.collide(Questionn)) //if pacman collides with a quesitonmark
					{
						Config.mplayer.stop();
						System.out.println("Collided with QuestionMark");
						
						
						//replacing a energizer with ?
						int min=1,max=EnergizerList.size(),range;
						range=(max-min)+1;
						int x = (int) (Math.random() * range)+min;
						Energizer Ener = EnergizerList.get(x);
						Questionn.pos_currx = Ener.pos_currx;
						Questionn.pos_curry=Ener.pos_curry;
						EnergizerList.remove(x);
						QuestionHandler(Questionn);

					}
				}
				renderObjects() ; //goes to renderobjcet method to render objects on canvas
			
			   	Ghost1.Gmove(maze);//auto moves ghost 1
			   	
				Ghost2.Gmove(maze);//auto moves ghost 2
				
				Ghost3.Gmove(maze);//auto moevs ghost 3
				
					
				
				if(maptoggle == true) 
				{
					DisplayMap(pcm.pos_currx,pcm.pos_curry) ;
				}
				if(Gmm.gameover==true) 
				{
					/*    ***************            ***************             *************************8  **************************8*/
					/* adding game object to history JSON  */
					ArrayList<Game> gameAdd=SysData.getInstance().getGames();
					gameAdd.add(Config.GManager);
					//Grace added 2 following lines
					Gmm.setLive(Gmm.getLive()-1);
					lives(Gmm.getLive());
					SysData.getInstance().setGames(gameAdd);
					SysData.getInstance().observableMethodForGame();
					
					/*    ***************            ***************             *************************8  **************************8*/
					Tile.setVisible(true);//sets gameover tile to true
					
					gameloop.stop();
			
				}
			}
			
		};
		
		gameloop.start(); //starts the gameloop
	}
	
	
	@FXML
	 private void redraw()//will be used for canvas resizing
	{
		    double w=boardPane.getWidth();
		    double h=boardPane.getHeight();
		    GraphicsContext gc=boardPane.getGraphicsContext2D();
		    gc.clearRect(0, 0, w, h);
		    gc.beginPath();
		    gc.rect(10, 10, w-20, h-20);
		    gc.stroke();
		  }
	void QuestionHandler(Question questions) //this mehtod is called when pacman collides with a question
	{	
		Config.GManager.setTotalQuest(Config.GManager.getTotalQuest()+1);//adds 1 to total question in Game class
		soundHandler(Config.Question,3); //plays the question pickup sound
		Config.mplayer.setOnEndOfMedia(()->soundHandler(Config.Question_Wait,0));//after the previous sound finished playing play the background music
		 
		questions = SysData.getInstance().popQuestion(questions.getLevel()); //gets a question instance
		
		CorrAnsewer = questions.getCorrect_ans();//stores correct answer
		Diff_ques = questions.getLevel();//stores question difficulty
		System.out.println("Correct Answer IS: "+CorrAnsewer);
		Quest.setText(questions.getQuestion());
		ch1.setText(questions.getAnswer1());//puts answer 1 from question instance on  button 1
		ch2.setText(questions.getAnswer2());//puts answer 2 from question instance on  button 2
		ch3.setText(questions.getAnswer3());//puts answer 3 from question instance on  button 3
		ch4.setText(questions.getAnswer4());//puts answer 4 from question instance on  button 4
		System.out.println("Question IS: "+questions.getQuestion());
		PopUp.setVisible(true);//sets qna popup to true that is vissible
		
		gameloop.stop();
	}
	public void AnswerChek(MouseEvent  event) //checks if currently chosen answer is current or not
	{
		int choice=0;//initializes choice var
		
		Button btn = (Button) event.getSource();//checks if a button was pressed
		
		//compares and decided which button was pressed
        if(event.getSource().equals(ch1)) 
        {
        	choice=1;
        }
        if(event.getSource().equals(ch2)) 
        {
        	choice=2;  
        }
        if(event.getSource().equals(ch3)) 
        {
        	choice=3;
        	}
        if(event.getSource().equals(ch4)) 
        {
        	choice=4;
        }
        

        if(CorrAnsewer.equals(""+choice)) 
        {	//if the button pressed was correct answer
        	
    		btn.setStyle("-fx-background-color: limegreen; -fx-text-fill:white; "); //change the color to green
    		
    		Btn_Q.playFromStart(); //uses pausetransistion to pause the hold the screen for 1 sec
    		Btn_Q.setOnFinished(e->btn.setStyle("-fx-background-color: grey; -fx-text-fill:black; "));
    		Config.GManager.setTotalCorrectAnswer(Config.GManager.getTotalCorrectAnswer()+1); //increases correct answer by 1 at Game class
        	AnswerHandler(true) ;
        }else 
        {//if the button pressed was wrong answer
        	AnswerHandler(false) ;
    		btn.setStyle("-fx-background-color: crimson; -fx-text-fill:white; "); //change the color to red
    		Btn_Q.playFromStart();//uses pausetransistion to pause the hold the screen for 1 sec
    		Btn_Q.setOnFinished(e->btn.setStyle("-fx-background-color: grey; -fx-text-fill: black; "));

        }

	}
	void AnswerHandler(boolean sta) 
	{	
		//adds or subtract score based on the difficulty of the question
		Config.mplayer.stop();//stops current playing music
		System.out.println("Difficulty IS: "+Diff_ques);
		
		int Addpoint = Diff_ques == Difficulty.EASY? 1:Diff_ques== Difficulty.MEDIUM?2:Diff_ques == Difficulty.HARD?3:10;	//if you are getting +10 score then the level of difficulty is greater then easy medium hard
		int rempoint = Diff_ques == Difficulty.EASY? 10:Diff_ques== Difficulty.MEDIUM?20:Diff_ques == Difficulty.HARD?30:50;	//if you are getting -50 score then the level of difficulty is greater then easy medium hard

		if(sta == true) 
		{
			soundHandler(Config.CorrectAnswer,3);
			
			localscore = localscore +Addpoint;
			Config.GManager.setScore(Config.GManager.getScore() +Addpoint);
			
		}else {
			soundHandler(Config.WrongAnswer,3);

			Config.GManager.setScore(Config.GManager.getScore() -rempoint);
			localscore = localscore -rempoint;
			

		}
		score();
		Config.mplayer.setOnEndOfMedia(()->soundHandler(Config.PacSiren,0));//plays pacman sirent sound
		CorrAnsewer=null;
		
		gameloop.start();
		hidePopUp.playFromStart();
		hidePopUp.setOnFinished(e ->PopUp.setVisible(false));
		hidePopUp.play();
	}
	void renderObjects() //handles rendering of objects in canvas
	{	
		
		for(Tunnel tunnel : TunnelList) //it will render each tunnel stored in tunnellist
		{
			tunnel.render(); //gameobject.render()
			if(pcm.collide(Tun_2) && intunel == false) //checks if pacman collided or entered the tunnel
			{
				System.out.println("Entering Tunnel 1");
				intunel = true;//avoids getting stuck in tunnel
				Tun_1.Teleport(pcm);
				
			}
			
			if(pcm.collide(Tun_1) && intunel == false) 
			{
				System.out.println("Entering Tunnel 2");
				intunel = true;//avoids getting stuck in tunnel
				Tun_2.Teleport(pcm);
				
			}
			if((pcm.collide(Tun_1) == false )&& (pcm.collide(Tun_2)  == false)) //if pacman is not in tunnel set intunnel to false
			{
				intunel = false;
			}
		}
		
		for( GameObject powpell : PowerPelletList) 
		{
			powpell.render();

		}
		for( GameObject Question : QuestionMarkList) 
		{
			Question.render();

		}
		//render pacman
		for( GameObject Energizer : EnergizerList) 
		{
			Energizer.render();

		}
		for( Ghost ghost : GhostList) 
		{
			Ghost3.NeibourTiles(pcm);
			if(ghost.collide(pcm)) 
			{
				
				//if ghosts collide with pacman
				bombs=0;
				pcm.ObjPow=false;
				System.out.println("Pacman Died");
				
				soundHandler(Config.PacDie,1);
				if(Gmm.getLive() <= 1) 
				{//if pacman has no life left show the gameover screen
					Gmm.gameover = true;
					return;
				}
				
				
				//if pacman still has lives
				Gmm.setLive(Gmm.getLive()-1);//subtract one life from pacman 
				lives(Gmm.getLive());//update current life pacman has
				delay(1000);//pause for 1 sec
				resetBoard();//prepare  board for starting agian by reseting pacman posistion and displaying "Start" Message 
				ghost.Reset();//reset the position of ghost that pacman collidded with
				gameloop.stop();//resume gameloop
				
				
				looppause = true;
			
			}
			ghost.render();

		}
		
		
		for( GameObject Wall : WallsList) 
		{
			
			
			Wall.render();

		}
		pcm.render();
	}
	
	
	public void resetBoard() //resets pacman and parts of board when pacman dies
	{
		pcm.Reset();//resets pacmans position
		
		ReadyBox.setText("Start!"); //set the display message to start
		ReadyBox.setVisible(true); //shows the display message
		
		CorrectDir(0);
	}
	
	@FXML
	public void sound(MouseEvent  event) //handles sound button at top right corner
	{
		System.out.println("Sound Button Pressed");
		soundtoggle = !soundtoggle; //toggles the sound variable
		Config.mplayer.setMute(soundtoggle); //sets the media player to muted
		
		if(soundtoggle == true) //changes sound icon at top right corner to mute or unmute
		{
			sound.setImage(Config.Audio_img_mute);
		}else if(soundtoggle == false) 
		{
			sound.setImage(Config.Audio_img);
		}
	}
	
	
	@FXML
	public void fieldClick(MouseEvent  event) //when a mouse click is pressed on screen
	{
		 
		
		maptoggle = !maptoggle;
		
		
		if((Gmm.gamestarted != true) && (Gmm.gameover == false)) 
		{
			score();
			//play waw waw pacman sound
			ReadyBoxx("Ready",false);//sets a label saying "ready"
			soundHandler(Config.PacSiren,0);
			System.out.println("====[GameLoop Started]");
			gameloop();//starts the gameloop
			//delay
			delay(1000);//wait for few seconds before starting a new level
			
			System.out.println("Game Loop Started");
			
			
			Scene scn =  ((Node) event.getSource()).getScene();//Gets Current Scene and store it in scn 
			
			scn.setOnKeyPressed(new EventHandler<KeyEvent>() //attaches Keyevent Handler to the Scene scn
			{
				
				@Override
				public void handle(KeyEvent eve) {
				
					// TODO Auto-generated method stub
					System.out.println(eve.getCode());//prints the key pressed
					
					switch(eve.getCode()) //excutes specific instruction on specific key presses
					{
						case UP:
						{
							
								Pac_dir = "Up"; //sets the pacman move dir to UP
								
							break;
						}
						case DOWN:
						{
								Pac_dir = "Down";//sets the pacman move dir to DOWN
							
							break;
						}
						case LEFT:
						{
							Pac_dir = "Left";//sets the pacman move dir to LEFT
							break;
						}
						case RIGHT:
						{
							Pac_dir = "Right"; //sets the pacman move dir to RIGHT
							break;
						}
						case SPACE://when space is pressed
						{
							
							pcm.ObjPow = false; //sets blue pacman to false
							DeployBomb = true; //sets bomb as deployed
							break;
						}
						default:
						{
							break;
						}
					}
					CorrectDir(pcm.ObjPow == true?1:0);	
				}
			}
			);

		}
		if(looppause == true) //handles unpause when user clicks the 2nd time on button at top right corner
		{
			gameloop.start(); //pauses the game loop
			ReadyBox.setVisible(false); //hides the label
			looppause = false; //sets the pause flag for loop to false
			soundHandler(Config.PacSiren,0);

			
		}
		//System.out.println("coords: "+pcm.getCurrCordinateX()+","+pcm.getCurrCordinateY()); // for debugging purposes
		
	}
	public void CorrectDir(int i) //rotates pacman to face currently moving dir 
	{
		System.out.println("Correcting Dir");
		if(i == 0) 
		{
			if(Pac_dir == "Up" && pcm.ObjPow == false) 
			{					
				pcm.img = Config.Y_UP_PACMAN;//resets blue pacman to yellow pacman
			}
			if(Pac_dir == "Down" && pcm.ObjPow == false) 
			{
				pcm.img = Config.Y_DOWN_PACMAN;
			}
			if(Pac_dir == "Left" && pcm.ObjPow == false) 
			{
				pcm.img = Config.Y_LEFT_PACMAN;
			}	
			if(Pac_dir == "Right" && pcm.ObjPow == false) 
			{
				pcm.img = Config.Y_RIGHT_PACMAN;
			}	
		}
		if(i==1) 
		{
			if(Pac_dir == "Up" ) 
			{					
				pcm.img = Config.B_UP_PACMAN;
			}
			if(Pac_dir == "Down" ) 
			{
				pcm.img = Config.B_DOWN_PACMAN;
			}
			if(Pac_dir == "Left") 
			{
				pcm.img = Config.B_LEFT_PACMAN;
			}	
			if(Pac_dir == "Right" ) 
			{
				pcm.img = Config.B_RIGHT_PACMAN;
			}
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		try {
			Tile.setVisible(false);
			pcm.speed = 2;
			Ghost1.speed = 2;
			Ghost2.speed = 2;
			Ghost3.speed = 2;
			createBoardView(Maze.getMaze()); //Prepares The first Level
		} catch (Exception e) {
			e.printStackTrace(); //prints the stack trace if there was a error loading maze
		}
		
	}
	


	
    
}
