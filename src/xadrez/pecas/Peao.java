package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;
import xadrez.Cores;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

	public Peao(TabuleiroDoJogo tabuleiro, Cores cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] possibilidadeMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cores.WHITE) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)
					&& getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existePeca(p2)
					&& getContagemMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			p.setValores(posicao.getLinha()+1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().existePeca(p)
					&& getTabuleiro().posicaoExistente(p2) && !getTabuleiro().existePeca(p2)
					&& getContagemMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
