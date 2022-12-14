package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;
import xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
		while (!partidaDeXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.printPartida(partidaDeXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoNoXadrez origem = UI.lerPosicaoNoXadrez(sc);

				boolean[][] possibilidadeMovimento = partidaDeXadrez.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possibilidadeMovimento);
				System.out.println("\n");
				System.out.print("Destino: ");
				PosicaoNoXadrez destino = UI.lerPosicaoNoXadrez(sc);

				PecaDeXadrez pecaCapturada = partidaDeXadrez.excecutarMovimentoXadrez(origem, destino);

				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
			} catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
		}
		
		UI.limparTela();
		UI.printPartida(partidaDeXadrez, capturada);

	}

}
