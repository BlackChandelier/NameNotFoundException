package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScaleView {
	View view;
	public ScaleView(View v){
		view = v;
	}
	public void drawScale(int x, int y, Scale scale) throws IOException{
		Image img = null;
		if (scale.hasBox)
			img = ImageIO.read(new File("src/projlab/map/scale_with_box.png"));
		else
			img = ImageIO.read(new File("src/projlab/map/scale.png"));		
		view.drawTile(x, y, img);
	}
}
