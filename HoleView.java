package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HoleView {
	View view;
	public HoleView(View v){
		view = v;
	}
	public void drawHole(int x, int y) throws IOException{
		Image img = ImageIO.read(new File("src/projlab/map/Hole.png"));
				
		view.drawTile(x, y, img);
	}
}
