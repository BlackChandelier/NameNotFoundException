package projlab;

import java.io.IOException;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException {
	    Scanner scanner = new Scanner(System.in);
	    int useCaseID;
		
	    /*Miut�n lefutott a kiv�lasztott use-case �jra ki�rjuk a men�t
	      Eg�szen addig am�g a felhaszn�l� egy null�val ki nem l�p.*/
		do{
			System.out.println("Use-case-ek:" 
					+ "\n1.Mozg�s"
					+ "\n2.Doboz felv�tele"
					+ "\n3.Doboz lerak�sa"
					+ "\n4.Ajt� kinyit�sa/bez�r�sa"
					+ "\n5.L�v�s"
					+ "\n6.Csillagkapu nyit�s"
					+ "\n7.ZPM felv�tel"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
			
			useCaseID=scanner.nextInt(); //beolvassuk a kiv�lasztott use-case sz�m�t

			switch(useCaseID){
			case 0:
				break;
			case 1:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseMozgas(scanner); //megh�v�dnak a mozg�ssal kapcsolatos tov�bbi use-case-ek
				break;	
			case 2:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseDobozFel(scanner); //megh�v�dnak a doboz felv�tellel kapcsolatos tov�bbi use-case-ek
			break;
			case 3:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseDobozLe(scanner); //megh�v�dnak a doboz lerak�ssal kapcsolatos tov�bbi use-case-ek
				break;
			case 4:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseAjtoMuveletek(scanner); //megh�v�dnak az ajt�nyit�ssal/csuk�ssal kapcsolatos use-case-ek
				break;			
			case 5:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseLoves(scanner);
				break;
			case 6:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseCsillagkapu(scanner); //megh�v�dnak a csillagkapu nyit�ssal kapcsolatos use-case-ek
				break;
			case 7:
				Tabulator.resetTabNumber(); //lenull�zzuk a tabul�torok sz�m�t
				caseZPM();//megh�v�dnak a ZPM felv�tellel kapcsolatos use-case-ek
				break;
			default:
				Tabulator.resetTabNumber();
				System.out.println("\n\nNincs ilyen sz�m� use-case. Pr�b�ld �jra!\n\n");
				break;
			}
			
			System.in.read(); //enterre v�runk miel�tt �jra megjelenne a men�
			
			}while(useCaseID!=0); //men� ki�r�s�nak ism�tl�se ha a use-case sz�ma nem 0
	}

	private static void caseZPM() {
		ActionController.move(new Player(), 0);
		Player.addZPM();
	}

	private static void caseCsillagkapu(Scanner scanner) {
		int subID;
		System.out.println("\n6.Csillagkapu nyit�s"
				+ "\n\t6.1 S�rga"
				+ "\n\t6.2 K�k");
		subID=scanner.nextInt();
		switch(subID){
		case 1:
			System.out.println("\n6.Csillagkapu nyit�s"
					+ "\n\t6.1 S�rga"
					+ "\n\t\t6.1.1 Els�"
					+ "\n\t\t6.1.2 Van m�r"
					//+ "\n\t\t6.1.3 F�regj�rat"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
					subID=scanner.nextInt();
					ActionController.shoot(new Player(),"s�rga");
					switch(subID){
					case 1:
						break;
					case 2:
						break;
					//case 3:
					//	break;
					default:
						System.out.println("\nNincs ilyen almen�pont!");
					}
					
			break;
		case 2:
			System.out.println("\n6.Csillagkapu nyit�s"
					+ "\n\t6.2 K�k"
					+ "\n\t\t6.2.1 Els�"
					+ "\n\t\t6.2.2 Van m�r"
					//+ "\n\t\t6.2.3 F�regj�rat"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
					subID=scanner.nextInt();
					ActionController.shoot(new Player(),"k�k");
					switch(subID){
					case 1:
						break;
					case 2:
						break;
					//case 3:
					//	break;
					default:
						System.out.println("\nNincs ilyen almen�pont!");
					}
			break;
		default:
			System.out.println("\nNincs ilyen almen�pont!");
		}
	}

	private static void caseLoves(Scanner scanner) {
		int subID;
		System.out.println("\n5.L�v�s"
				+ "\n\t5.1 Szakad�k felett"
				+ "\n\t5.2 Falra"
				+ "\n\t5.3 Dobozra"
				+ "\n\t5.4 Speci�lis falra"
				+ "\n\t5.5 Nyitott ajt�n kereszt�l"
				+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
		
		subID=scanner.nextInt();
		ActionController.getNextTile(new Tile(),1); //megn�zz�k mi a k�vetkez� mez�
		Tabulator.increaseTabNumber();
		switch(subID){
		case 1: //szakad�k felett
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a l�ved�k tov�bbrep�l a szakad�k felett
			break;
		case 2: //falra
			break;
		case 3: //dobozra
			 new Tile().accept("PortalBeam");
			 new PortalBeam().visit(new Tile());  //a l�ved�k tov�bbrep�l a doboz felett
			break;
		case 4: //speci�lis falra
			new Tile().accept("PortalBeam");
			new PortalBeam().visit(new Tile());
			ActionController.changeGates(null, null);  //a l�ved�k �j csillagkaput �ll�t be
			break;
		case 5: //nyitott ajt�n
			 new Tile().accept("PortalBeam"); 
			 new PortalBeam().visit(new Tile()); //a l�ved�k tov�bbrep�l a nyitott ajt�n
			break;
		default:
			System.out.println("\nNincs ilyen almen�pont!");
		}
	}

	private static void caseAjtoMuveletek(Scanner scanner) {
		int subID;
		System.out.println("\n4.Ajt� kinyit�sa/bez�r�sa"
				+ "\n\t4.1 Saj�t s�ly"
				+ "\n\t4.2 Doboz");
		subID=scanner.nextInt();
		switch(subID){
		case 1: //saj�t s�ly
			System.out.println("\n4.Ajt� kinyit�sa/bez�r�sa"
					+ "\n\t4.1 Saj�t s�ly"
					+ "\n\t\t4.1.1 Nyit�s"
					+ "\n\t\t4.1.2 Bez�r�s"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
			
			subID=scanner.nextInt();
			Player.getVisitable();
			Tabulator.increaseTabNumber();
			new Player().visit(new Tile());
			Scale.setWeight();
			Tabulator.increaseTabNumber();
			break;
		case 2: //doboz
			System.out.println("\n4.Ajt� kinyit�sa/bez�r�sa"
					+ "\n\t4.2 Doboz"
					+ "\n\t\t4.2.1 Nyit�s"
					+ "\n\t\t4.2.2 Bez�r�s"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
					subID=scanner.nextInt();
					switch(subID){
					case 1: //nyit�s
						Player.changeBox();
						Tabulator.increaseTabNumber();
						ActionController.changeVisitable(new Tile(),new Tile());
						System.out.print("(currentCleanTile,boxedTile)\n");
						break;
					case 2: //bez�r�s
						Player.changeBox();
						Tabulator.increaseTabNumber();
						ActionController.changeVisitable(new Tile(),new Tile());
						System.out.print("(currentBoxedTile,cleanTile)\n");
						break;
					default:
						System.out.println("\nNincs ilyen almen�pont!");
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
		System.out.println("\n3.Doboz lerak�sa"
				+ "\n\t3.1 Van a j�t�kosn�l doboz"
				+ "\n\t3.2 Nincs a j�t�kosn�l doboz"
				+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
		subID=scanner.nextInt(); //beolvassuk a kiv�lasztott use-case sz�m�t
		switch(subID){
		case 1: //Ha van a j�t�kosn�l doboz
			System.out.println("\n3.Doboz lerak�sa"
					+ "\n\t3.1 Van a j�t�kosn�l doboz"
					+ "\n\t\t3.1.1 �res mez�re"
					+ "\n\t\t3.1.2 Szakad�kra"
					+ "\n\t\t3.1.3 Falra"
					+ "\n\t\t3.1.4 Z�rt ajt�ra"
					+ "\n\t\t3.1.5 Nyitott ajt�ra"
			+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
			subID=scanner.nextInt();
			Player.getBox();
			System.out.println("<- true"); //megtudjuk, hogy van a felhaszn�l�n�l doboz
			Tabulator.increaseTabNumber();
			ActionController.getNextTile(new Tile(),1); //megn�zz�k milyen t�pus� a k�vetkez� mez�
			
			switch(subID){
			case 1: //�res mez�re
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				Tabulator.increaseTabNumber();
				ActionController.changeVisitable(new Tile(),new Tile());
				System.out.print("(nextTile,boxedTile)\n");
				Tabulator.tabMethod();
				System.out.println("<- void");
				break;
			case 2: //szakad�kra
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				break;
			case 3: //falra
				break;
			case 4: //z�rt ajt�ra
				break;
			case 5: //nyitott ajt�ra
				Tabulator.decreaseTabNumber();
				Player.changeBox();
				Tabulator.increaseTabNumber();
				ActionController.changeVisitable(new Tile(),new Tile());
				break;
			default:
				System.out.println("\nNincs ilyen almen�pont!");
			}
		
			break;
		case 2: //Ha nincs a j�t�kosn�l doboz
			Player.getBox();
			System.out.println("<- false"); //megtudjuk, hogy nincs a j�t�kosn�l doboz, �gy nem tudjuk lerakni
			break;
		}
	}

	private static void caseDobozFel(Scanner scanner) {
		int subID;
		System.out.println("\n2. Doboz felv�tele"
				+ "\n\t2.1 Van a j�t�kosn�l doboz"		
				+ "\n\t2.2 Nincs a j�t�kosn�l doboz"
				+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
		subID=scanner.nextInt(); //beolvassuk a kiv�lasztott use-case sz�m�t
		switch(subID){
		case 1:
			System.out.println("\n2. Doboz felv�tele"
					+ "\n\t2.1 Van a j�t�kosn�l doboz"
					+ "\n\t\t2.1.1 Van doboz a mez�n"		
					+ "\n\t\t2.1.2 Nincs doboz a mez�n"
					+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
			
			subID=scanner.nextInt();
			Player.getBox();
			Tabulator.increaseTabNumber();
			Player.getVisitable(); //megk�rdezz�k melyik a k�vetkez� mez�, ami fel� n�z
			//new Player().visit(new Tile()); 
			Tabulator.decreaseTabNumber();
			System.out.println("<- false");
			break;
			
		case 2:
			System.out.println("\n2. Doboz felv�tele"
					+ "\n\t2.2 Nincs a j�t�kosn�l doboz"
					+ "\n\t\t2.2.1 Van doboz a mez�n"		
					+ "\n\t\t2.2.2 Nincs doboz a mez�n"
					+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
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
				System.out.println("\nNincs ilyen almen�pont!");
			}
			break;
		default:
			System.out.println("\nNincs ilyen almen�pont!");
		}
	}

	private static void caseMozgas(Scanner scanner) {
		int subID;
		System.out.println("\n1.Mozg�s"
				+ "\n\t1.1 �res mez�re"
				+ "\n\t1.2 Szakad�kra"
				+ "\n\t1.3 Falra"
				+ "\n\t1.4 Z�rt ajt�ra"
				+ "\n\t1.5 Nyitott ajt�ra"
				+ "\n\nAdd meg a kiv�lasztott almen�pont sz�m�t:");
		subID=scanner.nextInt(); //beolvassuk a kiv�lasztott use-case sz�m�t
		switch(subID){
		case 1:
		    caseZPM();
			break;
		case 2:
		    ActionController.move(new Player(),1); //szakad�kra l�p
			break;
		case 3:
		    ActionController.move(new Player(),2); //falra pr�b�l l�pni
			break;
		case 4:
		    ActionController.move(new Player(),3); //z�rt ajt�ra pr�b�l l�pni
		    break;
		case 5:
		    ActionController.move(new Player(),4); //ny�lt ajt�ra l�p
		    break;
		default:
			System.out.println("\nNincs ilyen almen�pont!");
		}
	}
}
