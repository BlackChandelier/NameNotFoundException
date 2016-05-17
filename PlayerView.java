package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerView {
	View view;	
	Image img1 = null;
	Image img2 = null;
	public PlayerView(View v){
		view = v;
	}
	public void drawPlayer(int x, int y, boolean o, int direction) throws IOException{		
		if(o){			
			switch(direction){
			case 0: img1 = ImageIO.read(new File("src/projlab/map/Oneill_left.png")); break; 
			case 1: img1 = ImageIO.read(new File("src/projlab/map/Oneill_up.png")); break; 
			case 2: img1 = ImageIO.read(new File("src/projlab/map/Oneill_right.png")); break;
			case 3: img1 = ImageIO.read(new File("src/projlab/map/Oneill_down.png")); break; 
			default: break;
			}			
				view.drawTile(x, y, img1);
		}				
		else{
			switch(direction){
			case 0: img2 = ImageIO.read(new File("src/projlab/map/jaffa_left.png")); break; 
			case 1: img2 = ImageIO.read(new File("src/projlab/map/jaffa_up.png")); break; 
			case 2: img2 = ImageIO.read(new File("src/projlab/map/jaffa_right.png")); break;
			case 3: img2 = ImageIO.read(new File("src/projlab/map/jaffa_down.png")); break; 
			default: break;
			}			
				view.drawTile(x, y, img2);
		}
	}
}