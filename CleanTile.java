package projlab;

public class CleanTile extends Tile{
	
	@SuppressWarnings("unused")
	private Tile[] tiles;	//​Eltárolja a mező szomszédjait.
	private Boolean hasZPM=false;	//Eltárolja, hogy van-e a mezőn felvehető ZPM
	
	public void changeZPM(){ //Átállítja, hogy van-e ZPM a mezőn
		hasZPM=!hasZPM;
	}
	
	public Boolean getZPM(){ //Megmondja, hogy van-e ZPM a mezőn
		return hasZPM;
	}
}