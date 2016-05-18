package projlab;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	protected ActionController ac;
	protected View view = new View();	

	//megjelenitest segito osztalyok letrehozasa
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
	
	int pDirection = -1; // feljegyezzuk a jatekos poziciojat
	int column=0; //oszlopok
	int row=0; //sorok
	ViewThread VT;
    Thread viewthread;
    CustomMouseListener listener;
	
	public void run() throws FileNotFoundException{ //A jatekot kesziti elo. Letrehozza az ActionControllert.
	
		ac=new ActionController();
		
		try {
			view.menu(); // menu megjelenitese
		} catch (IOException e) {
			e.printStackTrace();
		}
		listener = new CustomMouseListener(); //listener letrehozasa
		view.addMouseListener(listener);
	}
	
	//egy masik futtato folyamat, aminek a dolga a felinicializalast
	public void run2(String filename) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filename)); //Itt nyitjuk meg a beolvasando palyat
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    ac.rows=scanner.nextInt();
	    ac.columns=scanner.nextInt();
	    scanner.next();

		ac.visitables​=new Tile[ac.rows][ac.columns]; //Letrehozunk egy megfelelo meretu palyat
		ac.starGates​=new StarGate[4]; //Letrehozzuk a csillagkapukat eltarolo tombot
		for(int i=0;i<4;i++)
			ac.starGates​[i]=null;
		ac.beams=new PortalBeam[4]; //Letrehozzuk a lovedekeket eltarolo tombot
		for(int i=0;i<4;i++)
			ac.beams[i]=null;
				
	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       int tempArray[] ={row,column};
	       if(temp.contains(System.getProperty("line.separator"))){ 
	    	   /*sortores eseten a palyan is uj sort kezdunk*/
	    	   row++;
	    	   column=0;
	       }
	       else{
	    	   /*egyebkent pedig a beolvasott betu alapjan hozzuk letre a megfelelo tipusu mezot*/
		       switch(temp.charAt(0)){
		       		case 'P':
		       			ac.visitables​[row][column]=new StarGate();
		       		 switch(temp.charAt(1)){
		       		 case 'b':
		       			((StarGate) ac.visitables​[row][column]).setColor("blue");
		       			ac.starGates​[0]=ac.visitables​[row][column]; //a 0 helyen taroljuk a kek portalt
		       			break;
		       		 case 'r':
		       			((StarGate) ac.visitables​[row][column]).setColor("red");
		       			ac.starGates​[1]=ac.visitables​[row][column]; //a 1 helyen taroljuk a piros portalt
		       			break;
		       		 case 'g':
		       			((StarGate) ac.visitables​[row][column]).setColor("green");
		       			ac.starGates​[2]=ac.visitables​[row][column]; //a 2 helyen taroljuk a zold portalt
		       			break;
		       		 case 'y':
		       			((StarGate) ac.visitables​[row][column]).setColor("yellow");
		       			ac.starGates​[3]=ac.visitables​[row][column]; //a 3 helyen taroljuk a sarga portalt
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
	    view.removeMouseListener(listener);
	    try {
			drawWalls();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    VT = new ViewThread();
	    viewthread = new Thread(VT);
	    viewthread.start();
	    ReplicatorThread RT = new ReplicatorThread();
	    Thread repthread = new Thread(RT);
	    repthread.start();
	}
		
	// Adott koordinatan levo mezot kirajzoljuk
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
	
	// Csak a falakat rajzoljuk ki, egyszer hivodik meg a jatek folyaman osszesen
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
	
	// Megjelenitessel foglalkozo szal hivogatja folyamatosan, megjeleniti a palyat
	private void Output() throws IOException{
		//O'Neil pozicioja
		int oRow;
		int oColumn;
		
		if(ac.getPlayer(0)!=null){
		oRow = ac.getPlayer(0).getRow();
		oColumn = ac.getPlayer(0).getColumn();		
		}else{
			oRow=-1;
			oColumn=-1;
		}
		//Jaffa pozicioja
		int jRow;
		int jColumn;
		
		if(ac.getPlayer(1)!=null){
		jRow = ac.getPlayer(1).getRow();
		jColumn = ac.getPlayer(1).getColumn();		
		}else{
			jRow=-1;
			jColumn=-1;
		}
		// Mezok kirajzolasa, ha visitor all rajta, nem bantjuk (nem rajzoljuk ki ujra)
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
	    		} else { 
	    			if(!ac.replicatorIsAlive)
	    				drawTile(i,j);
	    			else if (ac.getReplicatorX() != i || ac.getReplicatorY() != j)
	    				drawTile(i,j);
	    			end(false);
	    		}	    			    					
	    	}
	    }  
	    
	    //playerek, ha nem valtozik a jatekos iranya, nem rajzoljuk ki ujra a mezot, amin all
	    for(int i = 0; i < 2; i++){
	    	Player p = ac.getPlayer(i);
	    	if (p != null){
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
	    }
	    
	    //lovedekek
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
	    
	    //ha a replikator meg el
	    if(ac.replicatorIsAlive){
	    	RV.drawReplicator(ac.getReplicatorX(), ac.getReplicatorY());
	    }		
	}
	
	//ONeill iranyitasaert es mozgatasaert felelos programresz
	private void ONeill(String temp){		
		switch(temp){
		/*O'Neill ezredes mozgatasa*/
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
		/*Ezredes dobozfelvetele*/
		case "5":
			if(ac.players[0]!=null)
			ac.boxing(ac.players[0]);
			break;
		/*Ezredes loves*/
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
	
	//Jaffa iranyitasaert es mozgatasaert felelos programresz
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
		/*Jaffa dobozfelvetele*/
		case "r":
		if(ac.players[1]!=null)
		ac.boxing(ac.players[1]);
		break;
		/*Jaffa loves*/
		case "q":
		if(ac.players[1]!=null)
		ac.shoot(ac.players[1], "green");
		break;
		case "e":
		if(ac.players[1]!=null)
		ac.shoot(ac.players[1], "yellow");
		}
		
	}
	
	//jatek vegen meghivodo fuggveny
	public void end(boolean vic){
		view.end(vic);		
	}

	// Replicator mozgatasaert felel
	private class ReplicatorThread implements Runnable{

		@Override
		public void run() {
			while(ac.replicatorIsAlive){
				ac.moveReplicators();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	//Megjelenitesert felel
	private class ViewThread implements Runnable{

		boolean run = true;
		
		@Override
		public void run() {
			while(run){
				if(ac.vic){ //nyrtunk?
					end(true);
				}
				else{
					if (ac.players[0]==null && ac.players[1]==null){ //van meg jatekos?
						end(false);
					}
					else{
						try { //meg megy a jatek, kiirjuk a palyat
							ac.getMap();
							Output();						
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}							
		}
		
	}
	
	// Key listener, figyeli a billentyuzetet, jobban is lehetne implementalni, optimalizalasra szorul
	class MKeyListener extends KeyAdapter {

	    @Override
	    public void keyPressed(KeyEvent event) {
	    	String ch = "" + event.getKeyChar();	
	    	ONeill(ch);
	    	Jaffa(ch);
	    	try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}

	//egerlttuntast figyelo osztay
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