package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpecialWallView {
	View view;
	Image img;
	public SpecialWallView(View v){
		view = v;
	}
	public void drawSpecialWall(int x, int y) throws IOException{
		if (img == null)
			img = ImageIO.read(new File("src/projlab/map/specialwall.png"));
				
		view.drawTile(x, y, img);
	}
}