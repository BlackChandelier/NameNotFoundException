package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReplicatorView {
	View view;	
	static Image img;
	public ReplicatorView(View v){
		view = v;
	}
	public void drawReplicator(int x, int y) throws IOException{
		if(img == null)
			img = ImageIO.read(new File("src/projlab/map/replicator_up.png"));			
		view.drawTile(x, y, img);
	}
}