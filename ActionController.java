package projlab;

import java.util.Random;

public class ActionController {
	
	
	private Object locker = new Object();
	
	//test
	protected Player players[] = new Player[2]; // A ket jatekos tarolasara
												// szolgalo tomb.
	protected Tile[][] visitables​; // A palyan levo elemeket tarolo tomb.
	protected Replicator replicator​; // A replikator helyet jegyzi.
	PortalBeam[] beams;
	int countZPMs=0; // ​A palyan levo ZPM­ek szamat jegyzi.
	Visitable additionalStoredVisitable​; // A funkciok vegrehajtasat
											// megkonnyito plusz attributum.
	Tile[] starGates​; // Azon mezok jegyzesere szolgal, ahol portalt nyitottak
						// a jatekosok.
	Boolean replicatorIsAlive= false ; // A replikator letezeserol vagy nem letezeserol
								// kapunk informaciot a segitsegevel.*/
	int rows = 0; //sorok szama
	int columns = 0; //oszlopok szama
	boolean vic = false; //gyozelem jelzese
	
	// mozgatja az adott jatekost
	/* 0:balra 1:fel 2:jobbra 3:le */
	public void move(Player player, int direction) { 		
		synchronized(locker){
		if (player.getDirection() != direction)
			player.setDirection(direction);
		else {
			if(getNextVisitable(player.coordinates, direction)!=null)
				if(!getNextVisitable(player.coordinates, direction).getClass().getSimpleName().equals("StarGate")){
					if(visitables​[player.coordinates[0]][player.coordinates[1]]
								.getClass()
								.getSimpleName()
								.equals("Scale")){ //ha merlegen ALTUNK es most elmozdulunk onnan
						if (player.getBox())((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-6); //ha volt nalunk doboz...
						else ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(-4); //ha nem
						if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeight()
								<((Scale) visitables​[player.coordinates[0]][player.coordinates[1]])
									.getWeightLimit()){ //limitet ellenorzunk
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
					if(getNextVisitable(player.coordinates, direction).getClass().getSimpleName().equals("Hole")){ //ha lyukba esunk
						for (int i=0; i<2; i++){
							if(players[i]==player) {
								player=null;
								players[i]=null; //meghal a jatekos
							}
						}
					
					}
					else{
					getNextVisitable(player.coordinates, direction).accept(player);

					if(visitables​[player.coordinates[0]][player.coordinates[1]].getClass().getSimpleName().equals("Scale")){ //ha merleg
						if (player.getBox())((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(6); //ha van nalunk doboz
						else ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).setWeight(4); //ha nincs
						if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeight()>=((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getWeightLimit()){//ha atleptuk a limitet
							if(((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor()!=null){
								int[] tempDoor = ((Scale) visitables​[player.coordinates[0]][player.coordinates[1]]).getDoor();//ajtok megjegyzese
								if (!(((Door) visitables​[tempDoor[0]][tempDoor[1]]).isPassable()))
									((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable(); //kinyitjuk
							}
						}
					}
					
					if(visitables​[player.coordinates[0]][player.coordinates[1]]
							.getClass()
							.getSimpleName()
							.equals("CleanTile") //ha szabad mezo...
						&&((CleanTile) visitables​[player.coordinates[0]][player.coordinates[1]]).getZPM()){//es van rajta ZPM...
						((CleanTile) visitables​[player.coordinates[0]][player.coordinates[1]]).changeZPM();//elvesszuk tole
						player.addZPM(); // ZPM felvetele
						ZPMcreator(player);//megnezzuk hgy paros szamu-e a ZPM az adott jatekosnal
						
					}
					}
				}
				else{
					switch( ((StarGate)getNextVisitable(player.coordinates, direction)).getColor() ){ //ha csillagkapu, ellenorizzuk, majd a parjanak a koordinatajara lepunk
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
					sum+=players[n].getZPMs(); //ZPM szam noveles osszegyujtese
				}catch(Exception e){}
			}
			if (sum>=countZPMs){ //osszegyujtott ZPM szam ellenorzese
				vic = true;
			}
			}
		}
	}

	//a dobozolast megvalosito fuggveny
	public void boxing(Player player) {
		synchronized(locker){
		if (!player.getBox()) { //ha nincs doboz a jatekosnal...
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("BoxedTile")) { //ha doboz van elotte...
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new CleanTile());
				player.changeBox(); //megvaltoztatjuk a jatekosnal van-e doboz, vagy sem
			}
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("Scale") //ha meleg van elotte...
					&& ((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox) {
				((Scale) getNextVisitable(player.coordinates, player.getDirection()))
					.hasBox = false;
				((Scale) getNextVisitable(player.coordinates, player.getDirection()))
					.setWeight(-2);
				if (((Scale) getNextVisitable(player.coordinates, player.getDirection()))
						.getWeight() < (((Scale) getNextVisitable(player.coordinates, player.getDirection())))
								.getWeightLimit()) { //ellenorizzuk a sulylimitet
					int[] tempDoor = ((Scale) getNextVisitable(player.coordinates, player.getDirection())).getDoor(); //a kapuhoz tartozo ajtok
					if(tempDoor!=null)
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable(); //kinyitas/becsukas
				}
				player.changeBox(); //megvaltoztatjuk a jatekosnal van-e doboz, vagy sem
			}
		} else { //ha van doboz a jatekosnal...
			if (getNextVisitable(player.coordinates, player.getDirection())
					.getClass()
					.getSimpleName()
					.equals("CleanTile") //ha szabad mezo van...
				&& !((CleanTile) getNextVisitable(player.coordinates, player.getDirection())
						).getZPM() //es van rajta ZPM...
					) {
				changeVisitable(getNextVisitable(player.coordinates, player.getDirection()), new BoxedTile()); //megvaltozatjuk a palyaelemet
				player.changeBox(); //megvaltoztatjuk a jatekosnal van-e doboz, vagy sem
			}
			if (getNextVisitable(player.coordinates, player
					.getDirection())
					.getClass()
					.getSimpleName()
					.equals("Scale")) { //ha merleg van...
				
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).hasBox = true; //rarakjuk a dobozt
				((Scale) getNextVisitable(player.coordinates, player.getDirection())).setWeight(2); //megvaltoztatjuk a sulyat
				if (((Scale) getNextVisitable(player.coordinates, player
							.getDirection()))
							.getWeight()
						>= ((Scale) getNextVisitable(player.coordinates, player
								.getDirection()))
								.getWeightLimit()) { //limit ellenorzese
					int[] tempDoor = (((Scale) getNextVisitable(player.coordinates, player
							.getDirection()))
							.getDoor()); //ajtok feljegyzese
					if(tempDoor!=null)
						((Door) visitables​[tempDoor[0]][tempDoor[1]]).changePassable(); //kinyitas/becsukas
				}
				player.changeBox(); //megvaltoztatjuk a jatekosnal van-e doboz, vagy sem
			}
			
			if (getNextVisitable(player.coordinates, player
					.getDirection())
					.getClass()
					.getSimpleName()
					.equals("Hole")){ //ha lyuk, akkor el kell pusztitani a dobozt
				player.changeBox(); //amit csak ilyen egyszeruen teszunk meg
			}
			
		}
		}
	}

	// megadja, hogy a meghatarozott iranyban mi akovetkezo mezo 
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

	// megvaltoztatja az adott palyaelemet egy masik adott elemre
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

	//az adott visitor adott szinu lovedeket lo ki
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
		  				break;
		  			case "red":
		  				beams[1]=beam;
		  				break;	
		  			case "green":
		  				beams[2]=beam;
		  				break;
		  			case "yellow":
		  				beams[3]=beam;
		  				break;
		  		}		 		
		  	}
		}

	//a lovedekek mozgatasat valositja meg
	private void move(PortalBeam beam) {
		((Tile) getNextVisitable(beam.coordinates,beam
				.getDirection()))
				.accept(beam);
	}

	//a replikator mozgatasat valositja meg
	private void move(Replicator rep){
		int[] temp=getNextVisitable(rep.coordinates,rep
				.getDirection())
				.coordinates;
		if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("Wall") //ha fallal talalkozik...
			||visitables​[temp[0]][temp[1]]
					.getClass()
					.getSimpleName()
					.equals("Door") //vagy ajtoval...
			&& !((Door)visitables​[temp[0]][temp[1]]).isPassable() //ami csukva van...
			||visitables​[temp[0]][temp[1]]
					.getClass()
					.getSimpleName()
					.equals("SpecialWall")) //vagy specialis fallal
			rep.setDirection(); //iranyt kell valtoztatni
		else if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("Hole")){ //ha lyukkal...
			changeVisitable(visitables​[temp[0]][temp[1]],new CleanTile());
			rep=null;
			replicatorIsAlive=false; //elpusztul es ezt jelezzuk
		}else if(visitables​[temp[0]][temp[1]]
				.getClass()
				.getSimpleName()
				.equals("StarGate")){
			//itt nem csinalunk semmit, nem mehet at csillagkapun
		}
		else
			rep.coordinates=temp; //egyebkent pedig mozgatjuk
	}
	
	//lovedekek mozgatasa
	private void moveBeams(){
		for(int i=0;i<4;i++){ 
			if(beams[i]!=null
					&&beams[i].coordinates[0]<rows-1
					&&beams[i].coordinates[1]<columns-1
					&&beams[i].coordinates[0]!=0
					&&beams[i].coordinates[1]!=0)
				move(beams[i]); //mozgatja a palyan levo lovedekeket
			}
	}
	
	// replikatorok mozgatasa
	public void moveReplicators(){
		if(replicatorIsAlive
				&&replicator​!=null
				&&replicator​.coordinates[0]<rows-1
				&&replicator​.coordinates[1]<columns-1
				&&replicator​.coordinates[0]!=0
				&&replicator​.coordinates[1]!=0){
			move(replicator​); //mozgatja a palyan levo replikatort
		}
	}
	
	//replikator X koodinatajat adja vissza
	public int getReplicatorX(){
		return replicator​.coordinates[0];
	}
	
	//replikator Y koodinatajat adja vissza
	public int getReplicatorY(){
		return replicator​.coordinates[1];
	}
	
	//adott helyen visszaadja a palyaelemet
	public Visitable getTile(int i, int j){
		return visitables​[i][j];
	}
	
	//vissazadja a jatekosok kozul az egyiket
	public Player getPlayer(int i){
		return players[i];
	}  
	
	//visszaadja a sorok szamat
	public int getRows(){
		return rows;
	}
	
	//visszaadja az oszlopok szamat
	public int getColumns(){
		return columns;
	}
	
	// Egy multifunkcionalis fuggveny. Kiir es mozgatja a lovedekeket.
	public void getMap() {
		// Lovedekek mozgatasa
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
						for(int n=0; n<4; n++){
							if (starGates​[n]!=null)
								if(starGates​[n].equals(visitables​[i][j])) starGates​[n]=null;
						}
						changeVisitable(visitables​[i][j],
								new StarGate(beams[b-1].getColor()));
						switch(beams[b-1].getColor()){
				  		case "blue": 
				  			/*Kek csillagkapu bezarasa eseten a csillagkpu*/
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
				  			/*Kek csillagkapu bezarasa eseten a csillagkpu*/
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
	
	//az a fuggveny, ami rendom helyre lerak egy ZPM-et, ha az uj ZPM-et felvevo jatekos paros szamu ZPM-el rendelkezik
	private void ZPMcreator(Player player){
		if(player.getZPMs()%2==0){
			int tries=0;
			Random rand = new Random();
			int i=rand.nextInt(rows-1) + 1;
			int j=rand.nextInt(columns-1) + 1;
			
			while(tries<(rows-1)*(columns-1) //probalkozunk...
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
			
			((CleanTile) visitables​[i][j]).changeZPM(); //leraktuk, adunk neki ZPM-et
			countZPMs++;
		}
	}
	
}