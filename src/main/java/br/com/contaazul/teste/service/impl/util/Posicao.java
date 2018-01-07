package br.com.contaazul.teste.service.impl.util;

import br.com.contaazul.teste.Orientacao;
import br.com.contaazul.teste.exception.ComandoInvalidoException;

public class Posicao {

	private int x;
	private int y;
	private Orientacao orientacao;

	public Posicao() {
		this.orientacao = Orientacao.NORTH;
	}

	public Posicao(int x, int y, Orientacao orientacao) {
		this.x = x;
		this.y = y;
		this.orientacao = orientacao;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Orientacao getOrientacao() {
		return orientacao;
	}

	public void move() {
		if (Orientacao.NORTH.equals(orientacao)) {
			y++;
		} else if (Orientacao.SOUTH.equals(orientacao)) {
			y--;
		} else if (Orientacao.EAST.equals(orientacao)) {
			x++;
		} else {
			x--;
		}

		if (x < 0 || x > 4 || y < 0 || y > 4) {
			throw new ComandoInvalidoException("O comando ultrapassa os limites do terreno.");
		}
	}

	public void turnLeft() {
		this.orientacao = orientacao.getLeft();
	}

	public void turnRight() {
		this.orientacao = orientacao.getRight();
	}

	@Override
	public String toString() {
		return String.format("(%s,%s,%s)", getX(), getY(), getOrientacao().getSigla());
	}
}