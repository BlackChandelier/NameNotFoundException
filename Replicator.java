package projlab;

public class Replicator implements Visitor {

	int direction=0; //eltárolja, hogy milyen irányba néz a replikátor
	Tile tile; //eltárolja, hogy hol tartózkodik épp a replikátor
		
	public void setDirection() { //a replikátor fordul, akkor hívódik meg ha falba ütközött
		
	}
	
	public void getDirection() { //visszaadja a replikátor aktuális irányát
		
	}
	
	public static Visitable getVisitable() { // megadja, hogy mi a következõ mezõ
	
		return null;
	}
	
	public static void setVisitable(Visitable visitable) { // beállítja a következõ mezõt

	}

	@Override
	public void visit(Visitable visitable) {
		// TODO Auto-generated method stub
		
	}
}
