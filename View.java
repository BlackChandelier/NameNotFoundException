package projlab;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import projlab.Game.MKeyListener;

//a nezeteket osszefoglalo osztaly
@SuppressWarnings("serial")
public class View extends JFrame{

	private JPanel map;
	private JPanel menu;
	
	//menu megjelenitese
	public void menu() throws IOException{
		this.setVisible(true);	
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try{Thread.sleep(400);}
		catch(InterruptedException e){}
		menu = new JPanel();
		this.setSize(400, 400);
		menu.setSize(400, 400);
		this.setResizable(false);
		this.add(menu);
		
		//a menuben megjeleno kepek betoltese
		Image background = ImageIO.read(new File("src/projlab/menu/menu_bg.png"));
		Image onePlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_1player.png"));
		Image twoPlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_2players.png"));
		
		//megjelenites
		menu.getGraphics().drawImage(background, 0, 0, null);
		menu.getGraphics().drawImage(onePlayerBtn, 97, 54, null);
		menu.getGraphics().drawImage(twoPlayerBtn, 97, 154, null);
	}
	
	//vegso kepernyo
	public void end(boolean vic){
		this.setSize(400, 400);		
		menu.setSize(400, 400);
		if(vic){ //gyozelem
			Image background = null;
			try {
				background = ImageIO.read(new File("src/projlab/menu/victory.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			menu.getGraphics().drawImage(background, 0, 0, null);
		}
		else{ //vereseg
			Image background = null;
			try {
				background = ImageIO.read(new File("src/projlab/menu/defeat.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			menu.getGraphics().drawImage(background, 0, 0, null);
		}
	}
	
	//ablak beallitasa
	public void setMap(int columns, int rows){
		map = new JPanel();
		this.setSize(40*(columns+2), 40*(rows+2));
		map.setSize(40*(columns+1), 40*(rows+1));
		this.add(map);
	}
	
	public void addMyKeyListener(MKeyListener kl){
		this.addKeyListener(kl);
	}
	
	//palyelem rajzolasa
	public void drawTile(int y, int x, Image img)
	{		
		map.getGraphics()
			.drawImage(img, x*40, y*40, null);  
	}
}