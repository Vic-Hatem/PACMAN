package Model;
/*@authors Hatem, Moran, Nahawand and Grace*/
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Ghost extends GameObject{
	

	public int [][] mazee;
	public static int speed = 2;
	
	BFSFinder bfs;
	
	int min=1,max=4,range;
	public  int G_random =1 ;

	public int GhostAlive=1; //Sets it to 0 if ghost is dead
	/**
	 * @param startpointX
	 * @param startpointY
	 * @param currCordinateX
	 * @param currCordinateY
	 * @param colorFlag
	 * @param speed
	 */
	// constructor
	public Ghost(Image img,int startpoinX, int startpoinY,Canvas bp,int[][] maze) {
		super(img, startpoinX, startpoinY, bp);

		this.mazee = maze;
		
		
		range=(max-min)+1;
		   G_random = (int) (Math.random() * range)+min;
	}
	
	public void NeibourTiles(Pacman pc) //gets neibour tiles where ghost is at for pathfinding
	{
		int x,y,nx,ny;
		if(this.ShadowCollide(pc, this.height*5, this.width*5)) 
		{
			System.out.println("Finding Tiles");
			//up
			x=this.pos_curry +1;
			y=this.pos_currx +1;
			nx=this.pos_currx -1;
			ny=this.pos_curry-1;
			System.out.println("Current Position: "+this.pos_currx+"  "+this.pos_curry);
			System.out.println("UP: "+x+" down: "+y+" left: "+nx+" right: "+ny);	
		}
		
	}
	
	
	public void FindPac(Pacman pc,int[][] m) //aggresive path finding which makes ghosts follow pacman with no awareness of envrioment around them
	{
		
		if(pc.pos_currx > this.pos_currx) 
		{
		
			this.GmoveRight(m);
			System.out.println("Going Right");
			return;
		}
		if(pc.pos_currx < this.pos_currx) 
		{
			
			this.GmoveLeft(m);

			System.out.println("Going Left");
			return;

		}if(pc.pos_curry > this.pos_curry) 
		{

			this.GmoveDown(m);
			System.out.println("Going Down");
			return;

		}if(pc.pos_curry < this.pos_curry) 
		{
			this.GmoveUP(m);
			System.out.println("Going UP");
			return;
		}
	}
	
	public void Gmove(int[][] m) //randomly moves ghost in random direction when a collision occurs
	{
		
		if(GhostAlive ==0) //dont move the ghost if ghost is dead
		{
			return;
		}
		if(G_random == 1) 
		{
			this.GmoveUP(m);//go up if random dir is up
		}
		if(G_random == 2) 
		{
			this.GmoveDown(m);//go Down if random dir is up
		}
		if(G_random == 3) 
		{
			this.GmoveLeft(m);//go Left if random dir is up
		}
		if(G_random == 4) 
		{
			this.GmoveRight(m);//go RIGHT if random dir is up
		}
	}
	
	
	public void GmoveLeft(int[][] maze) //movement method for ghosts
	{
		if(GhostAlive ==0) 
		{
			return;
		}
		int x=this.pos_currx/Config.Scale;
		int y=this.pos_curry/Config.Scale; //resizes maze cordinate so that we can compare it back to original maze 
		
		switch(maze[y][x-1]) //checks for valid tiles
		{
			case 1:{
				this.pos_currx = x*Config.Scale;
				this.pos_curry = y*Config.Scale; 
				G_random = (int) (Math.random() * range)+min;

				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 6:
			case 16://obstactles ghost can pass through
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
	public void GmoveDown(int[][] maze) //movement method for ghosts
	{
		if(GhostAlive ==0) 
		{
			return;
		}
		int x=this.pos_currx/Config.Scale;
		int y=this.pos_curry/Config.Scale;
	
		switch(maze[y+1][x]) 
		{
			case 1:{
				this.pos_currx = x*Config.Scale;
				this.pos_curry = y*Config.Scale;
				G_random = (int) (Math.random() * range)+min;

				break;
			}
			
			case 3:
			case 2:
			case 4:
			case 5:
			case 6:
			case 16:
			case 7:
			case 8://obstactles ghost can pass through
			case 9:
			case 10:
			case 11:
			case 12:
			case 0:
			{
				this.pos_curry = this.pos_curry +speed;
				break;
			}
			default:
			{
				
				
				break;
			}
		}

	}
	public void GmoveRight(int[][] maze) //movement method for ghosts
	{
		if(GhostAlive ==0) 
		{
			return;
		}
		int x=this.pos_currx/Config.Scale;
		int y=this.pos_curry/Config.Scale;
	
		switch(maze[y][x+1]) 
		{
			case 1:{
				this.pos_currx = x*Config.Scale;
				this.pos_curry = y*Config.Scale;
				G_random = (int) (Math.random() * range)+min;

				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 6:
			case 16://obstactles ghost can pass through
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
	
	public void GmoveUP(int[][] maze) //movement method for ghosts
	{
		if(GhostAlive ==0) 
		{
			return;
		}
		int x=this.pos_currx/Config.Scale;
		int y=this.pos_curry/Config.Scale;
		
		switch(maze[y-1][x]) 
		{
			case 1:{
				this.pos_currx = x*Config.Scale;
				this.pos_curry = y*Config.Scale;
				
				G_random = (int) (Math.random() * range)+min;
				break;
			}
			case 3:
			case 2:
			case 4:
			case 5:
			case 6:
			case 16:
			case 7:
			case 8:
			case 9:
			case 10://obstactles ghost can pass through
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

	
	public void Follow(Pacman obj,int [][] Maze) 
	{
		int ShadowX=this.height*5;
		int ShadowY =this.width*5;
		mazee = Maze;
		if(ShadowCollide(obj,ShadowX,ShadowY))
		{
			
			System.out.println("Following Pacman");
			this.FindPac(obj, Maze);
		}
	}

	
	
	
}
