package br.com.projeto.exception;

public class NaoEncontradoException extends ErroException {
	private static final long serialVersionUID = 1L;
	private Object item = null;

	public NaoEncontradoException() {
		super("O item não foi encontrado");
	}

	public NaoEncontradoException(String msg) {
		super(msg);
	}

	public NaoEncontradoException(String msg, int codigoErro) {
		super(msg, codigoErro);
	}

	public NaoEncontradoException(Object item) {
		super("O item não foi encontrado: " + item.toString());
		this.item = item;
	}

	public NaoEncontradoException(String msg, Object item) {
		super(msg);
		this.item = item;
	}

	public Object getItem() {
		return this.item;
	}
}