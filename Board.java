package tpfinalcomplementPOO_2;

import java.util.ArrayList;
import java.util.Random;

public class Board extends AbstractModelEcoutable {

	//ATTRIBUTS
	int nbRows, nbCols;
	int[][] board;

	//CONSTRUCTEURS
	public Board(int nbRows, int nbCols) {
		this.nbRows = nbRows;
		this.nbCols = nbCols;
		this.board = new int[nbRows][nbCols];
		this.ecouteurs = new ArrayList<>();		
	}
	
	//ACCESSEURS
	public int getRows() {
		return this.nbRows;
	}
	public int getCols() {
		return this.nbCols;
	}
	public int[][] getBoard() {
		return this.board;
	}

	//METHODES
	//INITIALISE LA BOARD
	public void initBoard() {
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				board[i][j] = (nbRows * i) + (j+1);
			}
		}
		board[nbRows - 1][nbCols - 1] = 0;
	}	

	//AFFICHAGE LA BOARD
	public void displayBoard() {
		System.out.println();
		for(int i = 0; i < nbRows; i++) {
			System.out.print("| ");
			for(int j = 0; j < nbCols; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//COPIE UNE BOARD
	public int[][] copyBoard(){
		int[][] winBoard = new int[nbRows][nbCols];
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				winBoard[i][j] = board[i][j];
			}
		}
		return winBoard;
	}

	//GET LES COUPS VALIDES
	public ArrayList<String> getValidMoves() {
		ArrayList<String> validMoves = new ArrayList<String>();
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				if(board[i][j] == 0) {
					if(i+1 < nbCols) {
						validMoves.add("u");
					}
					if(i-1 >= 0) {
						validMoves.add("d");
					}
					if(j+1 < nbRows) {
						validMoves.add("l");
					}		
					if(j-1 >= 0) {
						validMoves.add("r");
					}
				}
			}
		}
		return validMoves;
	}

	//DEPLACE L'ELEMENT DE VALEUR a SUIVANT LE DEPLACEMENT str
	public void moveElement(String str) {
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				if(board[i][j] == 0) {
					int tmp = board[i][j];
					if(str == "u") {	
						board[i][j] = board[i+1][j]; 
						board[i+1][j] = tmp;
						str = "";
					}
					else if(str == "d") { 
						board[i][j] = board[i-1][j];
						board[i-1][j] = tmp;
						str = "";
					}
					else if(str == "l") {
						board[i][j] = board[i][j+1];
						board[i][j+1] = tmp;
						str = "";
					}
					else if(str == "r") {
						board[i][j] = board[i][j-1];
						board[i][j-1] = tmp;
						str = "";
					}
				}
			}	
		}
		moveChangement(); // MISE A JOUR DU BOARD APRES MOVE
	}

	//UPDATE LA GRILLE SUIVANT LE MOVE str
	public void play(String str) {
		ArrayList<String> validMoves = this.getValidMoves();
		if(str.equals("u") && validMoves.contains("u")) {
			this.moveElement("u");
		}
		if(str.equals("d") && validMoves.contains("d")) {
			this.moveElement("d"); 
		}
		if(str.equals("l") && validMoves.contains("l")) {
			this.moveElement("l");
		}
		if(str.equals("r") && validMoves.contains("r")) {
			this.moveElement("r");
		}
	}

	//MELANGE LA GRILLE
	public void shuffleBoard(int level) {
		Random r = new Random();
		int val;
		String str;
		for(int shfl = 0; shfl < level*7; shfl++) {	
			ArrayList<String> validMoves = getValidMoves();
			val = r.nextInt(validMoves.size());
			str = validMoves.get(val);
			play(str);
		}
	}

	//CONDITION DE FIN DE PARTIE
	public boolean isOver() {
		int[][] winBoard = Main.winBoard;
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				if(winBoard[i][j] != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override 
	public void moveChangement () {
		for(EcouteurModel ecm : this.ecouteurs) {
			ecm.ModelMiseAjour(this);
		}
	}

}
