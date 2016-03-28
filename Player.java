package projlab;

public class Player implements Visitor{
	
	public static Visitable getVisitable(){
		Tabulator.tabMethod();
		System.out.println("-> Player.getVisitable()");
		Tabulator.tabMethod();
		System.out.println("<- Visitable nextTile");
		return null;
	}
	public void visit(Visitable visitable) {
		Tabulator.increaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("-> Player.visit(nextTile)");
		Player.setVisitable();
		Tabulator.tabMethod();
		System.out.println("<- void");
		
	}
	
	public static Boolean getBox(){
		Tabulator.tabMethod();
		System.out.println("-> Player.getBox()");
		return null;
		
	}
	
	public static void setVisitable(){
		Tabulator.increaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("-> Player.setVisitable(nextTile)");
		Tabulator.tabMethod();
		System.out.println("<- void");
		Tabulator.decreaseTabNumber();

	}
	
	public static void changeBox(){
		Tabulator.tabMethod();
		System.out.println("-> Player.changeBox()");
		Tabulator.tabMethod();
		System.out.println("<- void");

	}
}
