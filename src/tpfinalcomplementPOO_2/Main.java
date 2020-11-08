package tpfinalcomplementPOO_2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Main{

	//ATTRIBUTS
	static int nbRows;
	static int nbCols;
	static int[][] winBoard;

	/**
	* Methode main;
	* @param args liste des arguments;
	*/
	public static void main(String[] args) throws IOException{
		
		nbRows = 3;
		nbCols = 3;
		Path currentDir = Paths.get(".");				
		System.out.println("Current directory in Main : " + currentDir.toAbsolutePath());
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir le niveau ( de 1 a 10 )");
		int level = Integer.parseInt(sc.nextLine());
		System.out.println("La taille de base du Taquin est de 3 par 3, voulez vous changer la taille ? ( 0/1 )");
		int choseN = Integer.parseInt(sc.nextLine());
		if(choseN == 1){
			System.out.println("Choisir la taille de la grille");
			nbRows = Integer.parseInt(sc.nextLine());
			nbCols = nbRows;
		}
		System.out.println("Affichage graphique ( 0/1 ) ?");
		int utilisationGUI = Integer.parseInt(sc.nextLine());
		
		Board b = new Board(nbRows, nbCols); //CREATION OBJET BOARD
		b.initBoard(); //INITIALISATION DE LA BOARD
		
		winBoard = b.copyBoard(); //COPIE DE LA BOARD POUR AVOIR LA BOARD DE BASE
		b.shuffleBoard(level); //ON MELANGE LA BOARD
		
		if(utilisationGUI == 1){
			FrameBoard win = new FrameBoard(b);
			win.printBoard(b);
			win.setLocationRelativeTo(null);
		}		
		else{
			while(!b.isOver()) { //TANT QU'ON A PAS TROUVE LA GRILLE GAGNANTE
				b.displayBoard();
				System.out.println("Vous pouvez jouer les coups suivants : " + b.getValidMoves()); //PRINT LES COUPS VALIDES
				String move = sc.nextLine(); //CHOIX DU COUP
				System.out.println("Vous avez choisit le coup : " + move);
				b.play(move); //ON JOUE LE COUP CHOISIT
				b.displayBoard();
			
			}
			System.out.println("BRAVO !!!");
		}

		sc.close();
	}

}
