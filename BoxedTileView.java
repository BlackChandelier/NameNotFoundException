package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BoxedTileView {
	View view;
	Image img;
	public BoxedTileView(View v){
		view = v;
	}
	public void drawBoxedTile(int x, int y) throws IOException{
		if (img == null)
			img = ImageIO.read(new File("src/projlab/map/box.png"));
				
		view.drawTile(x, y, img);
	}
}