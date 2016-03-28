package projlab;

public class ActionController {
	public static void move(Visitor player, int direction){
		
		switch(direction){
		case 0: 
			System.out.println("-> ActionController.move(player, 0)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new CleanTile().accept(new Player());
			Tabulator.decreaseTabNumber();
			System.out.println("<- void");
		break;
		case 1: 
			System.out.println("-> ActionController.move(player, 1)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Hole().accept(new Player());
			System.out.println("<- void");
			System.out.println("-> endGame()");
		break;
		case 2:
			System.out.println("-> ActionController.move(player, 2)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Wall().accept(new Player());
			System.out.println("<- void");
		break;
		case 3:
			System.out.println("-> ActionController.move(player, 3)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Door(false).accept(new Player());
			System.out.println("<- void");
		break;
		case 4:
			System.out.println("-> ActionController.move(player, 3)");
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Door(false).accept(new Player());
			System.out.println("<- void");
		break;
		}		
	}
	
	public static Visitable getNextTile(Visitable currentTile,int direction){
		Tabulator.tabMethod();
		System.out.println("-> ActionController.getNextVisitable(currentTile,1)");
		Tabulator.tabMethod();
		System.out.println("<- Visitable nextTile");
		
		return null;
		}

	public static void changeVisitable(Visitable changingVisitable, Visitable newVisitable){
		Tabulator.tabMethod();
		System.out.print("-> ActionController.changeVisitable");
	}
	
	public static void changeGates(Visitable oldGates[], Visitable newGates[]){
		Tabulator.decreaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("->  ActionController.changeGates(oldGates[], newGates[])");
		Tabulator.tabMethod();
		System.out.println("<- void");


	}
	
	public static void shoot(Visitor visitor,String color, int subID){
		System.out.println("-> ActionController.shoot(Player,"+color+")" );
		Tabulator.increaseTabNumber();
		ActionController.getNextTile(new Tile(),1); //megn�zz�k mi a k�vetkez� mez�
		switch(subID){
		case 1: //szakad�k felett
			//Tabulator.increaseTabNumber();
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a l�ved�k tov�bbrep�l a szakad�k felett
			break;
		case 2: //falra
			break;
		case 3: //dobozra
			 new Tile().accept("PortalBeam");
			 new PortalBeam().visit(new Tile());  //a l�ved�k tov�bbrep�l a doboz felett
			break;
		case 4: //speci�lis falra
			Tabulator.increaseTabNumber();
			new Tile().accept("PortalBeam");
			new PortalBeam().visit(new Tile());
			ActionController.changeGates(null, null);  //a l�ved�k �j csillagkaput �ll�t be
			break;
		case 5: //nyitott ajt�n
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a l�ved�k tov�bbrep�l a nyitott ajt�n
			break;
		}
		Tabulator.decreaseTabNumber();
		Tabulator.decreaseTabNumber();
		Tabulator.tabMethod();
		System.out.println("<- void");

	}

}
