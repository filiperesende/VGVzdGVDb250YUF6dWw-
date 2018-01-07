package br.com.contaazul.teste;

public enum Comando {

	MOVE('M'),
	LEFT('L'),
	RIGHT('R');

	private final char sigla;

	private Comando(char sigla) {
		this.sigla = sigla;
	}

	public char getSigla() {
		return sigla;
	}
}