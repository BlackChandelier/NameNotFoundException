package projlab;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ActionController {
	
	
	private Object locker = new Object();
	
	//test
	protected Player players[] = new Player[2]; // A két játékos tárolására
												// szolgáló tömb.
	protected Tile[][] visitables​; // A pályán lévő elemeket tároló tömb.
	protected Replicator replicator​; // A replikátor helyét jegyzi.
	PortalBeam[] beams;
	int countZPMs=0; // ​A pályán lévő ZPM­ek számát jegyzi.
	Visitable additionalStoredVisitable​; // A funkciók végrehajtását
											// megkönnyítő plusz attribútum.
	Tile[] starGates​; // Azon mezők jegyzésére szolgál, ahol portált nyitottak
						// a játékosok.
	Boolean replicatorIsAlive= false ; // A replikátor létezéséről vagy nem létezéséről
								// kapunk információt a segítségével.*/
	int rows = 0;
	int columns = 0;
	boolean vic = false;
	
	// mozgatja az adott
	// visitor-t
	/* 0:balra 1:fel 2:jobbra 3:le */
	public void move(Player player, int direction) { 		
		synchronized(locker){
		//int[] t = new int[2];
		if (player.getDirection() != direction)
			player.setDirection(direction);
		else {
			if(getNextVisitable(player.coordinates, direction)!=null)
				if(!getNextVisitable(player.coordinates, direction).getClass().getSimpleName().equals("StarGate")){
					if(visitables​[player.coordinates[0]][player.coordinates[1]]
								.getClass()
								.getSimpleName()
								.equals("Scale")){
						if (player.getBox())((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-6);
						else ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-4);
						if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeight()
								<((Scale) visitables​[player.coordinates[0]][player.coordinates[1]])
									.getWeightLimit()){
							if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor()!=null){
								int[] tempDoor = ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]])
										.getDoor();
								if ((((Door) visitables​[tempDoor[0]][tempDoor[1]])
										.isPassable()))
									((Door) visitables​[tempDoor[0]][tempDoor[1]])
										.changePassable();
							}
						}
					}
					if(getNextVisitable(player.coordinates, direction).getClass().getSimpleName().equals("Hole")){
						for (int i=0; i<2; i++){
							if(players[i]==player) {
								player=null;
								players[i]=null;
							}
						}
					
					}
					else{
					getNextVisitable(player.coordinates, direction).accept(player);

					if(visitables​[player.coordinates[0]][player.coordinates[1]].getClass().getSimpleName().equals("Scale")){
						if (player.getBox())((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(6);
						else ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(4);
						if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeight()>=((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeightLimit()){
							if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor()!=null){
								int[] tempDoor = ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor();
								if (!(((Door) visitables​[tempDoor[0]][tempDoor[1]]).isPassable()))
									((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
							}
						}
					}
					
					if(visitables​[player.coordinates[0]][player.coordinates[1]]
							.getClass()
							.getSimpleName()
							.equals("CleanTile")
						&&((CleanTile) visitables​[player.coordinates[0]][player.coordinates[1]]).getZPM()){
						((CleanTile) visitables​[player.coordinates[0]][player.coordinates[1]]).changeZPM();
						player.addZPM();
						ZPMcreator(player);
						
					}
					}
				}
				else{
					switch( ((StarGate)getNextVisitable(player.coordinates, direction)).getColor() ){
					case "blue":
						if(starGates​[1]!=null)
							starGates​[1].accept(player);
						break;
					case "red":
						if(starGates​[0]!=null)
							starGates​[0].accept(player);
						break;	
					case "green":
						if(starGates​[3]!=null)
							starGates​[3].accept(player);
						break;
					case "yellow":
						if(starGates​[2]!=null)
							starGates​[2].accept(player);
						break;
					
					}
				}
			int sum=0;
			for(int n=0; n<2; n++){
				try{
					sum+=players[n].getZPMs();
				}catch(Exception e){}
			}
			if (sum>=countZPMs){
				vic = true;
			}
			}
		}
	}

	public void boxing(Player player) {
		synchronized(locker){
		if (!player.getBox()) {
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("BoxedTile")) {
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new CleanTile());
				player.changeBox();
			}
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("Scale")
					&& ((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox) {
				((Scale) getNextVisitable(player.coordinates, player.getDirection()))
					.hasBox = false;
				((Scale) getNextVisitable(player.coordinates, player.getDirection()))
					.setWeight(-2);
				if (((Scale) getNextVisitable(player.coordinates, player.getDirection()))
						.getWeight() < (((Scale) getNextVisitable(player.coordinates, player.getDirection())))
								.getWeightLimit()) {
					int[] tempDoor = ((Scale) getNextVisitable(player.coordinates, player.getDirection())).getDoor();
					if(tempDoor!=null)
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
				}
				player.changeBox();
			}
		} else {
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("CleanTile")
				&& !((CleanTile) getNextVisitable(player.coordinates, player.getDirection())
						).getZPM()
					) {
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new BoxedTile());
				player.changeBox();
			}
			if (getNextVisitable(player.coordinates, player
					.getDirection())
					.getClass()
					.getSimpleName()
					.equals("Scale")) {
				
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox = true;
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).setWeight(2);
				if (((Scale) getNextVisitable(player.coordinates, player
							.getDirection()))
							.getWeight()
						>= ((Scale) getNextVisitable(player.coordinates, player
								.getDirection()))
								.getWeightLimit()) {
					int[] tempDoor = (((Scale) getNextVisitable(player.coordinates, player
							.getDirection()))
							.getDoor());
					if(tempDoor!=null)
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable();
				}
				player.changeBox();
			}
			
		}
		}
	}

	/* megadja, hogy a meghatározott irányban mi akövetkező mező */
	public Tile getNextVisitable(int[] coordinates, int direction) { 		
		int[] temp = coordinates;
		try {
			switch (direction) {
			case 0: // balra
				return visitables​[temp[0]][temp[1] - 1];
			case 1: // fel
				return visitables​[temp[0] - 1][temp[1]];
			case 2: // jobbra
				return visitables​[temp[0]][temp[1] + 1];
			case 3: // le
				return visitables​[temp[0] + 1][temp[1]];
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	// megváltoztatja
	// az
	// adott
	public void changeVisitable(Tile changingVisitable, Tile newVisitable) { 
		int[] t = changingVisitable.coordinates;

		if (newVisitable.getClass()
				.getSimpleName()
				.equals("BoxedTile")) {
			visitables​[t[0]][t[1]] = new BoxedTile();
		} else if (newVisitable
				.getClass()
				.getSimpleName()
				.equals("CleanTile")) {
			visitables​[t[0]][t[1]] = new CleanTile();
		}
		else if (newVisitable
				.getClass()
				.getSimpleName()
				.equals("StarGate")) {
			visitables​[t[0]][t[1]] = new StarGate(((StarGate) newVisitable).getColor());
		} else if (newVisitable
				.getClass()
				.getSimpleName()
				.equals("SpecialWall")) {
			visitables​[t[0]][t[1]] = new SpecialWall();
		}

		visitables​[t[0]][t[1]].coordinates = t;

	}

	// megváltoztatja az aktuális kapukat
	public static void changeGates(Visitable oldGates[], Visitable newGates[]) { 

	}

	//az adott visitor adott színű lövedéket lő ki
	public void shoot(Player visitor,String color){ 
		synchronized(locker){
		  		PortalBeam beam= new PortalBeam();
		  		beam.changeColor(color);
		  		beam.coordinates=new int[2];
		  		beam.coordinates=visitor.coordinates;
		  		beam.setDirection(visitor.getDirection());
		  		switch(color){
		  			case "blue":
		  				beams[0]=beam;
		  				/*changeVisitable(visitables​[starGates​[0].coordinates[0]][starGates​[0].coordinates[1]],
			  					new SpecialWall());
		  				starGates​[0]=null;*/
		  				break;
		  			case "red":
		  				beams[1]=beam;
		  				/*changeVisitable(visitables​[starGates​[1].coordinates[0]][starGates​[1].coordinates[1]],
			  					new SpecialWall());
		  				starGates​[1]=null;*/
		  				break;	
		  			case "green":
		  				beams[2]=beam;
		  				/*changeVisitable(visitables​[starGates​[2].coordinates[0]][starGates​[2].coordinates[1]],
			  					new SpecialWall());
		  				starGates​[2]=null;*/
		  				break;
		  			case "yellow":
		  				/*beams[3]=beam;
		  				changeVisitable(visitables​[starGates​[3].coordinates[0]][starGates​[3].coordinates[1]],
			  					new SpecialWall());
		  				starGates​[3]=null;*/
		  				break;
		  		}		 		
		  	}
		}

	private void move(PortalBeam beam) {
		// beam.coordinates=getNextVisitable(beam.coordinates,beam.getDirection()).coordinates;
		
		((Tile) getNextVisitable(beam.coordinates,beam
				.getDirection()))
				.accept(beam);
	}

	
	private void move(Replicator rep){
		int[] temp=getNextVisitable(rep.coordinates,rep
				.getDirection())
				.coordinates;
		if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("Wall")
			||visitables​[temp[0]][temp[1]]
					.getClass()
					.getSimpleName()
					.equals("Door")
			&& !((Door)visitables​[temp[0]][temp[1]]).isPassable()
			||visitables​[temp[0]][temp[1]]
					.getClass()
					.getSimpleName()
					.equals("SpecialWall"))
			rep.setDirection();
		else if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("Hole")){
			changeVisitable(visitables​[temp[0]][temp[1]],new CleanTile());
			rep=null;
			replicatorIsAlive=false;
		}else if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("StarGate")){
			
		}
		else
			rep.coordinates=temp;
	}
	
	//lövedékek mozgatása
	private void moveBeams(){
		for(int i=0;i<4;i++){ 
			if(beams[i]!=null
					&&beams[i].coordinates[0]<rows-1
					&&beams[i].coordinates[1]<columns-1
					&&beams[i].coordinates[0]!=0
					&&beams[i].coordinates[1]!=0)
				move(beams[i]); //mozgatja a pályán lévő lövedékeket
			}
	}
	
	// replikátorok mozgatása
	public void moveReplicators(){
		if(replicatorIsAlive
				&&replicator​!=null
				&&replicator​.coordinates[0]<rows-1
				&&replicator​.coordinates[1]<columns-1
				&&replicator​.coordinates[0]!=0
				&&replicator​.coordinates[1]!=0){
			move(replicator​);
		}
	}
	
	public int getReplicatorX(){
		return replicator​.coordinates[0];
	}
	
	public int getReplicatorY(){
		return replicator​.coordinates[1];
	}
	
	public Visitable getTile(int i, int j){
		return visitables​[i][j];
	}
	
	public Player getPlayer(int i){
		return players[i];
	}  
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	// Ezeket át kéne vinni Game-be - DONE
	public void getMap() {
		// Lövedékek mozgatása
		moveBeams();		
			
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				switch (visitables​[i][j]
						.getClass()
						.getSimpleName()) {
				case "StarGate":
					int b=0;
					boolean hasBeam=false;
					while(b<4&&!hasBeam){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
						hasBeam=true;
						}
						b++;
					}
					
					if(hasBeam){
						changeVisitable(visitables​[i][j],
								new StarGate(beams[b-1].getColor()));
						switch(beams[b-1].getColor()){
				  		case "blue": 
				  			/*Kék csillagkapu bezárása esetén a csillagkpu*/
				  			if(starGates​[0]!=null){
				  				if (starGates​[0].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[0].coordinates[0]][starGates​[0].coordinates[1]],
											new SpecialWall());
								}
								starGates​[0]=null;
				  			}
				  			starGates​[0]=visitables​[i][j];
				  			break;
				  		case "red":
				  			if(starGates​[1]!=null){
				  				if (starGates​[1].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[1].coordinates[0]][starGates​[1].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[1]=null;
				  			}
				  			starGates​[1]=visitables​[i][j];
				  			break;	
				  		case "green":
				  			if(starGates​[2]!=null){
				  				if (starGates​[2].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[2].coordinates[0]][starGates​[2].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[2]=null;
				  			}
				  			starGates​[2]=visitables​[i][j];
				  			break;
				  		case "yellow":
				  			if(starGates​[3]!=null){
				  				if (starGates​[3].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[3].coordinates[0]][starGates​[3].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[3]=null;
				  			}
				  			starGates​[3]=visitables​[i][j];
				  			break;
				  		}		 		
						beams[b-1]= null;
						
					}					
					break;
				case "SpecialWall":
					b=0;
					hasBeam=false;
					while(b<4&&!hasBeam){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
						hasBeam=true;
						}
						b++;
					}
					
					if(hasBeam){
						changeVisitable(visitables​[i][j],
								new StarGate(beams[b-1].getColor()));
						switch(beams[b-1].getColor()){
				  		case "blue": 
				  			/*Kék csillagkapu bezárása esetén a csillagkpu*/
				  			if(starGates​[0]!=null){
				  				if (starGates​[0].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[0].coordinates[0]][starGates​[0].coordinates[1]],
											new SpecialWall());
								}
								starGates​[0]=null;
				  			}
				  			starGates​[0]=visitables​[i][j];
				  			break;
				  		case "red":
				  			if(starGates​[1]!=null){
				  				if (starGates​[1].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[1].coordinates[0]][starGates​[1].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[1]=null;
				  			}
				  			starGates​[1]=visitables​[i][j];
				  			break;	
				  		case "green":
				  			if(starGates​[2]!=null){
				  				if (starGates​[2].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[2].coordinates[0]][starGates​[2].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[2]=null;
				  			}
				  			starGates​[2]=visitables​[i][j];
				  			break;
				  		case "yellow":
				  			if(starGates​[3]!=null){
				  				if (starGates​[3].coordinates!=beams[b-1].coordinates) {
									changeVisitable(
											visitables​[starGates​[3].coordinates[0]][starGates​[3].coordinates[1]],
											new SpecialWall());
								}
				  				starGates​[3]=null;
				  			}
				  			starGates​[3]=visitables​[i][j];
				  			break;
				  		}		 		
						beams[b-1]= null;
						
					}					
				case "Wall":
					b=0;
					while(b<4){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
						beams[b]= null;					}
						b++;
					}					
					break;
				case "Door":
					if (((Door)visitables​[i][j]).isPassable()){
						b=0;
						hasBeam=false;

						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
								hasBeam=true;
							}
							b++;
						}					
						break;
					}else{
						b=0;
						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
							beams[b]= null;					}
							b++;
						}	
					}			
					break;
				case "BoxedTile":
					b=0;
					hasBeam=false;

					while(b<4){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
							hasBeam=true;
						}
						b++;
					}					
					break;
				case "CleanTile":
					b=0;
					hasBeam=false;

					while(b<4){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
						hasBeam=true;
						if(replicatorIsAlive && replicator​.coordinates[0]==beams[b].coordinates[0] && replicator​.coordinates[1]==beams[b].coordinates[1]){
							replicator​=null;
							replicatorIsAlive=false;
							beams[b]=null;
							}
						}
						b++;
					}					
					break;
				case "Hole":
					b=0;
					hasBeam=false;

					while(b<4){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
							hasBeam=true;
						}
						b++;
					}
					if(hasBeam)
						System.out.print("*");
					else if (players[0] != null 
							&& i == players[0].getRow() 
							&& j == players[0].getColumn()){
						players[0]=null;						
						}
					else if (players[1] != null 
							&& i == players[1].getRow() 
							&& j == players[1].getColumn()){
						players[0]=null;					
					}					
					break;
				case "Scale":
					b=0;
					hasBeam=false;

					while(b<4){
					if (beams[b]!= null
							&&i == beams[b].coordinates[0] 
							&& j == beams[b].coordinates[1] ){
							hasBeam=true;
						}
						b++;
					}					
					break;								
				}				
			}			
		}
	}

	public void writeMap() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("output.csv", "UTF-8");
		for(int i=0;i<4;i++){ 
			if(beams[i]!=null
					&&beams[i].coordinates[0]<rows-1
					&&beams[i].coordinates[1]<columns-1)
				move(beams[i]); //mozgatja a pályán lévő lövedékeket
			}
		if(replicatorIsAlive
				&&replicator​!=null
				&&replicator​.coordinates[0]<rows-1
				&&replicator​.coordinates[1]<columns-1
				&&replicator​.coordinates[0]!=0
				&&replicator​.coordinates[1]!=0){
			move(replicator​);
		}
		writer.print(","+rows+","+columns+",\n");
			for (int i = 0; i < rows; i++) {
				writer.print(',');
				for (int j = 0; j < columns; j++) {
					switch (visitables​[i][j].getClass().getSimpleName()) {
					case "SpecialWall":
						int b=0;
						boolean hasBeam=false;
						while(b<4&&!hasBeam){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
							hasBeam=true;
							}
							b++;
						}
						
						if(hasBeam){
							changeVisitable(visitables​[i][j],
									new StarGate(beams[b-1].getColor()));
							switch(beams[b-1].getColor()){
					  		case "blue":
					  			if(starGates​[0]!=null){
					  			changeVisitable(visitables​[starGates​[0].coordinates[0]][starGates​[0].coordinates[1]],
					  					new SpecialWall());
					  			}
					  			starGates​[0]=visitables​[i][j];
					  			break;
					  		case "red":
					  			if(starGates​[1]!=null){
					  			changeVisitable(visitables​[starGates​[1].coordinates[0]][starGates​[1].coordinates[1]],
					  					new SpecialWall());
					  			}
					  			starGates​[1]=visitables​[i][j];
					  			break;	
					  		case "green":
					  			if(starGates​[2]!=null){
					  			changeVisitable(visitables​[starGates​[2].coordinates[0]][starGates​[2].coordinates[1]],
					  					new SpecialWall());
					  			}
					  			starGates​[2]=visitables​[i][j];
					  			break;
					  		case "yellow":
					  			if(starGates​[3]!=null){
					  			changeVisitable(visitables​[starGates​[3].coordinates[0]][starGates​[3].coordinates[1]],
					  					new SpecialWall());
					  			}
					  			starGates​[3]=visitables​[i][j];
					  			break;
					  		}		 		
							beams[b-1]= null;
							writer.print("Ws");
						}
						else if(!hasBeam){
							writer.print("Ws");
							if (j != columns - 1)
								writer.print(",");
							}
						break;
					case "StarGate":
						if (players[0] != null 
							&& i == players[0].getRow() 
							&& j == players[0].getColumn())
							writer.print("O");
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn())
							writer.print("J");
						else{
						writer.print("P");						
						switch( ((StarGate)visitables​[i][j]).getColor() ){
						case "blue":
							writer.print("b");
							break;
						case "red":
							writer.print("r");
							break;	
						case "green":
							writer.print("g");
							break;
						case "yellow":
							writer.print("y");
							break;
							}
						}
						if (j != columns - 1)
							writer.print(",");
						break;
					case "Wall":
						b=0;
						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
							beams[b]= null;					}
							b++;
						}
						writer.print("W");
						if (j != columns - 1)
							writer.print(",");
						break;
					case "BoxedTile":
						b=0;
						hasBeam=false;

						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
								hasBeam=true;
							}
							b++;
						}
						if(hasBeam)
							writer.print("*,");
						else if (players[0] != null 
								&& i == players[0].getRow() 
								&& j == players[0].getColumn())
							writer.print("O,");
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn())
							writer.print("J,");
						else
							writer.print("B,");
						break;
					case "CleanTile":
						b=0;
						hasBeam=false;

						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
								hasBeam=true;
							}
							b++;
						}
						if(hasBeam)
							writer.print("*,");
						else if (players[0] != null 
								&& i == players[0].getRow() 
								&& j == players[0].getColumn())
							writer.print("O,");
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn())
							writer.print("J,");
						else if (replicatorIsAlive 
								&& i == replicator​.coordinates[0] 
										&& j == replicator​.coordinates[1])
							writer.print("R,");
						else if(((CleanTile) visitables​[i][j]).getZPM())
							writer.print("Z,");
						else
							writer.print("C,");
						break;
					case "Hole":
						b=0;
						hasBeam=false;

						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
								hasBeam=true;
							}
							b++;
						}
						if(hasBeam)
							writer.print("*");
						else if (players[0] != null 
								&& i == players[0].getRow() 
								&& j == players[0].getColumn()){
							players[0]=null;
							writer.print("H");
							}
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn()){
							players[0]=null;
						writer.print("H");
						}
						else
							writer.print("H");
						if (j != columns - 1)
							writer.print(",");
						break;
					case "Scale":
						b=0;
						hasBeam=false;

						while(b<4){
						if (beams[b]!= null
								&&i == beams[b].coordinates[0] 
								&& j == beams[b].coordinates[1] ){
								hasBeam=true;
							}
							b++;
						}
						if(hasBeam)
							writer.print("*,");
						else if (players[0] != null 
								&& i == players[0].getRow() 
								&& j == players[0].getColumn())
							writer.print("O,");
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn())
							writer.print("J,");
						else if (((Scale) visitables​[i][j]).hasBox)
							writer.print("B,");
						else
							writer.print("S" + ((Scale) visitables​[i][j]).getID() + "."
									+ ((Scale) visitables​[i][j]).getWeightLimit() + ",");
						break;
					case "Door":
						if (players[0] != null 
						&& i == players[0].getRow() 
						&& j == players[0].getColumn())
							writer.print("O,");
						else if (players[1] != null 
								&& i == players[1].getRow() 
								&& j == players[1].getColumn())
							writer.print("J,");
						else
							writer.print("D" + ((Door) visitables​[i][j]).getID() + ",");
						break;
					default:
						writer.print(visitables​[i][j].getClass().getSimpleName() + ",");
					}
				}
				writer.print(',');
				writer.println();
			}
		writer.close();
	}
	private void ZPMcreator(Player player){
		if(player.getZPMs()%2==0){
			int tries=0;
			Random rand = new Random();
			int i=rand.nextInt(rows-1) + 1;
			int j=rand.nextInt(columns-1) + 1;
			
			while(tries<(rows-1)*(columns-1)
					&&(!visitables​[i][j]
							.getClass()
							.getSimpleName()
							.equals("CleanTile"))
					||(visitables​[i][j]
							.getClass()
							.getSimpleName()
							.equals("CleanTile")
					&&((CleanTile) visitables​[i][j]).getZPM())){
				System.out.println(visitables​[i][j]
						.getClass()
						.getSimpleName());
				tries++;
				i=rand.nextInt(rows-1) + 1;
				j=rand.nextInt(columns-1) + 1;
			}
			
			((CleanTile) visitables​[i][j]).changeZPM();
			countZPMs++;
		}
	}
	
}