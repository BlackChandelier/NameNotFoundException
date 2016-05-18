package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CleanTileView {
	View view; //view
	static Image img; //kep
	
	//view
	public CleanTileView(View v){
		view = v;
	}
	
	//kirajzolas
	public void drawCleanTile(int x, int y) throws IOException{
		if(img == null) //ha nem toltottuk volna be, most betoltjuk
			img = ImageIO.read(new File("src/projlab/map/cleantile.png"));
				
		view.drawTile(x, y, img); //kirajzoljuk
	}
}