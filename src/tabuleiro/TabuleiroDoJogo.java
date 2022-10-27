package tabuleiro;

public class TabuleiroDoJogo {

	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas;

	public TabuleiroDoJogo(Integer linhas, Integer colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException(
					"Erro ao criar tabuleiro: E necessario que haja pelo menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public Peca peca(int linhas, int colunas) {
		if (!posicaoExistente(linhas, colunas)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		}
		return pecas[linhas][colunas];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void lugarDaPeca(Peca peca, Posicao posicao) {
		if(existePeca(posicao)) {
			throw new TabuleiroException("Ja existe uma peca na posicao "+ posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	private boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}

	private boolean existePeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posicao nao existe no tabuleiro");
		}
		return peca(posicao) != null;
	}
}
