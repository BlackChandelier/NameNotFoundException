package projlab;

/**
 * @author bori
 *
 */
public class Tabulator {
	private int tabNumber=0; //kezdetben a megh�v�d� f�ggv�ny el�tt nincs beh�z�s
	
	//a met�dusokat a megh�v�d�suk sorrendje szerint h�zza be
	public void tabMethod(){
		for(int i=0;i<tabNumber;i++)
			System.out.print("\t");
	}
	
	//n�veli a tabul�torok sz�m�t
	public void increaseTabNumber(){
		tabNumber++;
	}
	
	//cs�kkenti a tabul�torok sz�m�t
	public void decreaseTabNumber(){
		tabNumber--;
	}
	
	//lenull�zza a tabul�torok sz�m�t
	public void resetTabNumber(){
		 tabNumber=0;
	 }
}
