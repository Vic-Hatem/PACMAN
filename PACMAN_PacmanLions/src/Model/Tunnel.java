package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Tunnel extends GameObject{
	// where the pacman start as a format of x and y in the maze matrix

	//constructor
	public Tunnel(Image img,int startpointX, int startpointY ,Canvas bp) 
	{
		
		super(img, startpointX, startpointY, bp);
	
	}
	
	public void Teleport(GameObject Obj) //this method is used for tunnel teleportation
	{
		System.out.println("Pcm Pos: "+	this.pos_currx+" "+this.pos_curry);
		System.out.println("Object Pos: "+	Obj.startx+" "+Obj.starty);

		Obj.pos_currx=this.startx;
		Obj.pos_curry=this.starty;
		
	}
	
	
}
