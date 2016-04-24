package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
		
	public static void main(String[] args) throws IOException, FileNotFoundException{  
	    Game game = new Game();
	    game.run();
	    game.play();
	}
}