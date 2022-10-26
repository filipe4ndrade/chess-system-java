package application;

import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;
import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {
		
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		UI.printTabuleiro(partidaDeXadrez.getPecas());
		

	}

}
