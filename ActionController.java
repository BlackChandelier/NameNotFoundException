package projlab;

public class ActionController {
	public static void move(Visitor player, int direction){
		
		switch(direction){
		case 0: 
			System.out.println("-> ActionController.move(Player, 0)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new CleanTile().accept(new Player());
			Tabulator.decreaseTabNumber();
			System.out.println("<- void");
		break;
		case 1: 
			System.out.println("-> ActionController.move(Player, 1)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Hole().accept(new Player());
			System.out.println("<- void");
			System.out.println("-> endGame()");
		break;
		case 2:
			System.out.println("-> ActionController.move(Player, 2)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Wall().accept(new Player());
			System.out.println("<- void");
		break;
		case 3:
			System.out.println("-> ActionController.move(Player, 3)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Door(false).accept(new Player());
			System.out.println("<- void");
		break;
		case 4:
			System.out.println("-> ActionController.move(Player, 3)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Door(false).accept(new Player());
			System.out.println("<- void");
		break;
		}

		
	}
	
	

}
