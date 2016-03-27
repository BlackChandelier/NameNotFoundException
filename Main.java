package projlab;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int useCaseID;

		
		do{
			System.out.println("Use-case-ek:" 
					+ "\n1.Mozg�s"
					+ "\n2.Doboz felv�tele"
					+ "\n3.Doboz lerak�sa"
					+ "\n4.Ajt� kinyit�sa/bez�r�sa"
					+ "\n5.K�zleked�s ajt�n"
					+ "\n6.L�v�s"
					+ "\n7.Csillagkapu nyit�s"
					+ "\n8.ZPM"
					+ "\n\n�rd be a kiv�lasztott use-case sz�m�t:");
			
			useCaseID=scanner.nextInt();

			switch(useCaseID){
			case 1:
				caseMozgas(scanner);
				break;	
			case 2:
				System.out.println("\n2. Doboz felv�tele"
						+ "\n\t2.1 Van a j�t�kosn�l doboz"		
						+ "\n\t2.2 Nincs a j�t�kosn�l doboz");
				break;		
			case 3:
				System.out.println("\n3.Doboz lerak�sa"
						+ "\n\t3.1 Van a j�t�kosn�l doboz"
						+ "\n\t3.2 Nincs a j�t�kosn�l doboz");
				break;	
			case 4:
				System.out.println("\n4.Ajt� kinyit�sa/bez�r�sa"
						+ "\n\t4.1 Saj�t s�ly"
						+ "\n\t4.2 Doboz");
				break;
			case 5:
				System.out.println("\n5.K�zleked�s ajt�n"
						+ "\n\t5.1 Z�rt"
						+ "\n\t5.2 Nyitott");
				break;
			case 6:
				System.out.println("\n6.L�v�s"
						+ "\n\t6.1 Szakad�k felett"
						+ "\n\t6.2 Falra"
						+ "\n\t6.3 Dobozra"
						+ "\n\t6.4 Speci�lis falra"
						+ "\n\t6.5 Nyitott ajt�n kereszt�l");
				break;
			case 7:
				System.out.println("\n7.Csillagkapu nyit�s"
						+ "\n\t7.1 S�rga"
						+ "\n\t7.2 K�k");
				break;
			case 8:
				System.out.println("\n8. ZPM"
						+ "\n\t7.1 Felv�tel"
						+ "\n\t7.2 Lerak�s");
				break;
			default:
				System.out.println("\n\nNincs ilyen sz�m� use-case. Pr�b�ld �jra!\n\n");
				useCaseID=0;
				break;
			}
		}while(useCaseID==0);
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
		subID=scanner.nextInt();
		switch(subID){
		case 1:
			System.out.println("\nrandom");
			break;
		default:
			System.out.println("\nNincs ilyen almen�pont!");
		}
	}
}
