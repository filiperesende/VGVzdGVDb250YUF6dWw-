package br.com.contaazul.teste.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.contaazul.teste.exception.ComandoInvalidoException;
import br.com.contaazul.teste.service.MoverRoboService;

@RunWith(MockitoJUnitRunner.class)
public class ComandosControllerTest {

	@InjectMocks
	private ComandosController controller;

	@Mock
	private MoverRoboService service;

	@Test
	public void deveDelegarAoServiceAExecucaoDosComandos() {
		controller.executarComandos("MML");

		verify(service).executarComandos("MML");
	}

	@Test
	public void deveRetornarPosicaoDoRoboAposExecutarComando() {
		when(service.executarComandos(anyString())).thenReturn("(1,2,E)");

		ResponseEntity<Object> retorno = controller.executarComandos("RMLMMR");

		Assert.assertThat(retorno.getBody(), equalTo("(1,2,E)"));
		Assert.assertThat(retorno.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void deveRetornarErro400EMensagemDoErroQuandoAcontecerAlgumProblemaAoExecutarComandos() {
		when(service.executarComandos(anyString())).thenThrow(new ComandoInvalidoException());

		ResponseEntity<Object> retorno = controller.executarComandos("XXX");

		Assert.assertThat(retorno.getBody(), equalTo("O comando não é válido."));
		Assert.assertThat(retorno.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void deveDelegarAoServiceABuscaPeloPosicaoFinal() {
		controller.getPosicaoFinal();

		verify(service).getPosicaoFinal();
	}

	@Test
	public void deveRetornarPosicaoFinalDoRoboAposExecutarComandos() {
		when(service.getPosicaoFinal()).thenReturn("(4,4,S)");

		ResponseEntity<Object> retorno = controller.getPosicaoFinal();

		Assert.assertThat(retorno.getBody(), equalTo("(4,4,S)"));
		Assert.assertThat(retorno.getStatusCode(), equalTo(HttpStatus.OK));
	}
}