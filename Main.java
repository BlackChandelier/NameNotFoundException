package projlab;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{	   
	   // System.out.print("Add meg a bet�ltend� map sz�m�t: ");
	    
	    Game game = new Game();
	    game.run();
	    game.play();
	}
}
