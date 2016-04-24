package projlab;

import java.util.Arrays;

public class ActionController {
	
	protected Player players[]=new Player[2]; 	//A két játékos tárolására szolgáló tömb.
	
	protected Visitor replicator​; 	//A replikátor helyét jegyzi.
	int countZPMs; 	//​A pályán lévő ZPM­ek számát jegyzi.
	Visitable additionalStoredVisitable​;	 //A funkciók végrehajtását megkönnyítő plusz attribútum.
	Visitable[] starGates​;	//Azon mezők jegyzésére szolgál, ahol portált nyitottak a játékosok.
	Boolean replicatorIsAlive​; 	//A replikátor létezéséről vagy nem létezéséről kapunk információt a segítségével.*/
	int rows=0;
	int columns=0;
	Tile[][] visitables​;	 //A pályán lévő elemeket tároló tömb.
	
	public void move(Visitor visitor, int direction){ //mozgatja az adott visitor-t
		String s=visitor.getClass().getSimpleName();
		if(s.equals("Player")){	
			Player temp = (Player) visitor;
			if(temp.direction==direction){ // mozgatás
				getNextVisitable(temp.coordinates, direction).accept(visitor);
			}else{ // forgatás
				temp.direction=direction;
			}
		}
		else if(s.equals("PortalBeam")){	
			PortalBeam temp = (PortalBeam) visitor;
			if(temp.direction==direction){ // mozgatás
				getNextVisitable(temp.coordinates, direction).accept(visitor);
			}else{ // forgatás
				temp.direction=direction;
			}
		}
	}
	
	public void boxing(Player player){
		if(!player.getBox()){
			if(getNextVisitable(player.coordinates, player.direction).getClass().toString().contains("BoxedTile")){
				player.changeBox();
				changeVisitable(getNextVisitable(player.coordinates, player.direction), new CleanTile());
			}
		}
		else{
			if(getNextVisitable(player.coordinates, player.direction).getClass().toString().contains("CleanTile")){
				player.changeBox();
				changeVisitable(getNextVisitable(player.coordinates, player.direction), new BoxedTile());
			}
		}
	}
	
	public Tile getNextVisitable(int[] coordinates,int direction){ //megadja, hogy a meghatározott irányban mi a következő mező
		int[] temp = coordinates;
		try{
			switch(direction){
				case 4:
					return visitables​[temp[0]][temp[1]-1];
				case 7:
					return visitables​[temp[0]-1][temp[1]-1];
				case 8:
					return visitables​[temp[0]-1][temp[1]];
				case 9:
					return visitables​[temp[0]-1][temp[1]+1];
				case 6:
					return visitables​[temp[0]][temp[1]+1];
				case 3:
					return visitables​[temp[0]+1][temp[1]+1];
				case 2:
					return visitables​[temp[0]+1][temp[1]];
				case 1:
					return visitables​[temp[0]+1][temp[1]-1];
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}

	public void changeVisitable(Tile changingVisitable, Tile newVisitable){ //megváltoztatja az adott visitable-t
		Tile temp_2= newVisitable;
		switch(newVisitable.getClass().getSimpleName()){
		case "BoxedTile":
			temp_2=(BoxedTile) newVisitable;
			break;
		case "CleanTile":
			temp_2=(CleanTile) newVisitable;
			break;
		}
		int[] t=changingVisitable.coordinates;
		temp_2.coordinates=t;
		visitables​[t[0]][t[1]]=temp_2;
	}
	
	public static void changeGates(Visitable oldGates[], Visitable newGates[]){ //megváltoztatja az aktuális kapukat

	}
	
	public void shoot(Player visitor,String color){ //az adott visitor adott színű lövedéket lő ki
		boolean alive=true;
		PortalBeam beam= new PortalBeam();
		beam.changeColor(color);
		beam.coordinates=visitor.coordinates;
		beam.direction=visitor.direction;
		while(alive)
			move(beam, beam.direction);
	}
	
	public void getMap(){

		for(int i = 0; i<rows; i++)
		{
		    for(int j = 0; j<columns; j++)
		    {
		    	switch(visitables​[i][j].getClass().getSimpleName()){
		    	case "Wall":
		    		System.out.print("W");
		    		if(j!=columns-1)
			    		System.out.print(",");
		    		break;
		    	case "BoxedTile":
		    		System.out.print("B,");
		    		break;
		    	case "CleanTile":
		    		if(players[0]!=null&&i==players[0].getRow()&&j==players[0].getColumn())
			    		System.out.print("O,");
		    		else if(players[1]!=null&&i==players[1].getRow()&&j==players[1].getColumn())
			    		System.out.print("J,");
		    		else
		    		System.out.print("C,");
		    		break;
		    	case "Scale":
		    		System.out.print("S"+((Scale) visitables​[i][j]).getID()+"."+((Scale) visitables​[i][j]).getWeightLimit()+",");
		    		break;
		    	case "Door":
		    		System.out.print("D"+((Door) visitables​[i][j]).getID()+",");
		    		break;
		    	default:
		    		System.out.print(visitables​[i][j].getClass().getSimpleName()+",");
		    		}
		    }
		    System.out.println();
		}	
	}

}