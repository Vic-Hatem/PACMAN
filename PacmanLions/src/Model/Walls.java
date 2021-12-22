package Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * @author Nahawand and Grace
 *
 */
public class Walls extends GameObject{
	public int CordinateX;
	public int CordinateY;
	public Walls(Image img,int cordinateX, int cordinateY,Canvas bp) 
	{
		super(img, cordinateX, cordinateY, bp);
		CordinateX = cordinateX;
		CordinateY = cordinateY;
	}
}
