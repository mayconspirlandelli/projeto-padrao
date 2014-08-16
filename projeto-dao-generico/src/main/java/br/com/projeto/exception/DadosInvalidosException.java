package br.com.projeto.exception;

public class DadosInvalidosException extends ErroException {
	private static final long serialVersionUID = 1L;

	public DadosInvalidosException() {
		super("Um dos dados n�o � v�lido");
	}

	public DadosInvalidosException(String msg) {
		super(msg);
	}

	public DadosInvalidosException(Exception e) {
		super("Um dos dados n�o � v�lido: " + e.getMessage());
	}
}