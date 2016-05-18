package projlab;

public abstract class Tile implements Visitable{
	
	private Tile[] tiles; 	//​Eltarolja a mezo szomszedjait.
	int[] coordinates= new int[2];
	public Visitable getVisitable(int direction){	//Megadja a kovetkezo mezot
		return tiles[direction];
	}

	//visitor pattern
	public void accept(Visitor visitor) {
			visitor.visit(this);
	}
}