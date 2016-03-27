package projlab;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
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
				System.out.println("\n2. Doboz felvétele"
						+ "\n\t2.1 Van a játékosnál doboz"		
						+ "\n\t2.2 Nincs a játékosnál doboz");
				break;		
			case 3:
				System.out.println("\n3.Doboz lerakása"
						+ "\n\t3.1 Van a játékosnál doboz"
						+ "\n\t3.2 Nincs a játékosnál doboz");
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
				useCaseID=0;
				break;
			}
		}while(useCaseID==0);
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
			System.out.println("\nrandom");
			break;
		default:
			System.out.println("\nNincs ilyen almenüpont!");
		}
	}
}
