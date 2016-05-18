package projlab;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	    
	    Game game = new Game(); //letrehozunk egy gamet es elinditjuk a jatekot
	    game.run();	    
	}
}