package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WallView {
	View view; //view
	static Image img; //kep
	
	//view
	public WallView(View v){
		view = v;
	}
	
	//kirajzolas
	public void drawWall(int x, int y) throws IOException{
		if(img == null) //ha meg nincs betoltve, betoltjuk
			img = ImageIO.read(new File("src/projlab/map/Wall.png"));				
		view.drawTile(x, y, img); //kirajzoljuk
	}
}
