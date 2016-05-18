package projlab;

public class Door extends Tile{
	
	private Boolean passable=false; //Eltarolja, hogy nyitva van-e az ajto
	public int doorID;
	
	public Boolean isPassable(){ //Megmondja, hogy nyitva van-e az ajto
		return passable;
	}
	
	public void changePassable(){	//Megvaltoztatja az ajto atjarhatosagat
		passable=!passable;
	}
	
	public int getID(){ //Visszaadja az ajto azonositojat
		return doorID; 	
	}
}