package tpfinalcomplementPOO_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	private Board board;
	private int val;
	private xImage img;

	/**
	* Constructeur View;
	* @param b la grille de jeu;
	* @param val numero de la case;
	* @param img image de la case;
	*/
	public View(Board b, int val, xImage img) {
		this.board = b;
		this.val = val;
		this.img = img;
	}

	/**
	* Override de la methode paintComponent;
	* @param g objet Graphics;
	*/
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, 600/this.board.getCols(), 600/this.board.getRows());

		//NUMEROTE LA CASE
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 60)); //DEFINI LA POLICE
		g.setColor(Color.RED);
		String strLabel; 
		Image imgfinal;
		if(val == 0) {
			strLabel = "";
			imgfinal = null;
		}	
		else {
			strLabel = String.valueOf(val); // CONVERSION EN STRING
			imgfinal = this.img.getImage();
		}	
		g.drawImage(imgfinal, 0, 0, getWidth(), getHeight(), this);
		//g.drawString(strLabel, (600/this.board.getCols())/2-10, (600/this.board.getCols())/2+10); //"DESSINE" LE NUMERO

		// AFFICHE UN CADRE VERT LORQUE LA CASE EST VOISIN DE 0
		if(this.board.getValid()) {
			if(board.getNeighbors().contains(val) && board.getCaseToMove() == -1 || val == 0 && board.getCaseToMove() != -1) {
				g.fillRect(0, 0, 600/this.board.getCols(), 10); // EN HAUT
				g.fillRect(0, 0, 10, 600/this.board.getRows()); // COTE GAUCHE
				g.fillRect(0, 600/this.board.getCols()-10, 600/this.board.getCols(), 10); // EN BAS 
				g.fillRect(600/this.board.getRows()-10, 0, 10, 600/this.board.getRows()); // COTE DROIT 
			}
		}
	}

}
