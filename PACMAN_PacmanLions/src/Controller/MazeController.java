package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.application.Platform;
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
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MazeController implements Initializable{

	
    @FXML
    private Label score;

    @FXML
    private  Label level;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView sound;
    
    @FXML
    private Canvas boardPane;  
    
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
	@FXML
	private Label nickname;
	@FXML
	private Label lives;
	@FXML 
	private TilePane Tile;
	@FXML
	private Label ReadyBox;
	@FXML
	private Button restart;
	@FXML Button MainMenu;
	
	@FXML
	private TilePane Win;
	 @FXML
    String Pac_dir="Left";
    int counter=0;
    Maze  theMaze;
    Pacman pcm;
    Tunnel Tun_1,Tun_2;
    Ghost Ghost1,Ghost2,Ghost3;
    Energizer Ener;
    PowerPellet pellet;
    Question Question;
    Walls Wall ;
    
    String dir;
    GraphicsContext gc ;
    ArrayList<Walls> WallsList = new ArrayList<Walls>();
    ArrayList<PowerPellet> PowerPelletList = new ArrayList<PowerPellet>();
    ArrayList<Question> QuestionMarkList = new ArrayList<Question>();
    ArrayList<Energizer> EnergizerList = new ArrayList<Energizer>();
    ArrayList<Ghost> GhostList = new ArrayList<Ghost>();
    ArrayList<Tunnel> TunnelList = new ArrayList<Tunnel>();

    Game Gmm;
    boolean looppause = false;
    boolean pausebtn = false;
	int Gdir;
    int scale=30;
    
    int PointPerEnergizer =1;
    AnimationTimer gameloop ;
    int localscore=0;
    int locallvl=1;

   public int [][]maze = Maze.getMaze();
    int bombs=0;
    boolean DeployBomb = false;
    boolean levelcomplete = false;

	public boolean pacBuff = false;
	public boolean intunel = false;
	public boolean soundtoggle = false;
	public Canvas createBoardView(int[][] m) throws Exception {
		localscore = 0;

		maze = m;
		gc= boardPane.getGraphicsContext2D(); //get graphic context to draw on canvas
		gc.setFill(Color.BLACK);//set it to black
		gc.fillRect(0,0,Config.SizeX, Config.SizeY);//add a rectangle to canvas
		Tile.setVisible(false);//sets gameover screen to false

		
		int i,j;
//		printMaze(theMaze);

			
	
	
		
		for(i=0 ; i<maze.length ; i++) {
			for(j=0 ; j<maze.length ; j++) {
		
				switch(maze[i][j]) {
				  case 1:{
						//Rectangle rec =new Rectangle(j*30,i*30,28,28);
						//rec.setFill(Color.BLUE);
					    //boardPane.getChildren().add(rec);
						//gc.drawImage(rec, i, j);

						Wall = new Walls(Config.Wall_img, j*scale, i*scale, boardPane);	//create walls from gameobject class
						WallsList.add(Wall);//add walls to wall list
//					    System.out.println("built wall");  
				    break;
				    }
				  case 3:
				     
						 pellet = new PowerPellet(Config.Pellet_img, j*scale, i*scale, boardPane);	//create powerpellet from gameobject class
						PowerPelletList.add(pellet);//add powerpellet to list
					  
//					    System.out.println("built PowePellet"); 
					break;
				  case 4:
					 

						Question = new Question(Config.Question_img, j*scale, i*scale, boardPane);	//create question mark from gameobject class
						QuestionMarkList.add(Question);//add question mark to questionmark list
//					    System.out.println("built questionmark"); 
					break;
				  case 10:
				        //here we have to do new ghost 1
				
					    Ghost1 = new Ghost(Config.Ghost1,j*scale,i*scale,boardPane,maze);//create ghost  from gameobject class
					    GhostList.add(Ghost1); //add it too ghost list
//					    System.out.println("built ghost1"); 
					break;
				  case 11:
					
					    Ghost2 = new Ghost(Config.Ghost2,j*scale,i*scale,boardPane,maze);//create ghost  from gameobject class
					    GhostList.add(Ghost2); //add it too ghost list
					    //boardPane.getChildren().add(img2);
//					    System.out.println("built ghost2"); 
					break;
				  case 12:
				
					   Ghost3 = new Ghost(Config.Ghost3,j*scale,i*scale,boardPane,maze);//create ghost  from gameobject class
					   GhostList.add(Ghost3); //add it too ghost list

//					    System.out.println("built ghost3"); 
					break;
				  case 5:
					  
					  //create pacman object from gameobject class
					  	pcm =new Pacman(Config.Y_LEFT_PACMAN, j*scale,  i*scale, boardPane);
//					    System.out.println("built pacman"); 
					break;
				  case 8:
				  {
					  
					  Tun_1 = new Tunnel(Config.Tunnel, j*scale, i*scale,boardPane);//create tunnel object from gameobject class
					  TunnelList.add(Tun_1);//add that too tunnel list 
//					    System.out.println("built pacman"); 
					  break;  
				  }
				  case 9:
				  {
					
					  
					  Tun_2 = new Tunnel(Config.Tunnel, j*scale, i*scale,boardPane);//create tunnel object from gameobject class
					  TunnelList.add(Tun_2);//add that too tunnel list 

//					    System.out.println("built pacman"); 
					  break;
				  }
				  case 0:
				      //  Energizer energy = new Energizer(i,j);
				       // Circle miniCircle =new Circle(j*30+15,i*30+15,4);
				   //     miniCircle.setFill(Color.YELLOW);
					 //   boardPane.getChildren().add(miniCircle);
				        
				    	
					   Ener = new Energizer(Config.Energizer, j*scale, i*scale, boardPane); //create the energizer object from gameobject class	
						EnergizerList.add(Ener);//add the obejct to energizer list
//					    System.out.println("built Energizer");  
				    break;
				  default:
				    // keep empty 
				}
		
			}
		}
		ReadyBoxx("Start",true);//shows "start" at the start of level
		return boardPane;
	}
	@FXML
	public void restart() throws Exception //handles restart button press from game over screen
	{
		soundHandler(null,-2);
		System.out.println("Restarting");
		Config.MainStage.close();
		Platform.runLater(()->{
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
		Config.MainStage.close();
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
		Config.GManager.setScore(PointPerEnergizer);
		score.setText("Score: "+Config.GManager.getScore());
	} 
	public void GameInstance(Game G) //gets instance of gamemanager from nickname class
	{
		Gmm = G;
		lives(G.getLive());
		Level(G.getLevel()); 
		nickname(G.getNickname());
		
		//soundHandler(Config.Pacintro,false);
		
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
		Config.MainStage.close();//closes the main stage
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
			ArrayList<Game> gameAdd=SysData.getInstance().getGames();
			gameAdd.add(Config.GManager);
			SysData.getInstance().setGames(gameAdd);
			SysData.getInstance().observableMethodForGame();

			return;
		}
		if(lvl == 4) //if level 4 increase ghost speed by 1 
		{
		   Ghost1.speed = Ghost1.speed +1;
		}
		if(lvl == 3) //if level 3 then increase pacman speed by 2  
		{
			pcm.speed = pcm.speed+2;	
		}
		//preparing canvas for next level
		
		Config.GManager.setLevel(lvl);
		localscore = 0;
		Config.GManager.gamestarted = false;
		soundHandler(Config.Pacintro,1);
		//Clears Previous Maze From canvas
		GhostList.clear();
		EnergizerList.clear();
		PowerPelletList.clear();
		WallsList.clear();
		QuestionMarkList.clear();
		TunnelList.clear();
		
		
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
		Config.GManager.setScore(0);//sets score in game class
	}
	public synchronized void soundHandler(Media play_M,int loop) //plays background sound
	{
		if(soundtoggle == true) 
		{
			Config.mplayer.stop();
			//Config.mplayer.dispose();
			return;
		}
		if(loop == -2) //if loop is set to -2 then music player will stop playing and returns
		{
			Config.mplayer.stop();
			return;
		}
		if(loop == 3) //if loop is set to -1 then it stops the music and continues
		{
		
			Config.mplayer = new MediaPlayer(play_M);
			Config.mplayer.play();
			return;
			
		}
		
		
		Config.mplayer.stop();
		Config.mplayer = new MediaPlayer(play_M);
		Config.mplayer.play();
		
		
		if(loop == 0) 
		{
			//Config.mplayer.setOnEndOfMedia(() -> Config.mplayer.seek(Duration.ONE));
			Config.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
			
		}
	
	
		
	}
	public void delay(int del) //adds delay at gameloop for a specific amount of time
	{
		try {
			synchronized(this.gameloop)
			{
				
				gameloop.wait(del);
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void gameloop()
	{
		Gmm.gamestarted = true;//sets game started to true
		
		
		
		 gameloop = new AnimationTimer()  //its the main game loop 
		{ 
			
			@Override
			public void handle(long nanosec) 
			{
				// TODO Auto-generated method stub
				//pcm.moveUP();
				//clear canvas
				//checks if score is greater or equal too (if level 1 then 50) (if level 2 then 100) (if level 3 then 150) (if level 4 then 200)
				if(localscore ==  50) //handles level switching if score cretieria is met
				{	score() ;
					levelcomplete = true;
					System.out.println("Level Complete Moving To Next Level");
					gameloop.stop();
					++locallvl;
					SwitchLevel(locallvl);
					Level(Gmm.getLevel());
					
				}
				gc.setFill(Color.BLACK); //fills the canvas with black color
				gc.fillRect(0,0,Config.SizeX, Config.SizeY); //sets  a background rectangle, it also clears the screen after each frame
				
				//continues movement
				
				if(Pac_dir == "Up") //when up arrow key is pressed
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
					for(GameObject Ghost : GhostList) //for every ghost in ghost list  
					{
						System.out.println("Killed a Ghost"); //if they are in range of bomb, they die.
						soundHandler(Config.PacEatGhost,3);
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
					
					GameObject Energizer = EnergizerList.get(s);
					if(pcm.collide(Energizer)) //if pacman collides with energizer
					{
						//EnergizerList.remove(Energizer);
						
						Energizer.Hide(Energizer,30000);//hides energizer for 30 seconds
							score();//updates the score label on scene

							++localscore;//increases score for each energizer collected
							//soundHandler(Config.PacEat,3);

						
					
						
						//pacman collected a energizer
					}
				}
				for(int s = 0; s < QuestionMarkList.size();s++) //renders questoinmarks in questionmarklist
				{
					GameObject Question = QuestionMarkList.get(s);//gets each question mark from questionmark list
					if(pcm.collide(Question)) //if pacman collides with a quesitonmark
					{
						QuestionMarkList.remove(Question); //removes the questionmark
						//pacman collected a questionmark
					}
				}
				renderObjects() ; //goes to renderobjcet method to render objects on canvas
			
			   	Ghost1.Gmove(maze);//auto moves ghost 1
			   	
				Ghost2.Gmove(maze);//auto moves ghost 2
				
				Ghost3.Gmove(maze);//auto moevs ghost 3
				
					
				
				
				if(Gmm.gameover==true) 
				{
					/*    ***************            ***************             *************************8  **************************8*/
					/* adding game object to history JSON  */
					ArrayList<Game> gameAdd=SysData.getInstance().getGames();
					gameAdd.add(Config.GManager);
					
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

	
	void renderObjects() //handles rendering of objects in canvas
	{	
		
		for(Tunnel tunnel : TunnelList) //it will render each tunnel stored in tunnellist
		{
			tunnel.render(); //gameobject.render()
			if(pcm.collide(Tun_2) && intunel == false) //checks if pacman collided or entered the tunnel
			{
				System.out.println("Entering Tunnel 1");
				intunel = true;//avoids getting stuck in tunnel
				//pcm.Teleport(Tun_1); //teleports pacman to second tunnel
				Tun_1.Teleport(pcm);
				
			}
			
			if(pcm.collide(Tun_1) && intunel == false) 
			{
				System.out.println("Entering Tunnel 2");
				intunel = true;//avoids getting stuck in tunnel
				//pcm.Teleport(Tun_2); //teleports pacman to first tunnel
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
			Ghost1.Follow(pcm, maze);
			Ghost2.Follow(pcm, maze);
			Ghost3.Follow(pcm, maze);

			if(ghost.collide(pcm)) 
			{
				
				
				bombs=0;
				pcm.ObjPow=false;
				System.out.println("Pacman Died");
				
				soundHandler(Config.PacDie,1);
				
				if(Gmm.getLive() <= 1) 
				{
					Gmm.gameover = true;
					return;
				}
				
				
				
				Gmm.setLive(Gmm.getLive()-1);
				lives(Gmm.getLive());
				try {
					synchronized(this.gameloop)
					{
					
						gameloop.wait(1000);
					}
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resetBoard();
				ghost.Reset();
				gameloop.stop();
				
				
				looppause = true;
			
			}
			ghost.render();

		}
		pcm.render();
		
		for( GameObject Wall : WallsList) 
		{
			
			
			Wall.render();

		}
	}
	public void resetBoard() //resets pacman and parts of board when pacman dies
	{
		pcm.Reset();
		
		ReadyBox.setText("Start!");
		ReadyBox.setVisible(true);
		CorrectDir(0);
	}
	@FXML
	public void sound(MouseEvent  event) //handles sound button at top right corner
	{
		System.out.println("Sound Button Pressed");
		soundtoggle = !soundtoggle; //toggles the sound variable
		Config.mplayer.setMute(soundtoggle); //sets the media player to muted
	}
	
	
	@FXML
	public void fieldClick(MouseEvent  event) //when a mouse click is pressed on screen
	{
		 
	
			soundHandler(Config.PacSiren,0);
	
		
		
		if((Gmm.gamestarted != true) && (Gmm.gameover == false)) 
		{
			//play waw waw pacman sound
			ReadyBoxx("Ready",false);//sets a label saying "ready"
			
			
			gameloop();//starts the gameloop
			//delay
			delay(1000);//wait for few seconds before starting a new level
			
			System.out.println("Game Loop Started");
			
			
			Scene scn =  ((Node) event.getSource()).getScene();//Gets Current Scene and store it in scn 
			
			scn.setOnKeyPressed(new EventHandler<KeyEvent>() //attaches Keyevent Handler to the Scene scn
			{
				
				@Override
				public void handle(KeyEvent eve) {
					int x = pcm.getCurrCordinateX(); //Gets Current X cordinate of pcm(Pacman) object from GameObject class
					int y = pcm.getCurrCordinateY(); //Gets Current Y cordinate of pcm(Pacman) object From GameObject class
				
					// TODO Auto-generated method stub
					System.out.println(eve.getCode());//prints the key pressed
					
					switch(eve.getCode()) //excutes specific instruction on specific key presses
					{
						case UP:
						{
						
								Pac_dir = "Up"; //sets the pacman move dir to UP

							
							
													/**
							 * 
							 * pcm.ObjPow == true?"B_pac_top.png":"Y_pac_top.png" is a ternary  operator or basically a if else statement which translates too
							 * 
							 * if(pcm.ObjPow == true)
							 * {
							 * 		pcm.img = new Image("B_pac_top.png");
							 * }else
							 * {
							 *   pcm.img = new Image("Y_pac_top.png");
							 * }
							 * this keeps pacman color intact after eating a powerpellet
							 * */
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
				pcm.img = new Image(Config.Y_UP_PACMAN.getUrl());//resets blue pacman to yellow pacman
			}
			if(Pac_dir == "Down" && pcm.ObjPow == false) 
			{
				pcm.img = new Image(Config.Y_DOWN_PACMAN.getUrl());
			}
			if(Pac_dir == "Left" && pcm.ObjPow == false) 
			{
				pcm.img = new Image(Config.Y_LEFT_PACMAN.getUrl());
			}	
			if(Pac_dir == "Right" && pcm.ObjPow == false) 
			{
				pcm.img = new Image(Config.Y_RIGHT_PACMAN.getUrl());
			}	
		}
		if(i==1) 
		{
			if(Pac_dir == "Up" ) 
			{					
				pcm.img = new Image(Config.B_UP_PACMAN.getUrl());
			}
			if(Pac_dir == "Down" ) 
			{
				pcm.img = new Image(Config.B_DOWN_PACMAN.getUrl());
			}
			if(Pac_dir == "Left") 
			{
				pcm.img = new Image(Config.B_LEFT_PACMAN.getUrl());
			}	
			if(Pac_dir == "Right" ) 
			{
				pcm.img = new Image(Config.B_RIGHT_PACMAN.getUrl());
			}
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		try {
			Tile.setVisible(false);
			createBoardView(Maze.getMaze()); //Prepares The first Level
		} catch (Exception e) {
			e.printStackTrace(); //prints the stack trace if there was a error loading maze
		}
		
	}
	


	
    
}
