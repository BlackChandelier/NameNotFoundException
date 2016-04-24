package projlab;

public abstract class Tile implements Visitable{
	
	private Tile[] tiles; 	//​Eltárolja a mező szomszédjait.
	int[] coordinates= new int[2];
	public Visitable getVisitable(int direction){	//Megadja a következő mezőt
		return tiles[direction];
	}

	public void accept(Visitor visitor) {
			visitor.visit(this);
	}
}