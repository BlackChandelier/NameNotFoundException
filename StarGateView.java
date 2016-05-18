package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StarGateView {
	View view; //view
	
	//view
	public StarGateView(View v){
		view = v;
	}
	
	//kirajzolas
	public void drawGate(int x, int y, StarGate gate) throws IOException{
		Image img = null; //alaphelyzetbe allitjuk
		switch(gate.getColor()){ //szin szerint betoltunk
			case "blue": img = ImageIO.read(new File("src/projlab/map/b_stargate.png")); break;
			case "red": img = ImageIO.read(new File("src/projlab/map/r_stargate.png")); break;
			case "green": img = ImageIO.read(new File("src/projlab/map/g_stargate.png")); break;
			case "yellow": img = ImageIO.read(new File("src/projlab/map/y_stargate.png")); break;
		}
		view.drawTile(x, y, img); //kirajzoljuk
	}
}
