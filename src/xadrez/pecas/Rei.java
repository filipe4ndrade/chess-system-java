package xadrez.pecas;

import tabuleiro.TabuleiroDoJogo;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {

	public Rei(TabuleiroDoJogo tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	
}