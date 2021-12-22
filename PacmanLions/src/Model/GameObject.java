package Model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/*
 * 
 * Game Object class is the class that represents game objects on maze such as:
 * Pacman, ghost, energizer, powerpellet, walls,tunnels
 * All the mentioned above classes extends this game object class
 * 
 * @AUTHORS - Nahawand & Grace
 * */

/**
 * @author Nahawand and Grace
 *
 */
public class GameObject {
	//Gameobject is the central class which all other objects are based on and depenedent on it stores there current and starting positions
	
	public int pos_currx; //current X position of obj derived from gameobject class
	public int pos_curry; //current Y position of obj derived from gameobject class
	public int width; //width of the object (Used for collision Detection)
	public int height; //Height of object (Used for collision Detection)
	public int startx; //Starting X Position of Object (Used for reseting object to starting x position) 
	public int starty; //Starting Y Position of Object (Used for reseting object to starting y position)
	public Image img; //Image to represent the object
	
	GraphicsContext gc ;
	
	
	//public int dir =0;
	
	public GameObject(Image img,int pos_currx,int pos_curry, Canvas bp)
	{
		//initilizes object on canvas	
		
		//creates a rectangle around the image for collision detection
		this.width = (int) img.getWidth();
		this.height = (int) img.getHeight();
		
		//sets initilized position as current position
	   this.pos_currx = pos_currx;
	   this.pos_curry = pos_curry;
	   
	   //sets current positions as starting position
	   this.startx = pos_currx;
	   this.starty = pos_curry;
	   
	   //stores the initilized image
	   this.img = img;
	   
		//gets graphic context, this is used to draw on canvas
		gc =  bp.getGraphicsContext2D();
		//draws the image on canvas
		render();
	   //used to decide random direction for ghosts to move in when they are created
	}
	
	public GameObject() 
	{
		//added for support with Question sub class
	}
	
	public void Reset() //puts back the game object back to its starting position
	{
		this.pos_currx = this.startx;
		this.pos_curry = this.starty;
	}

	
	/**
	 * @param Obj - the game object we are hiding
	 * @param tim - the amount of time the object is hidden 
	 * hides the game object for a specific amount of time
	 */
	public void Hide(GameObject Obj,int tim) 
	{
		Obj.pos_currx = 1000;//hides the object at position 1000 outside the window
		
		new Thread (new Runnable() {//creates a thread which will handle the hiding

			@Override
			public void run() //this method runs when the thread starts
			{
				
				// TODO Auto-generated method stub
				try {
					Thread.sleep(tim); //hides the object for a specfic amount of time by putting the thread to sleep 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Obj.Reset(); //after thread wakes up it resets the object to its original location
			}
			
		}).start(); //starts the thread
	}
	

	
	/**
	 * @param Obj - the ghost we are hiding
	 * @param tim - the amount of time the ghost is hidden
	 * hides the ghost for a specific amount of time
	 */
	public void GhostHide(Ghost Obj,int tim) //hides the object for a specific amount of time
	{
		Obj.pos_currx = 1000;//hides the object at position 1000 outside the window
		
		new Thread (new Runnable() {//creates a thread which will handle the hiding

			@Override
			public void run() //this method runs when the thread starts
			{
				Obj.GhostAlive =0;
				// TODO Auto-generated method stub
				try {
					Thread.sleep(tim); //hides the object for a specfic amount of time by putting the thread to sleep 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Obj.GhostAlive=1;
				Obj.Reset(); //after thread wakes up it resets the object to its original location
			}
			
		}).start(); //starts the thread
	}
	
	
	//used for checking ranged collision
	public boolean ShadowCollide(GameObject obj,int x,int y) 
	{
		boolean overlap = this.pos_currx + x < obj.pos_currx 
				||
				obj.pos_currx + x < this.pos_currx
				||
				this.pos_curry + y< obj.pos_curry
				||
				obj.pos_curry + y	 < this.pos_curry;
		
		return !overlap;
	}

	
	//used to detect collision at 1x1 block size
	public boolean collide(GameObject obj) 
	{
		boolean overlap = this.pos_currx + this.width < obj.pos_currx 
				||
				obj.pos_currx + obj.width < this.pos_currx
				||
				this.pos_curry + this.height < obj.pos_curry
				||
				obj.pos_curry + obj.height	 < this.pos_curry;
		
		return !overlap;
	}


	//renders object on canvas
	public void render() 
	{
		gc.drawImage(img, pos_currx,pos_curry);

	}
	

}
