package projet_j;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static String[][] board = { { ".", ".", "." }, { ".", ".", "." }, { ".", ".", "." } };
	public static Scanner scan = new Scanner(System.in);
	public static int numero_tour = 0;
	Random r = new Random();

	public static String turn = "O";

	// fonction permettant de connaitre la personne jouant
	public static String tour(int nb_tour) {
		return nb_tour % 2 == 0 ? "X" : "O";
	}

	public static void main(String[] args) {
		// initialisation du premier tour
		display();
		System.out.println("c'est au tour du joueur ayant les " + tour(numero_tour) + " de jouer");
		while (numero_tour < 9 && !checkwin()) // add checkwin
		{
			if (tour(numero_tour).equals("X")) {
				play();
				display();
			} else {
				/* bot(); */
				bot_v2();
				display();
			}
		}
		System.out.println("la partie est terminée");
		if (checkwin()) {
			System.out.println("le joueur ayant les " + tour(numero_tour + 1) + " a gagné"); // numero tour est indexé à
																								// +1 pour cohérence
		} else {
			System.out.println("match nul");
		}

	}

	public static char symboleDe(int j) {
		final char[] symboles = { '.', 'X', 'O' };
		return symboles[j];
	}

	/**
	 * 
	 * Fontion qui affiche le tableau
	 */
	public static void display() {
		System.out.println("Bienvenue au jeu du morpion !");
		System.out.println("------------------------------");
		System.out.print("Joueur 1 = " + symboleDe(1) + "     ");
		System.out.print("Joueur 2 = " + symboleDe(2) + "     ");
		System.out.print("\n");
		System.out.println("x y  0 1 2");
		System.out.println("-----------");
		for (int i = 0; i < 3; i++) {
			System.out.println(i + "|  |" + board[i][0] + "|" + board[i][1] + "|" + board[i][2] + "|");
			System.out.println("    -------");
		}
	}

	// jouer un signe sur le tableau
	public static boolean checkwin() {
		for (int i = 0; i < board.length; i++) {
			if (!board[0][i].equals(".") && board[0][i] == board[1][i] && board[0][i] == board[2][i]
					|| !board[i][0].equals(".") && board[i][0] == board[i][1] && board[i][0] == board[i][2]) { // numero
																												// tour
																												// est
				// indexé à +1 pour
				// cohérence
				return true;
			}
		}
		if ((!board[1][1].equals(".")) && (board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] == board[1][1] && board[1][1] == board[2][0])) {

			return true;
		}

		return false;
	}

	public static void play() {
		int x = -1;
		int y = -1;

		boolean test = true;
		while (x == -1 || y == -1) {
			System.out.print("Quelle " + (x == -1 ? "ligne" : "colonne") + " ?:");
			int abscisse = scan.nextInt();
			if (abscisse < 0 || abscisse > 2) {
				System.out.println("entrer une valeur correct");
			} else {
				if (x == -1) {
					x = abscisse;
				} else {
					y = abscisse;
				}
			}
		}
		if (board[x][y].equals(".")) {
			board[x][y] = tour(numero_tour);
			numero_tour = numero_tour + 1;
		} else {
			System.out.println("la case a déjà été joué");
			play();
		}
	}

	public static void bot() {
		int x = -1;
		int y = -1;

		boolean test = true;
		while (x == -1 || y == -1) {
			Random r = new Random();
			int abscisse = r.nextInt(3);
			if (abscisse < 0 || abscisse > 2) {
			} else {
				if (x == -1) {
					x = abscisse;
				} else {
					y = abscisse;
				}
			}
		}
		if (board[x][y].equals(".")) {
			board[x][y] = tour(numero_tour);
			numero_tour = numero_tour + 1;
		} else {
			bot();
		}
	}

	public static void bot_v2() {
		Random r = new Random();
		int Index = r.nextInt(9 - numero_tour);// random parmis le nombre de tour restant
		System.out.println("l'index est: " + Index);
		int x = 0;
		int y = 0;

		/*for (int j = 0; j < board.length; j++) {
			for (int j2 = 0; j2 < board.length; j2++) {
				if (board[j][j2].equals(".")) {
					i++;
					if (i == Index) {
						board[j][j2] = tour(numero_tour);
						numero_tour = numero_tour + 1;
						return;
					}
				}
			}
		}*/

		while (Index > 0 || !board[x][y].equals(".")) {
			if (board[x][y].equals(".")) {
				Index--;
			}
			x = (x + 1) % board.length;
			if (x % board.length == 0) {
				y = (y + 1)%board.length;
			}
		}
		board[x][y] = tour(numero_tour);
		numero_tour = numero_tour + 1;
	}



}
