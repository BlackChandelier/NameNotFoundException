package projlab;

public class StarGate extends Tile{
	
	private Tile[] tiles; 	//​Eltárolja a mező szomszédjait.
	Boolean passable;	//Eltárolja, hogy átjárható-e az adott csillagkapu.
	
	public Boolean isPassable(){
		return passable;
	}
	
	public void changePassable(){
		passable=!passable;
	}
}