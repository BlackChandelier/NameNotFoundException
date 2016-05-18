package projlab;

public class Hole extends Tile{
	//visitor pattern
	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
		visitor=null;
	}
}