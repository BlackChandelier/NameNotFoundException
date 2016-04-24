package projlab;

public class Player implements Visitor{
	
	private String name; //​Játékos neve
	int direction; //​A játékos irányát tárolja.
	private Boolean hasBox=false; //​Azt jegyzi, hogy van a felhasználónál doboz, vagy nincs.
	private Tile tile; //Azt a Tile­t tárolja, amelyiken a felhasználó karaktere éppen áll.
	private int collectedZPMs; //​Azért felel, hogy az összegyűjtött elemeket ZPMeket számontartsa.
	int coordinates[]=new int[2];
	private Boolean par=false; // Ezen keresztül fog jelezni az ac-nak, ha kell új ZPM a pályára
			
	public static Visitable getVisitable(){ //megadja, hogy mi a következő mező
		
		return null;
	}
		
	public Boolean getBox(){ //megmondja hány doboz van a játékosnál
		return hasBox;
	}
	
	public void setVisitable(CleanTile visitable){ //beállítja a következő mezőt
		this.coordinates=visitable.coordinates;
	}
	
	public void changeBox(){ //megváltoztatja, hogy van-e épp doboz a játékosnál
		hasBox=!hasBox;
	}
	
	public void addZPM(){ //hozzáad egyet a játékosnál lévő ZPM-ekhez 
		/* Itt még új ZPM-et is kell csináltatni minden második ZPM felvételnél*/
		collectedZPMs++;
		if(collectedZPMs%2==0){
			par=true;
		}else{
			par=false;
		}
	}
	
	public int getZPM(){
		return collectedZPMs;
	}
	
	public int getRow(){
		return coordinates[0];
	}
	
	public int getColumn(){
		return coordinates[1];
	}

	@Override
	public void visit (Visitable visitable){
		Tile temp = (Tile)visitable;
		switch(visitable.getClass().getSimpleName()){
		case "CleanTile":
			this.coordinates= temp.coordinates;
			return;
		case "Scale":
			Scale tempS=(Scale) visitable;
			if(tempS.getWeight()<=0) {
				this.coordinates= temp.coordinates;
				tempS.setWeight(1);
			}
			return;
		case "Door":
			Door tempD=(Door) visitable;
			if(tempD.isPassable()) this.coordinates= temp.coordinates;
			return;
		default:
			return;
		}
	}
	
}