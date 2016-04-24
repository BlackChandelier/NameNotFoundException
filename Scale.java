package projlab;

public class Scale extends Tile{
	
	private Tile[] tiles; 	//​Eltárolja a mező szomszédjait.
	private Tile door​;	 //Eltárolja melyik ajtó tartozik a mérleghez.
	private int currentWeight=0; 	//​Eltárolja mennyi súly van a mérlegen.
	private int weightLimit​;	 //Eltárolja mennyi súlyt kell a mérlegre helyezni az ajtó kinyitásához.
	public int scaleID;

	public void setWeight(int weight){ 	//Növeli a mérlegen lévő súlyt.
			currentWeight+=weight;
	}
	
	public void setWeightLimit(int limit){ 	//Növeli a mérlegen lévő súlyt.
			weightLimit​=limit;
	}
	public int getWeight(){ 	//Megadja a jelenleg a mérlegen lévő súlyt.
			return currentWeight;
	}
	
	public int getWeightLimit(){ //Megadja a jelenleg a mérleg súlyhatárát.
		return weightLimit​; 	
	}
	
	public int getID(){ //Visszaadja a mérleg azonosítóját
		return scaleID; 	
	}
}
