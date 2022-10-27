package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;

public abstract class PecaDeXadrez extends Peca {

	private Cores cor;
	private int contagemMovimento;

	public PecaDeXadrez(TabuleiroDoJogo tabuleiro, Cores cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cores getCor() {
		return cor;
	}
	
	public int getContagemMovimento() {
		return contagemMovimento;
	}

	public void adicionarContagemMovimento() {
		contagemMovimento++;
	}

	public void diminuirContagemMovimento() {
		contagemMovimento--;
	}

	public PosicaoNoXadrez getPosicaoNoXadrez() {
		return PosicaoNoXadrez.daPosicao(posicao);
	}

	protected boolean existePecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
