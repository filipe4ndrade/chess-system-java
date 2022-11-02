package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.TabuleiroDoJogo;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Integer turno;
	private Cores jogadorAtual;
	private TabuleiroDoJogo tabuleiro;
	private boolean check;
	private boolean checkMate;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();;
	private List<Peca> pecasCapturadas = new ArrayList<>();;

	public PartidaDeXadrez() {
		tabuleiro = new TabuleiroDoJogo(8, 8);
		turno = 1;
		jogadorAtual = Cores.WHITE;
		iniciarPartida();
	}

	public Integer getTurno() {
		return turno;
	}

	public Cores getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

	public boolean[][] movimentosPossiveis(PosicaoNoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validacaoPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possibilidadeMovimentos();
	}

	public PecaDeXadrez excecutarMovimentoXadrez(PosicaoNoXadrez posicaoOrigem, PosicaoNoXadrez posicaoDestino) {

		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validacaoPosicaoOrigem(origem);
		validacaoPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMovimento(origem, destino);

		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Voce nao pode se colocar em xeque");
		}

		check = (testeCheck(oponente(jogadorAtual))) ? true : false;

		if (testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		p.adicionarContagemMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);

		}
		// Movimento Especial roque menor
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.lugarDaPeca(torre, destinoT);
			torre.adicionarContagemMovimento();
		}
		// Movimento Especial roque maior
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.lugarDaPeca(torre, destinoT);
			torre.adicionarContagemMovimento();
		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		p.diminuirContagemMovimento();
		tabuleiro.lugarDaPeca(p, origem);

		if (pecaCapturada != null) {
			tabuleiro.lugarDaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
		}

		// Movimento Especial roque menor
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarDaPeca(torre, origemT);
			torre.diminuirContagemMovimento();
		}
		// Movimento Especial roque maior
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarDaPeca(torre, origemT);
			torre.diminuirContagemMovimento();;
		}
	}

	

	private void validacaoPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.existePeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua");
		}
		if (!tabuleiro.peca(posicao).existeMovimentoPossivelPeca()) {
			throw new XadrezException("Nao existem movimentos possiveis para a peca escolhida");
		}
	}

	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possibilidadeMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}

	private void novoLugarPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoNoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);

	}

	private Cores oponente(Cores cor) {
		return (cor == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	private PecaDeXadrez rei(Cores cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("Nao existe o rei da cor " + cor + " no tabuleiro");
	}

	private boolean testeCheck(Cores cor) {
		Posicao posicaoRei = rei(cor).getPosicaoNoXadrez().toPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.possibilidadeMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeCheckMate(Cores cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possibilidadeMovimentos();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaDeXadrez) p).getPosicaoNoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cores.WHITE) ? Cores.BLACK : Cores.WHITE;
	}

	private void iniciarPartida() {

		novoLugarPeca('a', 1, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('b', 1, new Cavalo(tabuleiro, Cores.WHITE));
		novoLugarPeca('c', 1, new Bispo(tabuleiro, Cores.WHITE));
		novoLugarPeca('d', 1, new Rainha(tabuleiro, Cores.WHITE));
		novoLugarPeca('e', 1, new Rei(tabuleiro, Cores.WHITE, this));
		novoLugarPeca('f', 1, new Bispo(tabuleiro, Cores.WHITE));
		novoLugarPeca('g', 1, new Cavalo(tabuleiro, Cores.WHITE));
		novoLugarPeca('h', 1, new Torre(tabuleiro, Cores.WHITE));
		novoLugarPeca('a', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('b', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('c', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('d', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('e', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('f', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('g', 2, new Peao(tabuleiro, Cores.WHITE));
		novoLugarPeca('h', 2, new Peao(tabuleiro, Cores.WHITE));

		novoLugarPeca('a', 8, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('b', 8, new Cavalo(tabuleiro, Cores.BLACK));
		novoLugarPeca('c', 8, new Bispo(tabuleiro, Cores.BLACK));
		novoLugarPeca('d', 8, new Rainha(tabuleiro, Cores.BLACK));
		novoLugarPeca('e', 8, new Rei(tabuleiro, Cores.BLACK, this));
		novoLugarPeca('f', 8, new Bispo(tabuleiro, Cores.BLACK));
		novoLugarPeca('g', 8, new Cavalo(tabuleiro, Cores.BLACK));
		novoLugarPeca('h', 8, new Torre(tabuleiro, Cores.BLACK));
		novoLugarPeca('a', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('b', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('c', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('d', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('e', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('f', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('g', 7, new Peao(tabuleiro, Cores.BLACK));
		novoLugarPeca('h', 7, new Peao(tabuleiro, Cores.BLACK));
	}
}
