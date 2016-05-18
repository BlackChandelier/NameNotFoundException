package projlab;

public class PortalBeam implements Visitor {
	
	private String color; 	//​A lovedek szinet tarolja.
	private int direction; 	 //​A lovedek iranyat tarolja.
	public int[] coordinates;
	
	//visitor pattern
	@Override
	public void visit(Visitable visitable) {
		this.coordinates=((Tile) visitable).coordinates;
	}
	
	public void changeColor(String col){	//Beallitja a lovedek szinet.
		color=col;
	}
	
	public String getColor(){	 //Visszaadja a lovedek szinet.
		return color;
	}
	
	public void setDirection(int newDirection){ //Beallitja a lovedek iranyat. 
		/*Ez a kiloveskor beallitodik.
		 *  Az irany a jatekosnak a kiloves pillanataban meglevo iranyaval lesz azonos.*/
		direction=newDirection;

	}

	public int getDirection(){ //Visszaadja a lovedek iranyat.
		return direction;	
	}
}