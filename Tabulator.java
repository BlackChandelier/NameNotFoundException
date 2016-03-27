package projlab;

/**
 * @author bori
 *
 */
public class Tabulator {
	private int tabNumber=0; //kezdetben a meghívódó függvény elõtt nincs behúzás
	
	//a metódusokat a meghívódásuk sorrendje szerint húzza be
	public void tabMethod(){
		for(int i=0;i<tabNumber;i++)
			System.out.print("\t");
	}
	
	//növeli a tabulátorok számát
	public void increaseTabNumber(){
		tabNumber++;
	}
	
	//csökkenti a tabulátorok számát
	public void decreaseTabNumber(){
		tabNumber--;
	}
	
	//lenullázza a tabulátorok számát
	public void resetTabNumber(){
		 tabNumber=0;
	 }
}
