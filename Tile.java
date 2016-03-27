package projlab;

public class Tile implements Visitable{
	Tile[] tiles;
	
	public Visitable getVisitable(int direction){
		
		return tiles[0];
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
