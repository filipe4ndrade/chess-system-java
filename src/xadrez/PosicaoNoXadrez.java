package xadrez;

import tabuleiro.Posicao;

public class PosicaoNoXadrez {

	private Character coluna;
	private Integer linha;

	public PosicaoNoXadrez(Character coluna, Integer linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro ao instanciar PosicaoNoXadrez: valores válidos são de a1 até h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public Character getColuna() {
		return coluna;
	}

	public Integer getLinha() {
		return linha;
	}
	
	protected Posicao toPosicao() {
		return new Posicao(8-linha,coluna - 'a');
	}
	protected static PosicaoNoXadrez daPosicao(Posicao posicao) {
		return new PosicaoNoXadrez((char)('a'- posicao.getColuna()),8 - posicao.getLinha());
	}
	@Override
	public String toString() {
		return ""+coluna+linha;
	}

}
