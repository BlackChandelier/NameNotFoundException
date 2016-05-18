package projlab;

public class Player implements Visitor{
	
	//private String name; //​Jatekos neve
	private int direction=0; //​A jatekos iranyat tarolja.
	private Boolean hasBox=false; //​Azt jegyzi, hogy van a felhasznalonal doboz, vagy nincs.
	private int collectedZPMs=0; //​Azert felel, hogy az osszegyujtott elemeket ZPMeket szamontartsa.
	int coordinates[]=new int[2]; //koordinatak
	
	//visitor pattern
	public void visit(Visitable visitable) { //meglatogatja az adott mezot
		switch (visitable.getClass().getSimpleName()){
		case "CleanTile":
			CleanTile tempCT= (CleanTile) visitable;
			this.coordinates=tempCT.coordinates;
			break;
		case "BoxedTile":
			BoxedTile tempB= (BoxedTile) visitable;
			this.coordinates=tempB.coordinates;
			break;
		case "Door":
			Door tempD= (Door) visitable;
			if (tempD.isPassable()){
				this.coordinates=tempD.coordinates;
			}
			break;
		case "Wall":
			break;
		case "SpecialWall":
			break;
		case "Hole":
			this.coordinates=null;
			break;
		case "Scale":
			Scale tempS= (Scale) visitable;
			this.coordinates=tempS.coordinates;
			break;
		case "StarGate":
			StarGate tempSG= (StarGate) visitable;
			this.coordinates=tempSG.coordinates;
			break;
		}
		
	}
	
	public Boolean getBox(){ //megmondja van-e doboz van a jatekosnal
		return hasBox;
	}
	
	public void changeBox(){ //megvaltoztatja, hogy van-e epp doboz a jatekosnal
		hasBox=!hasBox;
	}
		  	
	public void addZPM(){ //hozzaad egyet a jatekosnal levo ZPM-ekhez 
		collectedZPMs++;
	}
	
	public int getZPMs(){ //osszegyujtott zpm-ek szamat adja vissza
		return collectedZPMs;
	}
	
	public int getRow(){ //elso koordinata lekerdezese
		return coordinates[0];
	}
	
	public int getColumn(){ //masodik koordinata lekerdezese
		return coordinates[1];
	}
	
	public int getDirection(){ //irany lekerese
		return direction;
	}
	
	public void setDirection(int dir){ //ireny beallitasa
		direction=dir;
	}

}