package br.com.projeto.exception;

public class BancoDadosException extends ErroException {
	private static final long serialVersionUID = 1L;

	public BancoDadosException(Exception e) {
		super(e);
	}
}