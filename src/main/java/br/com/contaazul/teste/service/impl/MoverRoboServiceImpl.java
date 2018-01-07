package br.com.contaazul.teste.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.contaazul.teste.Comando;
import br.com.contaazul.teste.Orientacao;
import br.com.contaazul.teste.exception.ComandoInvalidoException;
import br.com.contaazul.teste.service.MoverRoboService;
import br.com.contaazul.teste.service.impl.util.Posicao;

@Service
public class MoverRoboServiceImpl implements MoverRoboService {

	private static final long serialVersionUID = -7794987822895722584L;

	private Posicao posicao;

	@PostConstruct
	public void postContruct() {
		posicao = new Posicao();
	}

	@Override
	public String executarComandos(String comandos) {
		validarComandos(comandos);

		return execute(comandos);
	}

	private String execute(String comandos) {
		posicao = new Posicao(0, 0, Orientacao.NORTH);
		for (char cmd : comandos.toUpperCase().toCharArray()) {
			if (isMove(cmd)) {
				posicao.move();
			} else if (isLeft(cmd)) {
				posicao.turnLeft();
			} else {
				posicao.turnRight();
			}
		}
		return posicao.toString();
	}

	private boolean isLeft(char cmd) {
		return Comando.LEFT.getSigla() == cmd;
	}

	private boolean isMove(char cmd) {
		return Comando.MOVE.getSigla() == cmd;
	}

	private void validarComandos(String comandos) {
		if (comandos == null || comandos.trim().isEmpty()) {
			throw new ComandoInvalidoException();
		}

		String upCommand = comandos.toUpperCase();
		if (!upCommand.matches("[N|S|E|W|L|R|M]+")) {
			throw new ComandoInvalidoException();
		}
	}

	@Override
	public String getPosicaoFinal() {
		return posicao.toString();
	}
}