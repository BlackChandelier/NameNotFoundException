package projlab;

public class Replicator implements Visitor {

	int direction=0; //elt�rolja, hogy milyen ir�nyba n�z a replik�tor
	Tile tile; //elt�rolja, hogy hol tart�zkodik �pp a replik�tor
	
	public void visit(Visitable visitable) {
		
	}
	
	public void setDirection() { //a replik�tor fordul, akkor h�v�dik meg ha falba �tk�z�tt
		
	}
	
	public void getDirection() { //visszaadja a replik�tor aktu�lis ir�ny�t
		
	}
	
	public static Visitable getVisitable() { // megadja, hogy mi a k�vetkez� mez�
	
		return null;
	}
	
	public static void setVisitable(Visitable visitable) { // be�ll�tja a k�vetkez� mez�t

	}
}
