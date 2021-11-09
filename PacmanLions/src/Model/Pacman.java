package Model;

public class Pacman {
	// where the pacman start as a format of x and y in the maze matrix
	private int startpointX;
	private int startpointY;
	// where the pacman is right now as a format of x and y in the maze matrix
	private int currCordinateX;
	private int currCordinateY;
	private int colorFlag = 0; // If blown the flag turns to 1 then we know we should change the color

	
	
	/**
	 * @param startpointX
	 * @param startpointY
	 * @param currCordinateX
	 * @param currCordinateY
	 */
	//constructor
	public Pacman(int startpointX, int startpointY, int currCordinateX, int currCordinateY , int colorFlag) {
		super();
		this.startpointX = startpointX;
		this.startpointY = startpointY;
		this.currCordinateX = currCordinateX;
		this.currCordinateY = currCordinateY;
		this.colorFlag = colorFlag;
	}
	
	// getters and setters
	public int getStartpointX() {
		return startpointX;
	}
	public void setStartpointX(int startpointX) {
		this.startpointX = startpointX;
	}
	public int getStartpointY() {
		return startpointY;
	}
	public void setStartpointY(int startpointY) {
		this.startpointY = startpointY;
	}
	public int getCurrCordinateX() {
		return currCordinateX;
	}
	public void setCurrCordinateX(int currCordinateX) {
		this.currCordinateX = currCordinateX;
	}
	public int getCurrCordinateY() {
		return currCordinateY;
	}
	public void setCurrCordinateY(int currCordinateY) {
		this.currCordinateY = currCordinateY;
	}
	
	// modifing/changing the maze according to the level/score
	public void checkLevelByScore(int score , int currentLevel , Game game,  Maze maze1) {
		int [][]maze = maze1.getMaze();
		if(score > 49 && score < 101) {
			game.setLevel(2);
			maze[24][29] = 2;
			maze[6][0] = 2;
			maze1.setMaze(maze);
		}
		if(score > 100 && score < 151) {
			game.setLevel(3);
			maze[24][29] = 1;
			maze[6][0] = 1;
			maze1.setMaze(maze);
		}
		else game.setLevel(4);	
	}
	
	// method to control the pacmans coordinates if the pacman goes up
	public void goUp(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();
		switch(maze[x-1][y]) {
		  case 0:
			  //pak
				this.currCordinateX = x-1;
				this.currCordinateY = y;
				int tmpscore = 0;
				tmpscore = game.getScore();
				tmpscore = tmpscore+1;
				game.setScore(tmpscore);
				checkLevelByScore(tmpscore , game.getLevel() , game ,maze1);
			    maze[x-1][y]=7;
			    maze1.setMaze(maze);
		    break;
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		  case 3:
			  //PowerPellets
			  this.currCordinateX = x-1;
			  this.currCordinateY = y;
		      this.colorFlag=1;
		      maze[x-1][y]=7;
			    maze1.setMaze(maze);

		    break;
//		  case 3:
//			  //Question
//			  this.currCordinateX = x-1;
//			  this.currCordinateY = y;
//		    break;
		  case 6:
			  //Ghost
			  int tmplive = 0;
			  tmplive = game.getLive();
			  tmplive = tmplive - 1;
			  if(tmplive == 0) {
				  System.out.println("GAME OVER");
			  }
			  game.setLive(tmplive); 
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		    
		    
		  default:
			  this.currCordinateX = x-1;
   			  this.currCordinateY = y;
		}
	}


