package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZPMView {
	View view; //view
	Image img; //kep
	
	//view
	public ZPMView(View v){
		view = v;
	}
	
	//kirajzolas
	public void drawZPM(int x, int y) throws IOException{
		if (img == null) //ha meg nincs betoltve, betoltjuk
			img = ImageIO.read(new File("src/projlab/map/ZPM.png"));
				
		view.drawTile(x, y, img); //kirajzoljuk
	}
}
