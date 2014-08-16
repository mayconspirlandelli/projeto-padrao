package br.com.projeto.exception;

public class NaoAfetadoException extends ErroException {
	private static final long serialVersionUID = 1L;

	public NaoAfetadoException(Exception e) {
		super(e);
	}

	public NaoAfetadoException(String mens) {
		super(mens);
	}
}