package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PortalBeamView {
	View view;
	Image img;
	public PortalBeamView(View v){
		view = v;
	}
	public void drawBeam(int x, int y, String color, int direction) throws IOException{
		if(img == null){
			
		}
		boolean hor = direction % 2 == 0;
		switch(color){
		case "red": 
			if(hor)
				img = ImageIO.read(new File("src/projlab/map/r_beam_hor.png"));
			else
				img = ImageIO.read(new File("src/projlab/map/r_beam_up.png")); break;
		case "green": 
			if(hor)
				img = ImageIO.read(new File("src/projlab/map/g_beam_hor.png"));
			else
				img = ImageIO.read(new File("src/projlab/map/g_beam_up.png")); break;
		case "yellow": 
			if(hor)
				img = ImageIO.read(new File("src/projlab/map/y_beam_hor.png"));
			else
				img = ImageIO.read(new File("src/projlab/map/y_beam_up.png")); break;
		case "blue": 
			if(hor)
				img = ImageIO.read(new File("src/projlab/map/b_beam_hor.png"));
			else
				img = ImageIO.read(new File("src/projlab/map/b_beam_up.png")); break;
		}	
		view.drawTile(x, y, img);
	}
		
}