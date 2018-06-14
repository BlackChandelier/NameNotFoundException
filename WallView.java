package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WallView {
	View view;
	static Image img;
	public WallView(View v){
		view = v;
	}
	public void drawWall(int x, int y) throws IOException{
		if(img == null)
			img = ImageIO.read(new File("src/projlab/map/Wall.png"));				
		view.drawTile(x, y, img);
	}
}
