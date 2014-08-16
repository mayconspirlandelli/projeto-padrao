package br.com.projeto.exception;

public class ConsultarException extends ErroException {
	private static final long serialVersionUID = 1L;

	public ConsultarException() {
		super("Erro ao realizar consulta");
	}

	public ConsultarException(String msg) {
		super(msg);
	}

	public ConsultarException(Exception e) {
		super("Erro ao realizar consulta: " + e.getMessage());
	}
}