	// method to control the pacmans coordinates if the pacman goes down
	public void goDown(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();

		switch(maze[x+1][y]) {
		  case 0:
			  //pak
				this.currCordinateX = x+1;
				this.currCordinateY = y;
				int tmpscore = 0;
				tmpscore = game.getScore();
				tmpscore = tmpscore+1;
				game.setScore(tmpscore);
				checkLevelByScore(tmpscore , game.getLevel() , game ,maze1);
			    maze[x+1][y]=7;
			    maze1.setMaze(maze);

		    break;
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		  case 3:
			  //PowerPellets
			  this.currCordinateX = x+1;
			  this.currCordinateY = y;
		      this.colorFlag=1;
		      maze[x+1][y]=7;
			    maze1.setMaze(maze);

		    break;
//		  case 3:
//			  //Question
//			  this.currCordinateX = x-1;
//			  this.currCordinateY = y;
//
//		    break;
		  case 6:
			  //Ghost
			  int tmplive = 0;
			  tmplive = game.getLive();
			  tmplive = tmplive - 1;
			  if(tmplive == 0) {
				  System.out.println("GAME OVER");
			  }
			  game.setLive(tmplive); 
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		    
		    
		  default:
			  this.currCordinateX = x+1;
   			  this.currCordinateY = y;
		}
	}
	
	// method to control the pacmans coordinates if the pacman goes left
	public void goLeft(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();
		switch(maze[x][y-1]) {
		  case 0:
			  //pak
				this.currCordinateX = x;
				this.currCordinateY = y-1;
				int tmpscore = 0;
				tmpscore = game.getScore();
				tmpscore = tmpscore+1;
				game.setScore(tmpscore);
				checkLevelByScore(tmpscore , game.getLevel() , game,maze1);
			    maze[x][y-1]=7;
			    maze1.setMaze(maze);

		    break;
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		  case 2:
			  //tunnle
			  if(this.currCordinateX == 24 && this.getCurrCordinateY() == 29) {
				  this.currCordinateX = 6;
				  this.currCordinateY = 0;
			  }
			  
			  if(this.currCordinateX == 6 && this.getCurrCordinateY() == 0) {
				  this.currCordinateX = 24;
				  this.currCordinateY = 29;
			  }
		    break; 
		  case 3:
			  //PowerPellets
			  this.currCordinateX = x;
			  this.currCordinateY = y-1;
		      this.colorFlag=1;
		      maze[x][y-1]=7;
			    maze1.setMaze(maze);

		    break;
//		  case 3:
//			  //Question
//			  this.currCordinateX = x-1;
//			  this.currCordinateY = y;
//
//		    break;
		  case 6:
			  //Ghost
			  int tmplive = 0;
			  tmplive = game.getLive();
			  tmplive = tmplive - 1;
			  if(tmplive == 0) {
				  System.out.println("GAME OVER");
			  }
			  game.setLive(tmplive); 
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		    
		    
		  default:
			  this.currCordinateX = x;
   			  this.currCordinateY = y-1;
		}
	}

	// method to control the pacmans coordinates if the pacman goes right
	public void goRight(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();
		switch(maze[x][y+1]) {
		  case 0:
			  //pak
				this.currCordinateX = x;
				this.currCordinateY = y+1;
				int tmpscore = 0;
				tmpscore = game.getScore();
				tmpscore = tmpscore+1;
				game.setScore(tmpscore);
				checkLevelByScore(tmpscore , game.getLevel() , game, maze1);
			    maze[x][y+1]=7;
			    maze1.setMaze(maze);

		    break;
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		  case 2:
			  //tunnle
			  if(this.currCordinateX == 24 && this.getCurrCordinateY() == 29) {
				  this.currCordinateX = 6;
				  this.currCordinateY = 0;
			  }
			  
			  if(this.currCordinateX == 6 && this.getCurrCordinateY() == 0) {
				  this.currCordinateX = 24;
				  this.currCordinateY = 29;
			  }
		    break; 
		  case 3:
			  //PowerPellets
			  this.currCordinateX = x;
			  this.currCordinateY = y+1;
		      this.colorFlag=1;
		      maze[x][y+1]=7;
			  maze1.setMaze(maze);

		    break;
//		  case 3:
//			  //Question
//			  this.currCordinateX = x-1;
//			  this.currCordinateY = y;
//
//		    break;
		  case 6:
			  //Ghost
			  int tmplive = 0;
			  tmplive = game.getLive();
			  tmplive = tmplive - 1;
			  if(tmplive == 0) {
				  System.out.println("GAME OVER");
			  }
			  game.setLive(tmplive); 
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
		    
		    
		  default:
			  this.currCordinateX = x;
   			  this.currCordinateY = y+1;
		}
	}
	
}
