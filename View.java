package projlab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import projlab.Game.MKeyListener;

public class View extends JFrame{

	private JPanel map;
	private JPanel menu;
	
	public void menu() throws IOException{
		this.setVisible(true);	
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//v�r 400ms-ot k�l�nben a rajzol�s nem m�k�dik
		// TODO gyorsabb g�pen ellen�rizni, hogy ott is fenn�ll-e a helyzet
		try{Thread.sleep(400);}
		catch(InterruptedException e){}
		menu = new JPanel();
		this.setSize(400, 400);
		menu.setSize(400, 400);
		this.add(menu);
		
		//a men�t fel�p�t� k�pek
		Image background = ImageIO.read(new File("src/projlab/menu/menu_bg.png"));
		Image onePlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_1player.png"));
		Image twoPlayerBtn = ImageIO.read(new File("src/projlab/menu/menu_2players.png"));
		Image customBtn = ImageIO.read(new File("src/projlab/menu/menu_custom.png"));
		
		menu.getGraphics().drawImage(background, 0, 0, null);
		menu.getGraphics().drawImage(onePlayerBtn, 97, 54, null);
		menu.getGraphics().drawImage(twoPlayerBtn, 97, 154, null);
		menu.getGraphics().drawImage(customBtn, 97, 254, null);
	}
	
	public void end(boolean vic){
		this.setSize(400, 400);		
		menu.setSize(400, 400);
		if(vic){
			Image background = null;
			try {
				background = ImageIO.read(new File("src/projlab/menu/victory.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu.getGraphics().drawImage(background, 0, 0, null);
		}
		else{
			Image background = null;
			try {
				background = ImageIO.read(new File("src/projlab/menu/defeat.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu.getGraphics().drawImage(background, 0, 0, null);
		}
	}
	
	public void setMap(int columns, int rows){
		map = new JPanel();
		this.setSize(40*(columns+2), 40*(rows+2));
		map.setSize(40*(columns+1), 40*(rows+1));
		this.add(map);
	}
	
	public void addMyKeyListener(MKeyListener kl){
		this.addKeyListener(kl);
	}
	
	public void drawTile(int y, int x, Image img)
	{		
		map.getGraphics()
			.drawImage(img, x*40, y*40, null);  
	}
}