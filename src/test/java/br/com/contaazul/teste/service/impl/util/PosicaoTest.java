package br.com.contaazul.teste.service.impl.util;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.contaazul.teste.Orientacao;
import br.com.contaazul.teste.exception.ComandoInvalidoException;

@RunWith(MockitoJUnitRunner.class)
public class PosicaoTest {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();

	private Posicao posicao;

	@Test
	public void deveMovimentarUmaPosicaoAoNorte() {
		posicao = new Posicao(0, 0, Orientacao.NORTH);

		posicao.move();

		Assert.assertThat(posicao.getY(), equalTo(1));
	}

	@Test
	public void deveMovimentarDuasPosicoesAoSul() {
		posicao = new Posicao(0, 2, Orientacao.SOUTH);

		posicao.move();
		posicao.move();

		Assert.assertThat(posicao.getY(), equalTo(0));
	}

	@Test
	public void deveMovimentarUmaPosicaoALeste() {
		posicao = new Posicao(0, 0, Orientacao.EAST);

		posicao.move();

		Assert.assertThat(posicao.getX(), equalTo(1));
	}

	@Test
	public void deveMovimentarDuasPosicoesAoOeste() {
		posicao = new Posicao(4, 0, Orientacao.WEST);

		posicao.move();
		posicao.move();

		Assert.assertThat(posicao.getX(), equalTo(2));
	}

	@Test
	public void deveVirarAhEsquerda() {
		posicao = new Posicao(0, 0, Orientacao.NORTH);

		posicao.turnLeft();

		Assert.assertThat(posicao.getOrientacao(), equalTo(Orientacao.WEST));
	}

	@Test
	public void deveVirarAhDireita() {
		posicao = new Posicao(0, 0, Orientacao.NORTH);

		posicao.turnRight();

		Assert.assertThat(posicao.getOrientacao(), equalTo(Orientacao.EAST));
	}

	@Test
	public void deveRetornarStringQueRepresentaAPosicaoAtual() {
		posicao = new Posicao(1, 3, Orientacao.NORTH);

		Assert.assertThat(posicao.toString(), equalTo("(1,3,N)"));
	}

	@Test
	public void deveLancarExcecaoQuandoComandoTentarMovimentarRoboParaUmaPosicaoDeXMenorQueAPermitida() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando ultrapassa os limites do terreno."));

		posicao = new Posicao();

		posicao.turnLeft();
		posicao.move();
	}

	@Test
	public void deveLancarExcecaoQuandoComandoTentarMovimentarRoboParaUmaPosicaoDeXMaiorQueAPermitida() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando ultrapassa os limites do terreno."));

		posicao = new Posicao(4, 0, Orientacao.EAST);

		posicao.move();
	}

	@Test
	public void deveLancarExcecaoQuandoComandoTentarMovimentarRoboParaUmaPosicaoDeYMenorQueAPermitida() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando ultrapassa os limites do terreno."));

		posicao = new Posicao(0, 0, Orientacao.SOUTH);

		posicao.move();
	}

	@Test
	public void deveLancarExcecaoQuandoComandoTentarMovimentarRoboParaUmaPosicaoDeYMaiorQueAPermitida() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando ultrapassa os limites do terreno."));

		posicao = new Posicao(0, 4, Orientacao.NORTH);

		posicao.move();
	}
}