package projlab;

public class StarGate extends Tile{
	Boolean passable;
	
	public Boolean isPassable(){
		return passable;
	}
	
	public Boolean changePassable(){
		Tabulator.tabMethod();
		System.out.println("-> StarGate.changePassable()");
		Tabulator.tabMethod();
		System.out.println("<- void");
		return null;
	}
}
