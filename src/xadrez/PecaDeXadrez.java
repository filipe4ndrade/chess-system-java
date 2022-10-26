package xadrez;

import tabuleiro.Peca;
import tabuleiro.TabuleiroDoJogo;

public class PecaDeXadrez extends Peca{

	private Cores cor;

	public PecaDeXadrez(TabuleiroDoJogo tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cores getCor() {
		return cor;
	}

	
	
}
