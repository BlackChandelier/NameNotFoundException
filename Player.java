package projlab;

public class Player implements Visitor{
	
	public static void getVisitable(){
		Tabulator.tabMethod();
		System.out.println("-> Player.getVisitable()");
		Tabulator.tabMethod();
		System.out.println("<- Visitable nextTile");
	}
	public void visit(Visitable visitable) {
		Tabulator.tabMethod();
		System.out.println("-> Player.visit(nextTile)");
		Tabulator.tabMethod();
		System.out.println("<- void");
		Tabulator.decreaseTabNumber();
	}
}
