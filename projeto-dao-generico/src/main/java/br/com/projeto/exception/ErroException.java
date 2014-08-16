package br.com.projeto.exception;

public class ErroException extends Exception {
	private static final long serialVersionUID = 1L;
	protected static final String MSG_DEFAULT = "Ocorreu um erro";
	protected static final String MSG_DEFAULT_CONSULTAR = "Erro ao realizar consulta";
	protected static final String MSG_DEFAULT_INCLUIR = "Erro ao realizar inclus�o";
	protected static final String MSG_DEFAULT_ALTERAR = "Erro ao realizar altera��o";
	protected static final String MSG_DEFAULT_EXCLUIR = "Erro ao realizar exclus�o";
	protected static final String MSG_DEFAULT_NAOENCONTRADO = "O item n�o foi encontrado";
	protected static final String MSG_DEFAULT_DADOS_INVALIDOS = "Um dos dados n�o � v�lido";
	protected int codigoErro = 0;

	public ErroException() {
		super("Ocorreu um erro");
	}

	public ErroException(String msg) {
		super(msg);
	}

	public ErroException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ErroException(String msg, int codigoErro) {
		super(msg);
		this.codigoErro = codigoErro;
	}

	public ErroException(Exception e) {
		super("Ocorreu um erro: " + e.getMessage(), e);
	}

	public int getCodigoErro() {
		return this.codigoErro;
	}
}