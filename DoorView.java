package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorView {
	View view; //view
	
	//view
	public DoorView(View v){
		view = v;
	}
	
	//kirajzolo
	public void drawDoor(int x, int y, Door door) throws IOException{
		Image img = null;//alaphelyzetbe allitjuk
		if (door.isPassable()) //ha nyitott...
			img = ImageIO.read(new File("src/projlab/map/open_door.png"));
		else //ha zart...
			img = ImageIO.read(new File("src/projlab/map/door_right.png"));
		//megjelenitjuk
		view.drawTile(x, y, img);
	}
}