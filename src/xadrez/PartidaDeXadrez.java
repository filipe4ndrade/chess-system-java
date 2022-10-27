package xadrez;

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

	private void novoLugarPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoNoXadrez(coluna, linha).toPosicao());

	}

	private void iniciarPartida() {
		
		novoLugarPeca('c', 1, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('c', 2, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('d', 2, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('e', 2, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('e', 1, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('d', 1, new Rei(tabuleiro, Cores.WHITE));

		novoLugarPeca('c', 7, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('c', 8, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('d', 7, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('e', 7, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('e', 8, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('d', 8, new Rei(tabuleiro, Cores.BLACK));
	}
}
