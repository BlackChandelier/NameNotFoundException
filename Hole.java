package projlab;

public class Hole extends Tile{
	@SuppressWarnings("unused")
	private Tile[] tiles;	//​Eltárolja a mező szomszédjait.
	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
		visitor=null;
	}
}