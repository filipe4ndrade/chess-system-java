package xadrez;

import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private TabuleiroDoJogo tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new TabuleiroDoJogo(8, 8);
		iniciarPartida();
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void iniciarPartida() {
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cores.WHITE), new Posicao(7,0));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro , Cores.WHITE),new Posicao(0,4));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro , Cores.WHITE),new Posicao(7,4));
	}
}
