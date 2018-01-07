package br.com.contaazul.teste.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.contaazul.teste.exception.ComandoInvalidoException;

@RunWith(MockitoJUnitRunner.class)
public class MoverRoboServiceImplTest {

	@InjectMocks
	private MoverRoboServiceImpl service;

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void before() {
		service.postContruct();
	}

	@Test
	public void deveLancarExcecaoQuandoReceberComandoNulo() {
		ex.expect(ComandoInvalidoException.class);

		service.executarComandos(null);
	}

	@Test
	public void deveLancarExcecaoQuandoReceberComandoEmBranco() {
		ex.expect(ComandoInvalidoException.class);

		service.executarComandos("  ");
	}

	@Test
	public void deveLancarExcecaoQuandoReceberComandosInvalidos() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando não é válido."));

		service.executarComandos("AA");
	}

	@Test
	public void deveLancarExcecaoQuandoComandosEstrapolaremLimitesDoTerreno() {
		ex.expect(ComandoInvalidoException.class);
		ex.expectMessage(equalTo("O comando ultrapassa os limites do terreno."));

		service.executarComandos("MMMMMMMMMMMMMMMMMMMMMMMM");
	}

	@Test
	public void deveExecutarComandosERetornarPosicao_0_2_W() {
		String posicao = service.executarComandos("MML");

		Assert.assertThat(posicao, equalTo("(0,2,W)"));
	}

	@Test
	public void deveExecutarComandosERetornarPosicao_2_0_S() {
		String posicao = service.executarComandos("MMRMMRMM");

		Assert.assertThat(posicao, equalTo("(2,0,S)"));
	}

	@Test
	public void deveLancarExcecaoQuandoComandoTentarMovimentarRoboParaUmaPosicaoDeXMenorQueAPermitida() {
		ex.expect(ComandoInvalidoException.class);

		service.executarComandos("RRM");
	}

	@Test
	public void deveRetornarPosicaoInicialQuandoNaoExecutarNenhumComando() {
		Assert.assertThat(service.getPosicaoFinal(), equalTo("(0,0,N)"));
	}

	@Test
	public void deveRetornarPosicaoFinalAposExecutarComando() {
		service.executarComandos("MMRMM");

		Assert.assertThat(service.getPosicaoFinal(), equalTo("(2,2,E)"));
	}
}