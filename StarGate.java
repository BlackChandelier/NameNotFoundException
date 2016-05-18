package projlab;

public class StarGate extends Tile{

	Boolean passable;	//Eltarolja, hogy atjarhato-e az adott csillagkapu.
	private String color;
	
	public StarGate() { //alap konstruktor
	}
	
	public StarGate(String col) { //adott szinnel konstruktor
		color=col;
	}

	public Boolean isPassable(){ //atjarhatosag lekerdezese
		return passable;
	}
	
	public void changePassable(){ //atjarhatosag valtoztatasa
		passable=!passable;
	}
	
	public void setColor(String color){ //szin beallitasa
		this.color=color;
	}
	
	public String getColor(){ //szin lekerdezese
		return color;
	}
	
	public void changeColor(String col) { //szin valtasa
		color=col;
	}
}