package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
/*@authors Hatem, Moran, Nahawand and Grace*/
public class Energizer extends GameObject{
	
	//x coordinate for energizer on maze
	public int CordinateX;
	//y coordinate for energizer on maze
	public int CordinateY;
	
	//constructor
	public Energizer(Image img,int cordinateX, int cordinateY,Canvas bp) {
		super(img, cordinateX, cordinateY, bp);
		CordinateX = cordinateX;
		CordinateY = cordinateY;
	}
	


	
	
}
