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
	
	public abstract boolean[][] possibilidadeMovimentos();
	
	public boolean possibilidadeMovimento(Posicao posicao) {
		return possibilidadeMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}
	public boolean existeMovimentoPossivelPeca() {
		boolean[][] mat = possibilidadeMovimentos();
		
		for(int i = 0; i<mat.length; i++) {
			for(int j = 0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	

}
