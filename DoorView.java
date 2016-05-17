package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorView {
	View view;
	public DoorView(View v){
		view = v;
	}
	public void drawDoor(int x, int y, Door door) throws IOException{
		Image img = null;
		if (door.isPassable())
			img = ImageIO.read(new File("src/projlab/map/open_door.png"));
		else
			img = ImageIO.read(new File("src/projlab/map/door_right.png"));		
		view.drawTile(x, y, img);
	}
}
