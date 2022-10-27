package xadrez;

import tabuleiro.Peca;
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

	public PecaDeXadrez excecutarMovimentoXadrez(PosicaoNoXadrez posicaoOrigem, PosicaoNoXadrez posicaoDestino) {

		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validacaoPosicaoOrigem(origem);
		validacaoPosicaoDestino(origem,destino);
		Peca pecaCapturada = fazerMovimento(origem, destino);
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(p, destino);
		return pecaCapturada;
	}

	private void validacaoPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao de origem");
		}
		if(!tabuleiro.peca(posicao).existeMovimentoPossivelPeca()) {
			throw new XadrezException("Nao existem movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possibilidadeMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
		}
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
