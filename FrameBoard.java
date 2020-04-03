package tpfinalcomplementPOO_2;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FrameBoard extends JFrame implements EcouteurModel {

	private static final long serialVersionUID = 1L;
	
	private View view;
	private Board board;
	private Controller controller;
	private ArrayList<xImage> imgTab;
	
	public FrameBoard(Board b) {
		super("Taquin");
		
		this.board = b;
		board.ajoutEcouteur(this);
		
		//On ajoute le controller au frame pour le rendre controllable 
		this.controller = new Controller(board);
		this.addKeyListener(this.controller);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(this.board.getRows(), this.board.getCols()));
		this.setVisible(true);
		
		//Tableau des images
		imgTab = new ArrayList<xImage>();
		int rows = this.board.getRows();
		int cols = this.board.getCols();
		
		//Initialisation
		xImage xIm = new xImage(0,0,0,0,0,0);
		xIm.initImage();
		imgTab.add(xIm);
		int t = xImage.size;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				imgTab.add(new xImage(j*t/rows, i*t/cols, t/rows, t/cols, 2*j*(t/rows), 2*i*(t/cols)));
			}
		}
		
		
	} 
	
	public void printBoard(Board b) {
		this.getContentPane().removeAll();	
		for(int i = 0; i < b.getRows(); i++) {
			for(int j = 0; j < b.getCols(); j++) {
				//CREER UNE CASE
				this.view = new View(b, b.getBoard()[i][j], imgTab.get(b.getBoard()[i][j]));
				this.view.setPreferredSize(new Dimension(600/this.board.getCols(), 600/this.board.getRows()));
				//AJOUTE LE TOUT A LA FENETRE
				this.getContentPane().add(view);
			}
		}
	
		this.pack(); // REDIMENSIONNE LA FENETRE SELON LA DIMENSION DE SES ELEMENTS
	}
	
	@Override
	public void ModelMiseAjour(Object source) {
		this.printBoard(this.board);// REDESSINE AVEC LA GRILLE A JOUR
		if(this.board.isOver()) {
			System.out.println("BRAVO !!!");
			System.exit(-1);
		}
	}
	
}
