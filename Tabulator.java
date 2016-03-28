package projlab;

/**
 * @author bori
 *
 */
public class Tabulator {
	private static int tabNumber=0; //kezdetben a meghívódó függvény elõtt nincs behúzás
	
	//a metódusokat a meghívódásuk sorrendje szerint húzza be
	public static void tabMethod(){
		for(int i=0;i<tabNumber;i++)
			System.out.print("\t");
	}
	
	//növeli a tabulátorok számát
	public static void increaseTabNumber(){
		tabNumber++;
	}
	
	//csökkenti a tabulátorok számát
	public static void decreaseTabNumber(){
		if(tabNumber!=0)
			tabNumber--;
	}
	
	//lenullázza a tabulátorok számát
	public static void resetTabNumber(){
		 tabNumber=0;
	 }
}
