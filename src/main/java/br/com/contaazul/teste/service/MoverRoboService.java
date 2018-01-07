package br.com.contaazul.teste.service;

import java.io.Serializable;

public interface MoverRoboService extends Serializable {

	String executarComandos(String comandos);
	String getPosicaoFinal();

}