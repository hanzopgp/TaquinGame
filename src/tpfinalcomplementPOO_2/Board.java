package tpfinalcomplementPOO_2;

import java.util.ArrayList;
import java.util.Random;

public class Board extends AbstractModelEcoutable {

	//ATTRIBUTS
	int nbRows, nbCols;
	int[][] board;
	private boolean valid = false;
	private int caseToMove = -1;

	/**
	* Constructeur de la Board;
	* @param nbRows nombre de lignes;
	* @param nbCols nombres de colonnes;
	*/
	public Board(int nbRows, int nbCols) {
		this.nbRows = nbRows;
		this.nbCols = nbCols;
		this.board = new int[nbRows][nbCols];
		this.ecouteurs = new ArrayList<>();		
	}

	/**
	* Setter;
	* @param b la case valide;
	*/
	public void setValid(boolean b) {
		this.valid = b;
		this.moveChangement();
	}

	/**
	* Setter;
	* @param val la valeur de la case a bouger;
	*/
	public void setCaseToMove(int val) {
		this.caseToMove = val;
		this.moveChangement();
	}
	
	/**
	* Getter;
	* @return le nombre de lignes;
	*/
	public int getRows() {
		return this.nbRows;
	}
	/**
	* Getter;
	* @return le nombre de colonnes;
	*/
	public int getCols() {
		return this.nbCols;
	}
	/**
	* Getter;
	* @return la grille;
	*/
	public int[][] getBoard() {
		return this.board;
	}
	/**
	* Getter;
	* @return la grille;
	*/
	public boolean getValid() {
		return this.valid;
	}
	/**
	* Getter;
	* @return la valeur de la case a bouger;
	*/
	public int getCaseToMove() {
		return this.caseToMove;
	}

	//METHODES

	/**
	* Initialise la grille;
	*/
	public void initBoard() {
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				board[i][j] = (nbRows * i) + (j+1);
			}
		}
		board[nbRows - 1][nbCols - 1] = 0;
	}	

	/**
	* Affiche la grille;
	*/
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

	/**
	* Copie la grille actuelle;
	* @return la grille copie;
	*/
	public int[][] copyBoard(){
		int[][] winBoard = new int[nbRows][nbCols];
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				winBoard[i][j] = board[i][j];
			}
		}
		return winBoard;
	}

	/**
	* Recupère la liste des coups valides de la grille;
	* @return validMoves les coups valides de la grille;
	*/
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

	/**
	* Recupère la liste des cases voisines;
	* @return neighbors liste des cases voisines;
	*/
	public ArrayList<Integer> getNeighbors() {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for(int i = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				if(board[i][j] == 0) {
					if(i+1 < nbRows) {
						neighbors.add(board[i+1][j]);
					}
					if(j+1 < nbCols) {
						neighbors.add(board[i][j+1]);
					}
					if(i-1 >= 0) {
						neighbors.add(board[i-1][j]);
					}
					if(j-1 >= 0) {
						neighbors.add(board[i][j-1]);
					}	
				}
			}
		}
		return neighbors;
	}

	/**
	* Joue sur la grille;
	* @param str le coup a effectuer;
	*/
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

	/**
	* Met a jour la grille;
	* @param str type de coup;
	*/
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

	/**
	* Melange la grille;
	* @param level le niveau de difficulte choisit;
	*/
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

	/**
	* Condition de fin de partie;
	* @return booleen suivant si la partie est finie;
	*/
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
	
	/**
	* Met a jour suivant le move effectue;
	*/
	@Override 
	public void moveChangement () {
		for(EcouteurModel ecm : this.ecouteurs) {
			ecm.ModelMiseAjour(this);
		}
	}

}
