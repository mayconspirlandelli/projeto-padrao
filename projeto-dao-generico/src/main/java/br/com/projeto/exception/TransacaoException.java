package br.com.projeto.exception;

public class TransacaoException extends ErroException {
	private static final long serialVersionUID = 1L;

	public TransacaoException(Exception e) {
		super(e);
	}

	public TransacaoException(String mensagem) {
		super(mensagem);
	}
}