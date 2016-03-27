package projlab;

public class ActionController {
	public static void move(Visitor player, int direction){
		System.out.println("-> ActionController.move(Player, 1)");
		Tabulator.increaseTabNumber();
		Player.getVisitable();
		new Player().visit(new CleanTile());
		System.out.println("<- void");
	}
	
	

}
