package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Ghost extends GameObject{
	
	// where the pacman start as a format of x and y in the maze matrix
	private int startpointX;
	private int startpointY;
	// where the pacman is right now as a format of x and y in the maze matrix
	
	ArrayList<Integer> openset = new ArrayList<Integer>();
	ArrayList<Integer> closet = new ArrayList<Integer>();
	public int [][] mazee;
	public int speed = 3;
	BFSFinder bfs;
	int min=1,max=4,range;
	public  int G_random =1 ;
	public String dir1="Right";
	public String dir2="Left";
	public String dir3="Down";
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
		//bfs = new BFSFinder(this);
		
		this.startpointX = startpoinX;
		this.startpointY = startpoinY;
		 range=(max-min)+1;
		   G_random = (int) (Math.random() * range)+min;
	}

	public void FindPac(Pacman pc,int[][] m) 
	{
		
		if(pc.pos_currx > this.pos_currx) 
		{
		
			this.GmoveRight(m);
//			System.out.println("Going Right");
			return;
		}
		if(pc.pos_currx < this.pos_currx) 
		{
			
			this.GmoveLeft(m);

//			System.out.println("Going Left");
			return;

		}if(pc.pos_curry > this.pos_curry) 
		{

			this.GmoveDown(m);
//			System.out.println("Going Down");
			return;

		}if(pc.pos_curry < this.pos_curry) 
		{
			this.GmoveUP(m);
//			System.out.println("Going UP");
			return;

		}
	}
	
	public void Gmove(int[][] m) //randomly moves ghost in random direction when a collision occurs
	{
		
	
		if(G_random == 1) 
		{
			this.GmoveUP(m);
		}
		if(G_random == 2) 
		{
			this.GmoveDown(m);
		}
		if(G_random == 3) 
		{
			this.GmoveLeft(m);
		}
		if(G_random == 4) 
		{
			this.GmoveRight(m);
		}
	}
	public void GmoveLeft(int[][] maze) //movement method for ghosts
	{
		int x=this.pos_currx/30;
		int y=this.pos_curry/30; //resizes maze cordinate so that we can compare it back to original maze 
		if(x >30 || y >30) 
		{
		return; //retuns back if cordinates are more then 30 since our maze is 30 x 30	
		}
		switch(maze[y][x-1]) //checks for valid tiles
		{
			case 1:{
				//System.out.println("Collision Left"+y + " " + x);
				this.pos_currx = x*30;
				this.pos_curry = y*30; 
				G_random = (int) (Math.random() * range)+min;

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
	public void GmoveDown(int[][] maze) //movement method for ghosts
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
				this.pos_curry = y*30;
				G_random = (int) (Math.random() * range)+min;

				break;
			}
			
		
			case 3:
			case 2:
			case 4:
			case 5:
			case 7:
			case 10:
			case 8:
			case 9:
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
				this.pos_curry = y*30;
				G_random = (int) (Math.random() * range)+min;

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
	
	public void GmoveUP(int[][] maze) //movement method for ghosts
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
				this.pos_curry = y*30;
				
				G_random = (int) (Math.random() * range)+min;
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

	
	public void Follow(Pacman obj,int [][] Maze) 
	{
		int ShadowX=this.height*5;
		int ShadowY =this.width*5;
		List<Integer> pathh = null ;
		mazee = Maze;
		if(ShadowCollide(obj,ShadowX,ShadowY))
		{
			
//			System.out.println("Following Pacman");
			this.FindPac(obj, Maze);
			//System.out.println(""+bfs.getMove(this.pos_currx,this.pos_curry, obj.pos_currx, obj.pos_curry));
		}
	}

	public boolean Finder2(int[][] maze,int x,int y,int ox,int oy) 
	{
	
		return true;
	}
	//doesnt work
	public boolean Finder(int[][] maze,int x,int y,List<Integer> path,int ox,int oy ) 
	{
		 x=this.pos_currx/30;
		 y=this.pos_curry/30;
		
		System.out.println(maze[y][x]+"= ("+x+"-"+y+") ("+ox+"-"+oy+")" );
		if (maze[y][x] == maze[ox][oy]) {

            path.add(x);

            path.add(y);
           
            return true;

        }

        if (maze[y][x] == 0) 
        {
        	 
            maze[y][x] = 2;
            int dx = -1;
            int dy = 0;


            dx = 1;
            dy = 0;
            System.out.println("======="+x+" "+y);
            if (Finder(maze, x + dx, y + dy, path,ox,oy)) {

            path.add(x);

            path.add(y);

            return true;

        	}

            dx = 0;
            dy = -1;
            if (Finder(maze, x + dx, y + dy, path,ox,oy)) {
            	 System.out.println("======="+x+" "+y);
                path.add(x);
                path.add(y);
                return true;

            }

            dx = 0;
            dy = 1;
            if (Finder(maze, x + dx, y + dy, path,ox,oy)) {
            	 System.out.println("======="+x+" "+y);
                path.add(x);
                path.add(y);
                return true;

            }

        }

        return false;

	}
	
	
	
}
