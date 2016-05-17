package projlab;
import java.lang.Math;

public class Replicator implements Visitor {

	int direction=0; //elt�rolja, hogy milyen ir�nyba n�z a replik�tor
	Tile tile; //elt�rolja, hogy hol tart�zkodik �pp a replik�tor
	public int[] coordinates;
	
	public void visit(Visitable visitable) {
		this.coordinates=((Tile)visitable).coordinates;
	}
	
	public void setDirection() { //a replik�tor fordul, akkor h�v�dik meg ha falba �tk�z�tt
		/*if(direction<3)
			direction++;
		else
			direction=0;*/
		double i=Math.random()*100;
		direction=((int)i %4);
	}
	
	public int getDirection() { //visszaadja a replik�tor aktu�lis ir�ny�t
		return direction;
	}
	
	public static Visitable getVisitable() { // megadja, hogy mi a k�vetkez� mez�
	
		return null;
	}
	
	public static void setVisitable(Visitable visitable) { // be�ll�tja a k�vetkez� mez�t

	}
}
