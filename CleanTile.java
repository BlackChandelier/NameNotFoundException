package projlab;

public class CleanTile extends Tile{
	public void accept(Visitor visitor){
		Tabulator.tabMethod();
		System.out.println("-> nextTile.accept(player)");
		new Player().visit(new CleanTile());
		Tabulator.decreaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("<- void");

	}
	
}
