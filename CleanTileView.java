package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CleanTileView {
	View view;
	static Image img;
	public CleanTileView(View v){
		view = v;
	}
	public void drawCleanTile(int x, int y) throws IOException{
		if(img == null)
			img = ImageIO.read(new File("src/projlab/map/cleantile.png"));
				
		view.drawTile(x, y, img);
	}
}