package application;

import xadrez.PecaDeXadrez;

public class UI {

	public static void printTabuleiro(PecaDeXadrez[][] pecas) {
		for(int i = 0; i<pecas.length; i++) {
			System.out.print((8-i)+" ");
			for(int j = 0; j<pecas.length; j++) {
				printPeca(pecas[i][j]);
			}
			System.out.println();
			
		}
		System.out.print("  a b c d e f g h");
	}
	
	private static void printPeca(PecaDeXadrez pecas) {
		if(pecas == null) {
			System.out.print("-");
		}else {
			System.out.print(pecas);
		}
		System.out.print(" ");
	}
}
