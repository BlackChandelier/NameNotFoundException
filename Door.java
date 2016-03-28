package projlab;

public class Door extends Tile{
	private Boolean passable;
	Door(Boolean passable){
		this.passable=passable;
	}
	public Door() {
		// TODO Auto-generated constructor stub
	}
	public void accept(Player player) {
		if(passable==true){
			Tabulator.tabMethod();
			System.out.println("-> nextTile.accept(Player)");
			Tabulator.tabMethod();
			System.out.println("<- void");
			new Player().visit(new Door());
		}
		else{
			Tabulator.tabMethod();
			System.out.println("-> nextTile.accept(Player)");
			Tabulator.tabMethod();
			System.out.println("<- void");
		}
	}
	public Boolean isPassable(){
		return passable;
	}

}
