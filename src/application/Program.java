package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoNoXadrez origem = UI.lerPosicaoNoXadrez(sc);
			
			System.out.println("Destino: ");
			PosicaoNoXadrez destino = UI.lerPosicaoNoXadrez(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.excecutarMovimentoXadrez(origem, destino);
		}

	}

}
