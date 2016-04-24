package projlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {
	
	protected ActionController ac;

	public void run() throws FileNotFoundException{ //A játékot készíti elő. Létrehozza az ActionControllert.
	
		ac=new ActionController();

		int column=0;
		int row=0;
		
		Scanner scanner = new Scanner(new File("level1.csv"));
	    scanner.useDelimiter(",");
	    System.out.print(" ");  
	    
	    ac.rows=scanner.nextInt();
	    ac.columns=scanner.nextInt();
	    scanner.next();

		ac.visitables​=new Tile[ac.rows][ac.columns];

	    while(scanner.hasNext()){
	       String temp=scanner.next();
	       
	       if(temp.contains(System.getProperty("line.separator"))){
	    	   row++;
	    	   column=0;
	       }
	       else{
		       switch(temp.charAt(0)){
		       case 'W':
	       			ac.visitables​[row][column]=new Wall();
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			column++;
	       			break;
	       		case 'C':
	       			ac.visitables​[row][column]=new CleanTile();
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			column++;
	       			break;
	       		case 'B':
	       			ac.visitables​[row][column]=new BoxedTile();
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			column++;
	       			break;
	       		case 'O':
	       			ac.visitables​[row][column]=new CleanTile();
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			int tempArray[] ={row,column};
	       			ac.players[0]=new Player();
	       			ac.players[0].coordinates=tempArray;
	       			column++;
	       			break;
	       		case 'J':
	       			ac.visitables​[row][column]=new CleanTile();
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			int tempArray2[] ={row,column};
	       			ac.players[1]=new Player();
	       			ac.players[1].coordinates=tempArray2;
	       			column++;
	       			break;
	       		case 'D':
	       			Door tempDoor=new Door();
	       			tempDoor.doorID=Character.getNumericValue(temp.charAt(1));
	       			ac.visitables​[row][column]=tempDoor;
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			column++;
	       			break;
	       		case 'S':
	       			Scale tempScale=new Scale();
	       			tempScale.scaleID=Character.getNumericValue(temp.charAt(1));
	       			tempScale.setWeightLimit(Character.getNumericValue(temp.charAt(3)));
	       			ac.visitables​[row][column]=tempScale;
	       			ac.visitables​[row][column].coordinates[0]=row;
	       			ac.visitables​[row][column].coordinates[1]=column;
	       			column++;
	       			break;

		       }
	       }
	    }
	    scanner.close();
	    
	}
	public static void clear(){
		for(int i=0; i<1000; i++)
			System.out.println("");
	}
	
	public void play(){	//​Meghívásakor elindul a játék. Innentől kezdve az ActionController feladata a bemenetek kezelése.
		Scanner scanner = new Scanner(System.in);
		String input=" ";
	    do{
	    	
			int keypressed=-1;
			try{
				switch(input.charAt(0)){
					case '4':
						keypressed=4;
						break;
					case '7':
						keypressed=7;
						break;
					case '8':
						keypressed=8;
						break;
					case '9':
						keypressed=9;
						break;
					case '6':
						keypressed=6;
						break;
					case '3':
						keypressed=3;
						break;
					case '2':
						keypressed=2;
						break;
					case '1':
						keypressed=1;
						break;
					case '5':
						keypressed=5;
						break;
				}
				clear();
				if(keypressed==0)
					ac.shoot(ac.players[0], "BLUE");
				else if(keypressed!=5){
					ac.move(ac.players[0], keypressed);
				}else if(keypressed==5){
					ac.boxing(ac.players[0]);
				}
				ac.getMap();
				System.out.print("\nAdj meg egy parancsot: ");
			}catch(Exception e){
				System.out.println(e.getMessage().toString());
			}
		}while(!(input=scanner.next()).equals("exit"));
	    scanner.close();
	}
}


