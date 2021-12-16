package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class Energizer extends GameObject{
	
	public int CordinateX;
	public int CordinateY;
	
	
	public Energizer(Image img,int cordinateX, int cordinateY,Canvas bp) {
		super(img, cordinateX, cordinateY, bp);
		CordinateX = cordinateX;
		CordinateY = cordinateY;
	}
	


	
	
}
