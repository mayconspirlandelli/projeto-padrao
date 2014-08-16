package br.com.projeto.exception;

public class IncluirException extends ErroException {
	private static final long serialVersionUID = 1L;

	public IncluirException() {
		super("Erro ao realizar inclus�o");
	}

	public IncluirException(String msg) {
		super(msg);
	}

	public IncluirException(Exception e) {
		super("Erro ao realizar inclus�o: " + e.getMessage());
	}
}