package Model;

public class Ghost {
	
	// where the pacman start as a format of x and y in the maze matrix
	private int startpointX;
	private int startpointY;
	// where the pacman is right now as a format of x and y in the maze matrix
	private int currCordinateX;
	private int currCordinateY;
	private int colorFlag = 0; // If blown the flag turns to 1 then we know we should change the color
	private double speed;
	
	
	/**
	 * @param startpointX
	 * @param startpointY
	 * @param currCordinateX
	 * @param currCordinateY
	 * @param colorFlag
	 * @param speed
	 */
	// constructor
	public Ghost(int startpointX, int startpointY, int currCordinateX, int currCordinateY, int colorFlag,
			double speed) {
		super();
		this.startpointX = startpointX;
		this.startpointY = startpointY;
		this.currCordinateX = currCordinateX;
		this.currCordinateY = currCordinateY;
		this.colorFlag = colorFlag;
		this.speed = speed;
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
	public int getColorFlag() {
		return colorFlag;
	}
	public void setColorFlag(int colorFlag) {
		this.colorFlag = colorFlag;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	// method to control the pacmans coordinates if the pacman goes up
	public void goUp(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();
		switch(maze[x-1][y]) {
		  case 1:
			  //wall
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
		  case 1:
			  //wall
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
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
//		  case 2:
//			  //tunnle
//			  if(this.currCordinateX == 24 && this.getCurrCordinateY() == 29) {
//				  this.currCordinateX = 6;
//				  this.currCordinateY = 0;
//			  }
//			  
//			  if(this.currCordinateX == 6 && this.getCurrCordinateY() == 0) {
//				  this.currCordinateX = 24;
//				  this.currCordinateY = 29;
//			  }
//		    break; 

		  default:
			  this.currCordinateX = x;
   			  this.currCordinateY = y-1;
		}
	}

	// method to control the pacmans coordinates if the pacman goes right
	public void goRight(Maze maze1,Game game ,int x , int y) {
		int [][]maze = maze1.getMaze();
		switch(maze[x][y+1]) {
		  case 1:
			  //wall
			  this.currCordinateX = x;
   			  this.currCordinateY = y;
		    break;
//		  case 2:
//			  //tunnle
//			  if(this.currCordinateX == 24 && this.getCurrCordinateY() == 29) {
//				  this.currCordinateX = 6;
//				  this.currCordinateY = 0;
//			  }
//			  
//			  if(this.currCordinateX == 6 && this.getCurrCordinateY() == 0) {
//				  this.currCordinateX = 24;
//				  this.currCordinateY = 29;
//			  }
//		    break; 

		  default:
			  this.currCordinateX = x;
   			  this.currCordinateY = y+1;
		}
	}
	
	
}
