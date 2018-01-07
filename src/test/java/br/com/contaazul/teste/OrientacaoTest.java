package br.com.contaazul.teste;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Assert;
import org.junit.Test;

public class OrientacaoTest {

	@Test
	public void deveConverterSiglaEmOrientacaoValida() {
		Assert.assertThat(Orientacao.parse('N'), equalTo(Orientacao.NORTH));
	}

	@Test
	public void deveRetornarNuloQuandoSiglaNaoRepresentarOrientacaoValida() {
		Assert.assertThat(Orientacao.parse('a'), nullValue());
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhEsquerdaDoNorte() {
		Assert.assertThat(Orientacao.NORTH.getLeft(), equalTo(Orientacao.WEST));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhDireitaDoNorte() {
		Assert.assertThat(Orientacao.NORTH.getRight(), equalTo(Orientacao.EAST));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhEsquerdaDoSul() {
		Assert.assertThat(Orientacao.SOUTH.getLeft(), equalTo(Orientacao.EAST));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhDireitaDoSul() {
		Assert.assertThat(Orientacao.SOUTH.getRight(), equalTo(Orientacao.WEST));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhEsquerdaDoLeste() {
		Assert.assertThat(Orientacao.EAST.getLeft(), equalTo(Orientacao.NORTH));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhDireitaDoLeste() {
		Assert.assertThat(Orientacao.EAST.getRight(), equalTo(Orientacao.SOUTH));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhEsquerdaDoOeste() {
		Assert.assertThat(Orientacao.WEST.getLeft(), equalTo(Orientacao.SOUTH));
	}

	@Test
	public void deveRetornarOrientacaoQueFicaAhDireitaDoOeste() {
		Assert.assertThat(Orientacao.WEST.getRight(), equalTo(Orientacao.NORTH));
	}
}