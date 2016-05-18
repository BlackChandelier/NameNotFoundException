package projlab;

public class Scale extends Tile{
	
	private int[] door;	 //Eltarolja melyik ajto tartozik a merleghez.
	private int currentWeight=0; 	//​Eltarolja mennyi suly van a merlegen.
	private int weightLimit​;	 //Eltarolja mennyi sulyt kell a merlegre helyezni az ajto kinyitasahoz.
	public int scaleID; //azonositoja
	public Boolean hasBox=false;
	
	public void setWeight(int weight){ 	//Noveli a merlegen levo sulyt.
			currentWeight+=weight;
	}
	
	public void setDoor(int row, int column){ 	//visszadaja a hozzatartozo ajtok tombjet
		door=new int[2];
		door[0]=row;
		door[1]=column;
	}
	
	public int[] getDoor(){ //visszadaja a hozzatartozo ajtok tombjet
		return door;
	}
	
	public void setWeightLimit(int limit){ 	//Beallitja a merleg sulyhatarat
			weightLimit​=limit;
	}
	
	public int getWeight(){ 	//Megadja a jelenleg a merlegen levo sulyt.
			return currentWeight;
	}
	
	public int getWeightLimit(){ //Megadja a jelenleg a merleg sulyhatarat.
		return weightLimit​; 	

	}
	
	public int getID(){ //Visszaadja a merleg azonositojat
		return scaleID; 	
	}
}