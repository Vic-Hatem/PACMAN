package Model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	//Gameobject is the central class which all other objects are based on and depenedent on it stores there current and starting positions
	
	public int pos_currx; //current X position of obj derived from gameobject class
	public int pos_curry; //current X position of obj derived from gameobject class
	public int width; //width of the object (Used for collision Detection)
	public int height; //Height of object (Used for collision Detection)
	public int startx; //Starting X Position of Object (Used for reseting object to starting x position) 
	public int starty; //Starting Y Position of Object (Used for reseting object to starting y position)
	public Image img; //Image to represet the object
	
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
	  

//		System.out.println("Created: at "+pos_currx+" "+ pos_curry);
		
	}
	public GameObject() 
	{
		//added for support with Question sub class
	}
	public void Reset() //puts back the object back to its starting position
	{
		this.pos_currx = this.startx;
		this.pos_curry = this.starty;
	}
	
	public void Hide(GameObject Obj,int tim) //hides the object for a specific amount of time
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

	
	
	public boolean ShadowCollide(GameObject obj,int x,int y) //used for checking ranged collision
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
	
	public boolean collide(GameObject obj) //used to detect collision at 1x1 block size
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


	
	
	public void render() //renders object on canvas
	{
		gc.drawImage(img, pos_currx,pos_curry);

	}
	

}
