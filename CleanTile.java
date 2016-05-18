package projlab;

public class CleanTile extends Tile{
	
	private Boolean hasZPM=false;	//Eltarolja, hogy van-e a mezon felveheto ZPM
	
	public void changeZPM(){ //Atallitja, hogy van-e ZPM a mezon
		hasZPM=!hasZPM;
	}
	
	public Boolean getZPM(){ //Megmondja, hogy van-e ZPM a mezon
		return hasZPM;
	}
}