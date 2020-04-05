package tpfinalcomplementPOO_2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

public class xImage {

	private static Image image;
	private static BufferedImage bufferedImage;
	private int x,y,dx,dy,sx,sy;
	public static int size;

	/**
	* Constructeur xImage;
	* @param x position x;
	* @param y position y;
	* @param dx longueur;
	* @param dy largeur;
	* @param sx decalage x;
	* @param sy decalage y;
	*/
	public xImage(int x, int y, int dx, int dy, int sx, int sy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.sx = sx;
		this.sy = sy;
	}

	/**
	* Initialise l'image;
	*/
	public void initImage() {
		Random r = new Random();
		int index = r.nextInt(3)+1;
		System.out.println("IMAGE NUMERO : " + index + " CHARGEE");
		xImage.image = new ImageIcon("images/img"+index+".jpg").getImage();
		bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		size = image.getWidth(null);
	}

	/**
	* Recuperer l'image decoupee;
	* @return bi l'image;
	*/
	public Image getImage(){
		BufferedImage bi = bufferedImage.getSubimage(x, y, dx, dy);
		bi.createGraphics().drawImage(image, x-sx, y-sy, null);
		return bi;
	}

}
