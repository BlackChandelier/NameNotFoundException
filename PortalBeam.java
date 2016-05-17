package projlab;

public class PortalBeam implements Visitor {
	
	private String color; 	//​A lövedék színét tárolja.
	private Tile tile;	 //​A lövedék helyét tárolja.
	private int direction; 	 //​A lövedék irányát tárolja.
	public int[] coordinates;
	
	@Override
	public void visit(Visitable visitable) {
		this.coordinates=((Tile) visitable).coordinates;
	}
	
	public void changeColor(String col){	//Beállítja a lövedék színét.
		color=col;
	}
	
	public String getColor(){	 //Visszaadja a lövedék színét.
		return color;
	}
	
	public void setDirection(int newDirection){ //Beállítja a lövedék irányát. 
		/*Ez a kilövéskor beállítódik.
		 *  Az irány a játékosnak a kilövés pillanatában meglévő irányával lesz azonos.*/
		direction=newDirection;

	}

	public int getDirection(){ //Visszaadja a lövedék irányát.
		return direction;	
	}
	
	
	public Visitable getVisitable(){	//Visszaadja következő mezőt.
		return tile.getVisitable(direction);	
	}

	public void setVisitable(Visitable newVisitable){	//Beállítja a következő mezőt.
	}

}