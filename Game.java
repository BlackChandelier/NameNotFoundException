package projlab;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Game {
	
	protected ActionController ac;
	protected View view = new View();	
	int toFile=0;	

	WallView WV = new WallView(view);
	CleanTileView CTV = new CleanTileView(view);
	BoxedTileView BTV = new BoxedTileView(view);
	DoorView DV = new DoorView(view);
	HoleView HV = new HoleView(view);
	ScaleView SV = new ScaleView(view);
	StarGateView SGV = new StarGateView(view);	   
	SpecialWallView SWV = new SpecialWallView(view);
	PlayerView PV = new PlayerView(view);
	PortalBeamView PBV = new PortalBeamView(view);	
	ReplicatorView RV = new ReplicatorView(view);
	ZPMView ZV = new ZPMView(view);
	int pDirection = -1; // feljegyezzük a játékos pozícióját
	int file=0;
	int column=0;
	int row=0;
	
	
	public void run() throws FileNotFoundException{ //A játékot készíti elő. Létrehozza az ActionControllert.
	
		ac=new ActionController();		
		try {
			view.menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CustomMouseListener listener = new CustomMouseListener();
		view.addMouseListener(listener);
		
	    /*Scanner scanFileName = new Scanner(System.in);
	    file=scanFileName.nextInt();
	    switch(file){
	    case 1:
	    	filename="test1.csv";
	    	break;
	    case 2:
	    	filename="test2.csv";
	    	break;
	    case 3:
	    	filename="test3.csv";
	    	break;
	    case 4:
	    	filename="test4.csv";
	    	break;
	    case 5:
	    	filename="test5.csv";
	    	break;
	    case 6:
	    	filename="test6.csv";
	    	break;
	    case 7:
	    	filename="test7.csv";
	    	break;
	    default:
	    	filename="level3.csv";
	    }*/		
	}
	
	public void run2(String filename) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filename)); //Itt nyitjuk meg a beolvasandó pályát
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    ac.rows=scanner.nextInt();
	    ac.columns=scanner.nextInt();
	    scanner.next();

		ac.visitables​=new Tile[ac.rows][ac.columns]; //Létrehozunk egy megfelelő méretű pályát
		ac.starGates​=new StarGate[4]; //Létrehozzuk a csillagkapukat eltároló tömböt
		for(int i=0;i<4;i++)
			ac.starGates​[i]=null;
		ac.beams=new PortalBeam[4]; //Létrehozzuk a lövedékeket eltároló tömböt
		for(int i=0;i<4;i++)
			ac.beams[i]=null;
				
	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       int tempArray[] ={row,column};
	       if(temp.contains(System.getProperty("line.separator"))){ 
	    	   /*sortörés esetén a pályán is új sort kezdünk*/
	    	   row++;
	    	   column=0;
	       }
	       else{
	    	   /*egyébként pedig a beolvasott betű alapján hozzuk létre a megfelelő típusú mezőt*/
		       switch(temp.charAt(0)){
		       		case 'P':
		       			ac.visitables​[row][column]=new StarGate();
		       		 switch(temp.charAt(1)){
		       		 case 'b':
		       			((StarGate) ac.visitables​[row][column]).setColor("blue");
		       			ac.starGates​[0]=ac.visitables​[row][column]; //a 0 helyen tároljuk a kék portált
		       			break;
		       		 case 'r':
		       			((StarGate) ac.visitables​[row][column]).setColor("red");
		       			ac.starGates​[1]=ac.visitables​[row][column]; //a 1 helyen tároljuk a piros portált
		       			break;
		       		 case 'g':
		       			((StarGate) ac.visitables​[row][column]).setColor("green");
		       			ac.starGates​[2]=ac.visitables​[row][column]; //a 2 helyen tároljuk a zöld portált
		       			break;
		       		 case 'y':
		       			((StarGate) ac.visitables​[row][column]).setColor("yellow");
		       			ac.starGates​[3]=ac.visitables​[row][column]; //a 3 helyen tároljuk a sárga portált
		       			break;
		       			}
	       			break;
		       		case 'W':
		       			if(temp.length()==1){
			       			ac.visitables​[row][column]=new Wall();
			       			ac.visitables​[row][column].coordinates=tempArray;
			       			}
		       			else{
		       				ac.visitables​[row][column]=new SpecialWall();
			       			ac.visitables​[row][column].coordinates=tempArray;
			       			}
		       			break;
		       		case 'C':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'H':
		       			ac.visitables​[row][column]=new Hole();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'R':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.replicator​=new Replicator();
		       			ac.replicator​.coordinates=tempArray;
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.replicatorIsAlive=true;
		       			break;
		       		case 'Z':
		       			ac.visitables​[row][column]=new CleanTile();
		       			((CleanTile) ac.visitables​[row][column]).changeZPM();
		       			ac.countZPMs++;
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'B':
		       			ac.visitables​[row][column]=new BoxedTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			break;
		       		case 'O':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.players[0]=new Player();
		       			ac.players[0].coordinates=tempArray;
		       			break;
		       		case 'J':
		       			ac.visitables​[row][column]=new CleanTile();
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			ac.players[1]=new Player();
		       			ac.players[1].coordinates=tempArray;
		       			break;
		       		case 'D':
		       			Door tempDoor=new Door();
		       			tempDoor.doorID=Character.getNumericValue(temp.charAt(1));
		       			ac.visitables​[row][column]=tempDoor;
		       			ac.visitables​[row][column].coordinates=tempArray;
		       			for(int i = 0; i<=row; i++)
		       			{
		       				if(i!=row){
			       			    for(int j = 0; j<ac.columns; j++)
			       			    {
			       			    	if(ac.visitables​[i][j]
			       			    			.getClass()
			       			    			.getSimpleName()
			       			    			.equals("Scale")
			       			    		&&((Scale) ac.visitables​[i][j]).getID()==tempDoor.doorID){
			       			    		((Scale) ac.visitables​[i][j]).setDoor(row,column);
			       			    	}
			       			    		
			       			    }
		       			    }
		       				else{
		       				 for(int j = 0; j<column; j++)
			       			    {
			       			    	if(ac.visitables​[i][j]
			       			    			.getClass()
			       			    			.getSimpleName()
			       			    			.equals("Scale")
			       			    		&&((Scale) ac.visitables​[i][j]).getID()==tempDoor.doorID){
			       			    		((Scale) ac.visitables​[i][j]).setDoor(row,column);
			       			    	}
			       			    		
			       			    }
		       				}
		       					
		       			}
		       			
		       			break;
		       		case 'S':
		       			Scale tempScale=new Scale();
		       			tempScale.scaleID=Character.getNumericValue(temp.charAt(1));
		       			tempScale.setWeightLimit(Character.getNumericValue(temp.charAt(3)));
		       			ac.visitables​[row][column]=tempScale;
		       			for(int i = 0; i<=row; i++)
		       			{
		       				if(i!=row){
			       			    for(int j = 0; j<ac.columns; j++)
			       			    {
			       			    	if(ac.visitables​[i][j]
			       			    			.getClass()
			       			    			.getSimpleName()
			       			    			.equals("Door")
			       			    		&&((Door) ac.visitables​[i][j]).getID()==tempScale.scaleID){
			       			    		((Scale) ac.visitables​[row][column]).setDoor(i,j);
			       			    	}
			       			    		
			       			    }
		       			    }
		       				else{
		       				 for(int j = 0; j<column; j++)
			       			    {
			       			    	if(ac.visitables​[i][j]
			       			    			.getClass()
			       			    			.getSimpleName()
			       			    			.equals("Door")
			       			    		&&((Door) ac.visitables​[i][j]).getID()==tempScale.scaleID){
			       			    		((Scale) ac.visitables​[row][column]).setDoor(i,j);
			       			    	}
			       			    		
			       			    }
		       				}
		       					
		       			}
		       			break;
		       }
		       ac.visitables​[row][column].coordinates=new int[2];
		       ac.visitables​[row][column].coordinates[0]=row;
		       ac.visitables​[row][column].coordinates[1]=column;
      		   column++;

	       }
	    }
	    scanner.close();
	    view.setMap(column, row);
	    view.addMyKeyListener(new MKeyListener());	
	    try {
			drawWalls();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ViewThread VT = new ViewThread();
	    Thread viewthread = new Thread(VT);
	    viewthread.start();
	    ReplicatorThread RT = new ReplicatorThread();
	    Thread repthread = new Thread(RT);
	    repthread.start();
	}
	
	
	public void play() throws FileNotFoundException, UnsupportedEncodingException{	//​Meghívásakor elindul a játék. Innentől kezdve az ActionController feladata a bemenetek kezelése.
	    /*System.out.print("Add meg, hogy consol-ra vagy fájlba szeretnél írni (0:consol, 1:fájl): ");
	    Scanner scanWhere = new Scanner(System.in);
	    toFile=scanWhere.nextInt();	    */
	    
				
	    try {	    	
			drawWalls();
			for(int i = 0; i < ac.getRows();i++){
		    	for(int j = 0; j < ac.getColumns();j++){		    		
		    			drawTile(i,j);
		    	}
		    }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
	    	    
		
	}	
	
	// Adott koordinátán lévő mezőt kirajzoljuk
	private void drawTile(int i, int j) throws IOException{
		Visitable v = ac.getTile(i, j);
		String classname = v.getClass()
				.getName()
				.substring(8);
		switch(classname){			
			case "CleanTile": 
				CTV.drawCleanTile(i, j);
				if(((CleanTile) v).getZPM())
					ZV.drawZPM(i, j);
			break;
			case "Wall": WV.drawWall(i, j); break;		
			case "BoxedTile": BTV.drawBoxedTile(i, j); break;
			case "Door": DV.drawDoor(i, j, (Door)v); break;
			case "Hole": HV.drawHole(i, j); break;
			case "Scale": SV.drawScale(i, j, (Scale)v); break;
			case "StarGate": SGV.drawGate(i, j, (StarGate)v); break;	   
			case "SpecialWall": SWV.drawSpecialWall(i, j); break;
		}
	}
	
	// Csak a falakat rajzoljuk ki, egyszer hívódik meg a játék folyamán összesen
	private void drawWalls() throws IOException{
		 for(int i = 0; i < ac.getRows();i++){
		    	for(int j = 0; j < ac.getColumns();j++){
		    		Visitable v = ac.getTile(i, j);
		    		String classname = v.getClass()
		    				.getName()
		    				.substring(8);
		    		switch(classname){
		    			case "Wall": WV.drawWall(i, j); break;		    			
		    		}
		    	}
		    }
	}
	
	// Megjelenítéssel foglalkozó szál hívogatja folyamatosan, megjeleníti a pályát
	
	private void Output() throws IOException{
		//O'Neil pozíciója
		int oRow;
		int oColumn;
		
		if(ac.getPlayer(0)!=null){
		oRow = ac.getPlayer(0).getRow();
		oColumn = ac.getPlayer(0).getColumn();		
		}else{
			oRow=-1;
			oColumn=-1;
		}
		//Jaffa pozíciója
		int jRow;
		int jColumn;
		
		if(ac.getPlayer(1)!=null){
		jRow = ac.getPlayer(1).getRow();
		jColumn = ac.getPlayer(1).getColumn();		
		}else{
			jRow=-1;
			jColumn=-1;
		}
		// Mezők kirajzolása, ha visitor áll rajta, nem bántjuk (nem rajzoljuk ki újra)
	    for(int i = 0; i < ac.getRows();i++){
	    	for(int j = 0; j < ac.getColumns();j++){
	    		if(ac.getPlayer(0)!=null){
	    			if (ac.getPlayer(1)!=null){
	    				if((i != oRow || j != oColumn) && (i!=jRow || j!=jColumn)){
	    	    			if(!ac.replicatorIsAlive)
	    	    				drawTile(i,j);
	    	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
	    	    				drawTile(i,j);
	    	    		}  	
	    			}else{
	    				if(i != oRow || j != oColumn){
	    	    			if(!ac.replicatorIsAlive)
	    	    				drawTile(i,j);
	    	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
	    	    				drawTile(i,j);
	    	    		}  	
	    			}
	    		}else if(ac.getPlayer(1)!=null){
	    			if(i != jRow || j != jColumn){
    	    			if(!ac.replicatorIsAlive)
    	    				drawTile(i,j);
    	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
    	    				drawTile(i,j);
    	    		}  	
	    		} else { /////////////////////////////////////////////////////////////////IDE KELL A VERESÉG KÉPERNYŐ
	    			if(!ac.replicatorIsAlive)
	    				drawTile(i,j);
	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
	    				drawTile(i,j);
	    		}
	    		/*if(i != oRow && j != oColumn || i!=jRow && j!=jColumn){
	    			if(!ac.replicatorIsAlive)
	    				drawTile(i,j);
	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
	    				drawTile(i,j);
	    		} */ 	    					
	    	}
	    }  
	    
	    //playerek, ha nem változik a játékos iránya, nem rajzoljuk ki újra a mezőt, amin áll
	    for(int i = 0; i < 2; i++){
	    	Player p = ac.getPlayer(i);
	    	if (p == null)
	    		break;
	    	boolean o = true;	    	
	    	int row = p.getRow();
	    	int column = p.getColumn();
	    	int direction = p.getDirection();
	    	if (i == 1)
	    		o = false;
	    	if(direction != pDirection){
	    		pDirection = direction;
	    		drawTile(row,column);
	    	}
	    	PV.drawPlayer(row, column, o, direction);
	    }
	    
	    //lövedékek
	    for(int i = 0; i < 4;i++){
	    	PortalBeam b = ac.beams[i];
	    	if(b != null){	    		
	    	int row = b.coordinates[0];
	    	int column = b.coordinates[1];
	    	String color = b.getColor();
	    	int direction = b.getDirection();
	    	PBV.drawBeam(row, column, color, direction);
	    	}
	    }
	    
	    //replikátor
	    if(ac.replicatorIsAlive){
	    	RV.drawReplicator(ac.getReplicatorX(), ac.getReplicatorY());
	    }		
	}
	
	private void ONeill(String temp){		
		switch(temp){
		/*O'Neill ezredes mozgatása*/
		case "4": //balra
			if(ac.players[0]!=null)
				ac.move(ac.players[0],0);
			break;
		case "8": //fel
			if(ac.players[0]!=null)
				ac.move(ac.players[0],1);
			break;
		case "6": //jobbra
			if(ac.players[0]!=null)
				ac.move(ac.players[0],2);
			break;
		case "2": //le
			if(ac.players[0]!=null)
				ac.move(ac.players[0],3);
			break;
		/*Ezredes dobozfelvétele*/
		case "5":
			if(ac.players[0]!=null)
			ac.boxing(ac.players[0]);
			break;
		/*Ezredes lövés*/
		case "7":
			if(ac.players[0]!=null)
			ac.shoot(ac.players[0], "red");
			break;
		case "9":
			if(ac.players[0]!=null)
			ac.shoot(ac.players[0], "blue");
			break;
	}
}
	
	private void Jaffa(String temp){		
		switch(temp){
		case "a": //balra
		if(ac.players[1]!=null)
			ac.move(ac.players[1],0);
		break;
		case "w": //fel
		if(ac.players[1]!=null)
			ac.move(ac.players[1],1);
		break;
		case "d": //jobbra
		if(ac.players[1]!=null)
			ac.move(ac.players[1],2);
		break;
		case "s": //le
		if(ac.players[1]!=null)
			ac.move(ac.players[1],3);
		break;
		/*Jaffa dobozfelvétele*/
		case "r":
		if(ac.players[1]!=null)
		ac.boxing(ac.players[0]);
		break;
		/*Jaffa lövés*/
		case "q":
		if(ac.players[1]!=null)
		ac.shoot(ac.players[1], "green");
		break;
		case "e":
		if(ac.players[1]!=null)
		ac.shoot(ac.players[1], "yellow");
		}
		
	}


	// Replicátor mozgatásáért felel
	private class ReplicatorThread implements Runnable{

		@Override
		public void run() {
			while(ac.replicatorIsAlive){
				ac.moveReplicators();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	//Megjelenítésért felel
	private class ViewThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					ac.getMap();
					Output();					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}							
		}
		
	}
	
	

	// Key listener, figyeli a billentyűzetet, jobban is lehetne implementálni, optimalizálásra szorul
	class MKeyListener extends KeyAdapter {

	    @Override
	    public void keyPressed(KeyEvent event) {
	    	String ch = "" + event.getKeyChar();	
	    	ONeill(ch);
	    	Jaffa(ch);
	    	try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

	
	//egérlttuntást figyelő osztáy
	class CustomMouseListener implements MouseListener{

		public int clickedButton;
		CustomMouseListener(){
			clickedButton = 0;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			//1 player gomb
			if (e.getX() > 97 && e.getX() < 303 &&
				e.getY() > 54 && e.getY() < 126)
				try {
					run2("level1.csv");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else if (e.getX() >  97 && e.getX() < 303 &&
					 e.getY() > 154 && e.getY() < 226)
				try {
					run2("level2.csv");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//custom gomb
			else if (e.getX() >  97 && e.getX() < 303 &&
					 e.getY() > 254 && e.getY() < 326)
						clickedButton = 3;
		}		

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
