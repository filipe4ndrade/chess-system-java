package tabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private TabuleiroDoJogo tabuleiro;

	public Peca(TabuleiroDoJogo tabuleiro) {

		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected TabuleiroDoJogo getTabuleiro() {
		return tabuleiro;
	}

	

}
