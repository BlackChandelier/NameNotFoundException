package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BoxedTileView {
	View view; //view
	Image img; //kep
	
	//view
	public BoxedTileView(View v){
		view = v;
	}
	
	//kirajzolo
	public void drawBoxedTile(int x, int y) throws IOException{
		if (img == null) //ha meg nem toltottuk volna be...
			img = ImageIO.read(new File("src/projlab/map/box.png"));
				
		view.drawTile(x, y, img); //egyebkent kirajzoljuk
	}
}