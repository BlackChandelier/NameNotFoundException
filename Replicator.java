package projlab;
import java.lang.Math;

public class Replicator implements Visitor {

	int direction=0; //eltárolja, hogy milyen irányba néz a replikátor
	Tile tile; //eltárolja, hogy hol tartózkodik épp a replikátor
	public int[] coordinates;
	
	public void visit(Visitable visitable) {
		this.coordinates=((Tile)visitable).coordinates;
	}
	
	public void setDirection() { //a replikátor fordul, akkor hívódik meg ha falba ütközött
		/*if(direction<3)
			direction++;
		else
			direction=0;*/
		double i=Math.random()*100;
		direction=((int)i %4);
	}
	
	public int getDirection() { //visszaadja a replikátor aktuális irányát
		return direction;
	}
	
	public static Visitable getVisitable() { // megadja, hogy mi a következõ mezõ
	
		return null;
	}
	
	public static void setVisitable(Visitable visitable) { // beállítja a következõ mezõt

	}
}
