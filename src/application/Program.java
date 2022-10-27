package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;
import xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try{ UI.limparTela();
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println("\n");
			System.out.print("Origem: ");
			PosicaoNoXadrez origem = UI.lerPosicaoNoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoNoXadrez destino = UI.lerPosicaoNoXadrez(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.excecutarMovimentoXadrez(origem, destino);
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		

	}

}
