package projlab;
import java.lang.Math;

public class Replicator implements Visitor {

	int direction=0; //eltarolja, hogy milyen iranyba nez a replikator
	Tile tile; //eltarolja, hogy hol tartozkodik epp a replikator
	public int[] coordinates; //koordinatak
	
	//visitor pattern
	public void visit(Visitable visitable) {
		this.coordinates=((Tile)visitable).coordinates;
	}
	
	public void setDirection() { //a replikator fordul, akkor hivodik meg ha akadalyba utkozott
		double i=Math.random()*100;
		direction=((int)i %4);
	}
	
	public int getDirection() { //visszaadja a replikator aktualis iranyat
		return direction;
	}
	
	public static Visitable getVisitable() { // megadja, hogy mi a kovetkezo mezo
		return null;
	}

}
