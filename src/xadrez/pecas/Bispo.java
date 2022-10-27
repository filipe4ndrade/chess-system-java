package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez {

	public Bispo(TabuleiroDoJogo tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibilidadeMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// Noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha()-1, p.getColuna()-1);
			
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValores(posicao.getLinha()-1, posicao.getColuna() +1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha()-1, p.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValores(posicao.getLinha()+1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha()+1, p.getColuna()+1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Sudoeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
		while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha()+1, p.getColuna()-1);
		}
		if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
