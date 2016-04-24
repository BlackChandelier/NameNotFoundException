package projlab;

public class Door extends Tile{
	
	private Tile[] tiles;	//​Eltárolja a mező szomszédjait.
	private Boolean passable=false; //Eltárolja, hogy nyitva van-e az ajtó
	public int doorID;
	
	public Boolean isPassable(){ //Megmondja, hogy nyitva van-e az ajtó
		return passable;
	}
	
	public int getID(){ //Megmondja, hogy nyitva van-e az ajtó
		return doorID;
	}
	
	public void changePassable(){	//Megváltoztatja az ajtó átjárhatóságát
		passable=!passable;
	}

}