package projlab;

public class Tile implements Visitable{
	Tile[] tiles;
	
	public Visitable getVisitable(int direction){
		
		return tiles[0];
	}

	public void accept(Visitor visitor) {
		Tabulator.tabMethod();
		System.out.println("-> nextTile.accept(visitor)");
		Tabulator.tabMethod();
		System.out.println("<- void");		
	}

	public void accept(String name) {
		Tabulator.tabMethod();
		System.out.println("-> nextTile.accept("+name+")");
		Tabulator.tabMethod();
		System.out.println("<- void");		
	}
}
