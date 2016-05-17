package projlab;

public class Hole extends Tile{
	private Tile[] tiles;	//​Eltárolja a mező szomszédjait.
	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
		visitor=null;
	}
}