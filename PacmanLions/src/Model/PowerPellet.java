package Model;

public class PowerPellet {
	
	public int CordinateX;
	public int CordinateY;
	
	
	public int getCordinateX() {
		return CordinateX;
	}
	public void setCordinateX(int cordinateX) {
		CordinateX = cordinateX;
	}
	public int getCordinateY() {
		return CordinateY;
	}
	public void setCordinateY(int cordinateY) {
		CordinateY = cordinateY;
	}
	public PowerPellet(int cordinateX, int cordinateY) {
		super();
		CordinateX = cordinateX;
		CordinateY = cordinateY;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CordinateX;
		result = prime * result + CordinateY;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerPellet other = (PowerPellet) obj;
		if (CordinateX != other.CordinateX)
			return false;
		if (CordinateY != other.CordinateY)
			return false;
		return true;
	}
	
}
