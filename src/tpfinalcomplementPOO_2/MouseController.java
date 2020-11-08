package tpfinalcomplementPOO_2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {

	private Board b;
	
	/**
	* Constructeur MouseController;
	* @param b la grille de jeu;
	*/
	MouseController(Board b){
		this.b = b;
	}
	
	/**
	* Agit suivant le click de la souris;
	* @param e la souris;
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				// PERMET DE RETROUVER LA VALEUR DE LA CASE SELON SES COORDONNEES 
				if(e.getY() < 600/b.getRows()*(i+1)+10 && e.getY() > 600/b.getRows()*i+10 && e.getX() < 600/b.getRows()*(j+1)+10 && e.getX() > 600/b.getRows()*j + 10) {
					if(b.getBoard()[i][j] == 0) { // SI LA CASE CORRESPOND A LA VALEUR 0 (case vide)
						if(i+1 < b.getRows() && b.getCaseToMove() == b.getBoard()[i+1][j]) // VERIFIE SI b.getCaseToMove() EST LE VOISIN DU DESSOUS
							b.moveElement("u");
						if(i-1 >= 0 && b.getCaseToMove() == b.getBoard()[i-1][j]) // VERIFIE SI b.getCaseToMove() EST LE VOISIN DU DESSUS
							b.moveElement("d");
						if(j+1 < b.getCols() && b.getCaseToMove() == b.getBoard()[i][j+1]) // VERIFIE SI b.getCaseToMove() EST LE VOISIN DE DROITE
							b.moveElement("l");
						if(j-1 >= 0 && b.getCaseToMove() == b.getBoard()[i][j-1]) // VERIFIE SI b.getCaseToMove() EST LE VOISIN DE GAUCHE
							b.moveElement("r");
						b.setCaseToMove(-1); // RETIRE LA VALEUR DE LA CASE A BOUGER
					}
					else if(b.getNeighbors().contains(b.getBoard()[i][j])) 
						b.setCaseToMove(b.getBoard()[i][j]); // INDIQUE LA VALEUR DE LA CASE A BOUGER
				}
			}
		}
	}

	/**
	* Agit suivant la position de la souris (entrant);
	* @param e la souris;
	*/
	@Override
	public void mouseEntered(MouseEvent e) {
		this.b.setValid(true);
	}

	/**
	* Agit suivant la position de la souris (sortant);
	* @param e la souris;
	*/
	@Override
	public void mouseExited(MouseEvent e) {
		this.b.setValid(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
