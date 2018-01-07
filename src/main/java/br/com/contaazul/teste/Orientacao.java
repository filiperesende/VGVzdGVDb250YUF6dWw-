package br.com.contaazul.teste;

public enum Orientacao {

	NORTH('N', 'W', 'E'),
	SOUTH('S', 'E', 'W'),
	EAST('E', 'N', 'S'),
	WEST('W', 'S', 'N');

	private final char sigla;
	private final char left;
	private final char right;

	private Orientacao(final char sigla, final char left, final char right) {
		this.sigla = sigla;
		this.left = left;
		this.right = right;
	}

	public char getSigla() {
		return sigla;
	}

	public Orientacao getLeft() {
		return Orientacao.parse(left);
	}

	public Orientacao getRight() {
		return Orientacao.parse(right);
	}

	public static Orientacao parse(char sigla) {
		for (Orientacao item : Orientacao.values()) {
			if (item.getSigla() == sigla) {
				return item;
			}
		}
		return null;
	}
}