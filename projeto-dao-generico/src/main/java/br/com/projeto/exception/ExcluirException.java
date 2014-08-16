package br.com.projeto.exception;

public class ExcluirException extends ErroException {
	private static final long serialVersionUID = 1L;

	public ExcluirException() {
		super("Erro ao realizar exclus�o");
	}

	public ExcluirException(String msg) {
		super(msg);
	}

	public ExcluirException(Exception e) {
		super("Erro ao realizar exclus�o: " + e.getMessage());
	}
}