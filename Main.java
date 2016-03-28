package projlab;

import java.io.IOException;
import java.util.Scanner;


public class Main {
		
	public static void main(String[] args) throws IOException {
	    Scanner scanner = new Scanner(System.in);
	    int useCaseID;
		
		do{
			System.out.println("Use-case-ek:" 
					+ "\n1.Mozgás"
					+ "\n2.Doboz felvétele"
					+ "\n3.Doboz lerakása"
					+ "\n4.Ajtó kinyitása/bezárása"
					+ "\n5.Közlekedés ajtón"
					+ "\n6.Lövés"
					+ "\n7.Csillagkapu nyitás"
					+ "\n8.ZPM"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
			
			useCaseID=scanner.nextInt();

			switch(useCaseID){
			case 1:
				caseMozgas(scanner);
				break;	
			case 2:
				caseDobozFel(scanner);
			break;
			case 3:
				caseDobozLe(scanner);
				break;
			case 4:
				System.out.println("\n4.Ajtó kinyitása/bezárása"
						+ "\n\t4.1 Saját súly"
						+ "\n\t4.2 Doboz");
				break;
			case 5:
				System.out.println("\n5.Közlekedés ajtón"
						+ "\n\t5.1 Zárt"
						+ "\n\t5.2 Nyitott");
				break;
			case 6:
				System.out.println("\n6.Lövés"
						+ "\n\t6.1 Szakadék felett"
						+ "\n\t6.2 Falra"
						+ "\n\t6.3 Dobozra"
						+ "\n\t6.4 Speciális falra"
						+ "\n\t6.5 Nyitott ajtón keresztül");
				break;
			case 7:
				System.out.println("\n7.Csillagkapu nyitás"
						+ "\n\t7.1 Sárga"
						+ "\n\t7.2 Kék");
				break;
			case 8:
				System.out.println("\n8. ZPM"
						+ "\n\t7.1 Felvétel"
						+ "\n\t7.2 Lerakás");
				break;
			default:
				System.out.println("\n\nNincs ilyen számú use-case. Próbáld újra!\n\n");
				break;
			}
			System.in.read();
			}while(useCaseID!=0);
	}

	private static void caseDobozLe(Scanner scanner) {
		int subID;
		System.out.println("\n3.Doboz lerakása"
				+ "\n\t3.1 Van a játékosnál doboz"
				+ "\n\t3.2 Nincs a játékosnál doboz"
		+ "\n\nAdd meg a kiválasztott almenüpont számát:");
		subID=scanner.nextInt();
		switch(subID){
		case 1:
			System.out.println("\n3.Doboz lerakása"
					+ "\n\t3.1 Van a játékosnál doboz"
					+ "\n\t\t3.1.1 Szakadékra"
					+ "\n\t\t3.1.2 Falra"
					+ "\n\t\t3.1.3 Zárt ajtóra"
					+ "\n\t\t3.1.4 Nyitott ajtóra"
			+ "\n\nAdd meg a kiválasztott almenüpont számát:");
			subID=scanner.nextInt();
			Player.getBox();
			System.out.println("<- true");
			ActionController.getNextTile(new Tile(),1);
			Tabulator.increaseTabNumber();
			Player.changeBox();
			
			switch(subID){
			case 1:
								
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			}
			Tabulator.decreaseTabNumber();
			System.out.println("<- void");
			break;
		case 2:
			break;
		}
	}

	private static void caseDobozFel(Scanner scanner) {
		int subID;
		System.out.println("\n2. Doboz felvétele"
				+ "\n\t2.1 Van a játékosnál doboz"		
				+ "\n\t2.2 Nincs a játékosnál doboz"
				+ "\n\nAdd meg a kiválasztott almenüpont számát:");
		subID=scanner.nextInt();
		switch(subID){
		case 1:
			System.out.println("\n2. Doboz felvétele"
					+ "\n\t2.1 Van a játékosnál doboz"
					+ "\n\t\t2.1.1 Van doboz a mezõn"		
					+ "\n\t\t2.1.2 Nincs doboz a mezõn"
					+ "\n\nAdd meg a kiválasztott almenüpont számát:");
			
			subID=scanner.nextInt();
			Player.getBox();
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Player().visit(new Tile());
			Tabulator.decreaseTabNumber();
			System.out.println("<- false");
			break;
			
		case 2:
			System.out.println("\n2. Doboz felvétele"
					+ "\n\t2.2 Nincs a játékosnál doboz"
					+ "\n\t\t2.2.1 Van doboz a mezõn"		
					+ "\n\t\t2.2.2 Nincs doboz a mezõn"
					+ "\n\nAdd meg a kiválasztott almenüpont számát:");
			subID=scanner.nextInt();
			Player.getBox();
			Tabulator.increaseTabNumber();
			Player.getVisitable();
			new Player().visit(new Tile());
			Tabulator.decreaseTabNumber();
			switch(subID){
			case 1:
				System.out.println("<- true");
				break;
			case 2:
				System.out.println("<- false");
				break;
			}
			break;
		}
	}

	private static void caseMozgas(Scanner scanner) {
		int subID;
		System.out.println("\n1.Mozgás"
				+ "\n\t1.1 Üres mezõre"
				+ "\n\t1.2 Szakadékra"
				+ "\n\t1.3 Falra"
				+ "\n\t1.4 Zárt ajtóra"
				+ "\n\t1.5 Nyitott ajtóra"
				+ "\n\nAdd meg a kiválasztott almenüpont számát:");
		subID=scanner.nextInt();
		switch(subID){
		case 1:
		    ActionController.move(new Player(),0);
			break;
		case 2:
		    ActionController.move(new Player(),1);
			break;
		case 3:
		    ActionController.move(new Player(),2);
			break;
		case 4:
		    ActionController.move(new Player(),3);
		    break;
		case 5:
		    ActionController.move(new Player(),4);
		    break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
		}
	}
}
