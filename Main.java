package projlab;

import java.io.IOException;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException {
	    Scanner scanner = new Scanner(System.in);
	    int useCaseID;
		
	    /*Miután lefutott a kiválasztott use-case újra kiírjuk a menüt
	      Egészen addig amíg a felhasználó egy nullával ki nem lép.*/
		do{
			System.out.println("Use-case-ek:" 
					+ "\n1.Mozgás"
					+ "\n2.Doboz felvétele"
					+ "\n3.Doboz lerakása"
					+ "\n4.Ajtó kinyitása/bezárása"
					+ "\n5.Lövés"
					+ "\n6.Csillagkapu nyitás"
					+ "\n7.ZPM"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
			
			useCaseID=scanner.nextInt(); //beolvassuk a kiválasztott use-case számát

			switch(useCaseID){
			case 0:
				break;
			case 1:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseMozgas(scanner); //meghívódnak a mozgással kapcsolatos további use-case-ek
				break;	
			case 2:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseDobozFel(scanner); //meghívódnak a doboz felvétellel kapcsolatos további use-case-ek
			break;
			case 3:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseDobozLe(scanner); //meghívódnak a doboz lerakással kapcsolatos további use-case-ek
				break;
			case 4:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseAjtoMuveletek(scanner); //meghívódnak az ajtónyitással/csukással kapcsolatos use-case-ek
				break;			
			case 5:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseLoves(scanner);
				break;
			case 6:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				caseCsillagkapu(scanner); //meghívódnak a csillagkapu nyitással kapcsolatos use-case-ek
				break;
			case 7:
				Tabulator.resetTabNumber(); //lenullázzuk a tabulátorok számát
				System.out.println("\n8. ZPM"
						+ "\n\t7.1 Felvétel"
						+ "\n\t7.2 Lerakás");
				break;
			default:
				Tabulator.resetTabNumber();
				System.out.println("\n\nNincs ilyen számú use-case. Próbáld újra!\n\n");
				break;
			}
			
			System.in.read(); //enterre várunk mielõtt újra megjelenne a menü
			
			}while(useCaseID!=0); //menü kiírásának ismétlése ha a use-case száma nem 0
	}

	private static void caseCsillagkapu(Scanner scanner) {
		int subID;
		System.out.println("\n7.Csillagkapu nyitás"
				+ "\n\t7.1 Sárga"
				+ "\n\t7.2 Kék");
		subID=scanner.nextInt();
		switch(subID){
		case 1:
			System.out.println("\n7.Csillagkapu nyitás"
					+ "\n\t7.1 Sárga"
					+ "\n\t\t7.1.1 Elsõ"
					+ "\n\t\t7.1.2 Van már"
					+ "\n\t\t7.1.3 Féregjárat"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
					subID=scanner.nextInt();
					ActionController.shoot(new Player(),"sárga");
					switch(subID){
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.out.println("\nNincs ilyen almenüpont!");
					}
					
			break;
		case 2:
			System.out.println("\n7.Csillagkapu nyitás"
					+ "\n\t7.2 Kék"
					+ "\n\t\t7.2.1 Elsõ"
					+ "\n\t\t7.2.2 Van már"
					+ "\n\t\t7.2.3 Féregjárat"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
					subID=scanner.nextInt();
					ActionController.shoot(new Player(),"kék");
					switch(subID){
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						System.out.println("\nNincs ilyen almenüpont!");
					}
			break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
		}
	}

	private static void caseLoves(Scanner scanner) {
		int subID;
		System.out.println("\n5.Lövés"
				+ "\n\t5.1 Szakadék felett"
				+ "\n\t5.2 Falra"
				+ "\n\t5.3 Dobozra"
				+ "\n\t5.4 Speciális falra"
				+ "\n\t5.5 Nyitott ajtón keresztül"
				+ "\n\nÍrd be a kiválasztott use-case számát:");
		
		subID=scanner.nextInt();
		ActionController.getNextTile(new Tile(),1); //megnézzük mi a következõ mezõ
		Tabulator.increaseTabNumber();
		switch(subID){
		case 1: //szakadék felett
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a lövedék továbbrepül a szakadék felett
			break;
		case 2: //falra
			break;
		case 3: //dobozra
			 new Tile().accept("PortalBeam");
			 new PortalBeam().visit(new Tile());  //a lövedék továbbrepül a doboz felett
			break;
		case 4: //speciális falra
			new Tile().accept("PortalBeam");
			new PortalBeam().visit(new Tile());
			ActionController.changeGates(null, null);  //a lövedék új csillagkaput állít be
			break;
		case 5: //nyitott ajtón
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a lövedék továbbrepül a nyitott ajtón
			break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
		}
	}

	private static void caseAjtoMuveletek(Scanner scanner) {
		int subID;
		System.out.println("\n4.Ajtó kinyitása/bezárása"
				+ "\n\t4.1 Saját súly"
				+ "\n\t4.2 Doboz");
		subID=scanner.nextInt();
		switch(subID){
		case 1: //saját súly
			System.out.println("\n4.Ajtó kinyitása/bezárása"
					+ "\n\t4.1 Saját súly"
					+ "\n\t\t4.1.1 Nyitás"
					+ "\n\t\t4.1.2 Bezárás"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
			
			subID=scanner.nextInt();
			Player.getVisitable();
			Tabulator.increaseTabNumber();
			new Player().visit(new Tile());
			Scale.setWeight();
			Tabulator.increaseTabNumber();
			break;
		case 2: //doboz
			System.out.println("\n4.Ajtó kinyitása/bezárása"
					+ "\n\t4.2 Doboz"
					+ "\n\t\t4.2.1 Nyitás"
					+ "\n\t\t4.2.2 Bezárás"
					+ "\n\nÍrd be a kiválasztott use-case számát:");
					subID=scanner.nextInt();
					switch(subID){
					case 1: //nyitás
						Player.changeBox();
						Tabulator.increaseTabNumber();
						ActionController.changeVisitable(new Tile(),new Tile());
						System.out.print("(currentCleanTile,boxedTile)\n");
						break;
					case 2: //bezárás
						Player.changeBox();
						Tabulator.increaseTabNumber();
						ActionController.changeVisitable(new Tile(),new Tile());
						System.out.print("(currentBoxedTile,cleanTile)\n");
						break;
					default:
						System.out.println("\nNincs ilyen almenüpont!");
					}
					Tabulator.tabMethod();
					System.out.println("<- void");
					Scale.setWeight();
					Tabulator.increaseTabNumber();
			break;
		}
	}

	private static void caseDobozLe(Scanner scanner) {
		int subID;
		System.out.println("\n3.Doboz lerakása"
				+ "\n\t3.1 Van a játékosnál doboz"
				+ "\n\t3.2 Nincs a játékosnál doboz"
				+ "\n\nAdd meg a kiválasztott almenüpont számát:");
		subID=scanner.nextInt(); //beolvassuk a kiválasztott use-case számát
		switch(subID){
		case 1: //Ha van a játékosnál doboz
			System.out.println("\n3.Doboz lerakása"
					+ "\n\t3.1 Van a játékosnál doboz"
					+ "\n\t\t3.1.1 Üres mezõre"
					+ "\n\t\t3.1.2 Szakadékra"
					+ "\n\t\t3.1.3 Falra"
					+ "\n\t\t3.1.4 Zárt ajtóra"
					+ "\n\t\t3.1.5 Nyitott ajtóra"
			+ "\n\nAdd meg a kiválasztott almenüpont számát:");
			subID=scanner.nextInt();
			Player.getBox();
			System.out.println("<- true"); //megtudjuk, hogy van a felhasználónál doboz
			Tabulator.increaseTabNumber();
			ActionController.getNextTile(new Tile(),1); //megnézzük milyen típusú a következõ mezõ
			
			switch(subID){
			case 1: //üres mezõre
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				Tabulator.increaseTabNumber();
				ActionController.changeVisitable(new Tile(),new Tile());
				System.out.print("(nextTile,boxedTile)\n");
				Tabulator.tabMethod();
				System.out.println("<- void");
				break;
			case 2: //szakadékra
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				break;
			case 3: //falra
				break;
			case 4: //zárt ajtóra
				break;
			case 5: //nyitott ajtóra
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				Tabulator.increaseTabNumber();
				ActionController.changeVisitable(new Tile(),new Tile());
				break;
			default:
				System.out.println("\nNincs ilyen almenüpont!");
			}
		
			break;
		case 2: //Ha nincs a játékosnál doboz
			Player.getBox();
			System.out.println("<- false"); //megtudjuk, hogy nincs a játékosnál doboz, így nem tudjuk lerakni
			break;
		}
	}

	private static void caseDobozFel(Scanner scanner) {
		int subID;
		System.out.println("\n2. Doboz felvétele"
				+ "\n\t2.1 Van a játékosnál doboz"		
				+ "\n\t2.2 Nincs a játékosnál doboz"
				+ "\n\nAdd meg a kiválasztott almenüpont számát:");
		subID=scanner.nextInt(); //beolvassuk a kiválasztott use-case számát
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
			Player.getVisitable(); //megkérdezzük melyik a következõ mezõ, ami felé néz
			//new Player().visit(new Tile()); 
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
			//new Player().visit(new Tile());
			Tabulator.decreaseTabNumber();
			switch(subID){
			case 1: //van doboz
				System.out.println("<- true");
				Tabulator.increaseTabNumber();
				Player.changeBox();
				ActionController.changeVisitable(new Tile(),new Tile());
				System.out.print("(nextTile,cleanTile)\n");
				Tabulator.tabMethod();
				System.out.println("<- void");
				break;
			case 2: //nincs doboz
				System.out.println("<- false");
				break;
			default:
				System.out.println("\nNincs ilyen almenüpont!");
			}
			break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
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
		subID=scanner.nextInt(); //beolvassuk a kiválasztott use-case számát
		switch(subID){
		case 1:
		    ActionController.move(new Player(),0); //üres mezõre lép
			break;
		case 2:
		    ActionController.move(new Player(),1); //szakadékra lép
			break;
		case 3:
		    ActionController.move(new Player(),2); //falra próbál lépni
			break;
		case 4:
		    ActionController.move(new Player(),3); //zárt ajtóra próbál lépni
		    break;
		case 5:
		    ActionController.move(new Player(),4); //nyílt ajtóra lép
		    break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
		}
	}
}
