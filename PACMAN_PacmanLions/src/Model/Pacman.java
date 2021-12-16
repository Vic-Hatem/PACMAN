package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pacman extends GameObject{
	// where the pacman start as a format of x and y in the maze matrix

	// where the pacman is right now as a format of x and y in the maze matrix
	private int currCordinateX;
	private int currCordinateY;
	
	private int colorFlag = 0; // If blown the flag turns to 1 then we know we should change the color
	public boolean ObjPow = false;	
	public int speed = 2;
	
	/**
	 * @param startpointX
	 * @param startpointY
	 * @param currCordinateX
	 * @param currCordinateY
	 */
	   ImageView pacman;

	//constructor
	public Pacman(Image img,int startpointX, int startpointY ,Canvas bp) 
	{
		
		super(img, startpointX, startpointY, bp);
		
		//this.startpointX = startpointX;
		//this.startpointY = startpointY;
		this.currCordinateX = startpointX;
		this.currCordinateY = startpointY;
		//this.colorFlag = colorFlag;//no need to set colorflag when pacman is created
	
	}
	
	
	// getters and setters
//	public int getStartpointX() {
//		return startpointX;
//	}
//	public void setStartpointX(int startpointX) {
//		this.startpointX = startpointX;
//	}
//	public int getStartpointY() {
//		return startpointY;
//	}
//	public void setStartpointY(int startpointY) {
//		this.startpointY = startpointY;
//	}
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

	public void DeployBomb(GameObject obj) //handles pacmans bomb
	{
		int shadowx = this.width*3; //creates a 3x3 block sized rectangle to detect if a ghost is in range 
		int shadowy = this.height*3;
		
		if(ShadowCollide( obj, shadowx, shadowy) == true) //if a ghost is within the rectangle when the bomb was detonated it hides the ghosts
		{
			System.out.println("Collision Detected With Object");
			obj.Hide(obj,5000); //hides ghosts for 5 seconds
		}
	}
	public void moveUP(int[][] maze) //movement method for Pacman
	{
		int x=this.pos_currx/30;
		int y=this.pos_curry/30;
		if(x >30 || y >30) 
		{
			return;	
		}
		switch(maze[y-1][x]) 
		{
			case 1:{
				//System.out.println("Collision Up: "+y + " " + x);
				this.pos_currx = x*30;
				this.pos_curry =y*30;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:{
				this.pos_curry = this.pos_curry -speed;
				break;
			}
			
			default:
			{

				break;
			}
		}
	}
	public void moveLeft(int[][] maze) //movement method for Pacman
	{
		int x=this.pos_currx/30;
		int y=this.pos_curry/30;
		if(x >30 || y >30) 
		{
			return;	
		}
		switch(maze[y][x-1]) 
		{
			case 1:{
				//System.out.println("Collision Left"+y + " " + x);
				this.pos_currx = x*30;
				this.pos_curry =y*30;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:{
				this.pos_currx = this.pos_currx -speed;
				break;
			}
			default:
			{

				break;
			}
		}

	}
	public void moveDown(int[][] maze) //movement method for Pacman
	{
		
		int x=this.pos_currx/30;
		int y=this.pos_curry/30;
		if(x >30 || y >30) 
		{
			return;	
		}
		switch(maze[y+1][x]) 
		{
			case 1:{
				//System.out.println("Collision Down"+y + " " + x);
				this.pos_currx = x*30;
				this.pos_curry =y*30;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:{
				this.pos_curry = this.pos_curry +speed;
				break;
			}
			default:
			{
				break;
			}
		}

	}
	public void moveRight(int[][] maze) //movement method for Pacman
	{
		int x=this.pos_currx/30;
		int y=this.pos_curry/30;
		if(x >30 || y >30) 
		{
			return;	
		}
		switch(maze[y][x+1]) 
		{
			case 1:{
				//System.out.println("Collision Right"+y + " " + x);
				this.pos_currx = x*30;
				this.pos_curry =y*30;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:{
				this.pos_currx = this.pos_currx +speed;
				break;
			}
			default:
			{

				break;
			}
		}
	}
	public boolean isValidMove(int[][] maze,int x,int y) 
	{
		boolean isvalid = false;
		 x=this.pos_currx/30;
		 y=this.pos_curry/30;
		if(x >30 || y >30) 
		{
			return isvalid;	
		}
		switch(maze[y][x]) 
		{
			case 1:{
				//System.out.println("Collision Up: "+y + " " + x);
				isvalid = false;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:{
				isvalid = true;
			}
			
			default:
			{

				break;
			}
		}
		return isvalid;
	}
	
	

}
