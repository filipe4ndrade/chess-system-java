package tabuleiro;

public class TabuleiroDoJogo {

	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas;
	
	public TabuleiroDoJogo(Integer linhas, Integer colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public Integer getLinhas() {
		return linhas;
	}

	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}
	
	public Peca peca(int linhas, int colunas) {
		return pecas[linhas][colunas];
	}
	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
}
