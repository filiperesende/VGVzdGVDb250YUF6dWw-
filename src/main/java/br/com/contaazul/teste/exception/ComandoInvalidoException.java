package br.com.contaazul.teste.exception;

public class ComandoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -3057517747552804279L;

	public ComandoInvalidoException() {
		this("O comando não é válido.");
	}

	public ComandoInvalidoException(String msg) {
		super(msg);
	}
}