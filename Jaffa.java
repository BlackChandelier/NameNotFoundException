package projlab;

public class Jaffa implements Visitor {

	private String name; //​Játékos neve
	private int direction; //​A játékos irányát tárolja.
	private Boolean hasBox; //​Azt jegyzi, hogy van a felhasználónál doboz, vagy nincs.
	private Tile tile; //Azt a Tile­t tárolja, amelyiken a felhasználó karaktere éppen áll.
	private int collectedZPMs; //​Azért felel, hogy az összegyűjtött elemeket ZPMeket számontartsa.
	
	public static Visitable getVisitable() { // megadja, hogy mi a következő
												// mező

		return null;
	}

	public void visit(Visitable visitable) { // meglátogatja az adott mezőt

	}

	public static Boolean getBox() { // megmondja hány doboz van a játékosnál

		return null;

	}

	public static void setVisitable(Visitable visitable) { // beállítja a
															// következő mezőt

	}

	public static void changeBox() { // megváltoztatja, hogy van-e épp doboz a
										// játékosnál

	}

	public static void addZPM() { // hozzáad egyet a játékosnál lévő ZPM-ekhez

	}

}