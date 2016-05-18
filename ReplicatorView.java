package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReplicatorView {
	View view; //view	
	static Image img; //kep
	
	//view
	public ReplicatorView(View v){
		view = v;
	}
	
	//kirajzolas
	public void drawReplicator(int x, int y) throws IOException{
		if(img == null) //ha incs meg betoltve, betoltjuk
			img = ImageIO.read(new File("src/projlab/map/replicator_up.png"));			
		view.drawTile(x, y, img); //kirajzoljuk
	}
}