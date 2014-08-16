package br.com.projeto.framework.exception;


@SuppressWarnings("serial")
public class ErroException extends RuntimeException {

    protected static final String NIVEL_ERROR = "ERROR";
    protected static final String NIVEL_WARNING = "WARNING";
    protected static final String NIVEL_INFORM = "INFORM";
    protected static final String MSG_DEFAULT = "Ocorreu um erro";
    protected static final String MSG_DEFAULT_CONSULTAR = "Erro ao realizar consulta";
    protected static final String MSG_DEFAULT_INCLUIR = "Erro ao realizar inclusão";
    protected static final String MSG_DEFAULT_ALTERAR = "Erro ao realizar alteração";
    protected static final String MSG_DEFAULT_EXCLUIR = "Erro ao realizar exclusão";
    protected static final String MSG_DEFAULT_NAOENCONTRADO = "O item não foi encontrado";
    
    private static String classe = "";
    private static String metodo = "";
    private static String mensagem = "";
    private String nivel = NIVEL_ERROR;
    private String sistema = "*";
    private Exception exception = null;

    /**
     * Construtor.
     * @param e Exception
     */
    public ErroException(Exception e) {
    	this(e, null);
    }

    /**
     * Construtor.
     * @param e Exception
     * @param nivel String
     */
    public ErroException(Exception e, String nivel) {
    	this(e, null, null, nivel, null, null);
    }
    
    /**
     * Construtor.
     * @param msg String
     */
    public ErroException(String msg) {
    	this(msg, null, null, null, null);
    }
    
    /**
     * Construtor.
     * @param mensagem String
     * @param sistema String
     * @param nivel String
     * @param classe String
     * @param metodo String
     */
    public ErroException(String mensagem, String sistema, String nivel, String classe, String metodo) {
        this(null, mensagem, sistema, nivel, classe, metodo);
    }

    /**
     * Construtor.
     * @param exception Exception
     * @param mensagem String
     * @param sistema String
     * @param nivel String
     * @param classe String
     * @param metodo String
     */
    @SuppressWarnings("static-access")
	private ErroException(Exception exception, String mensagem, String sistema, String nivel, String classe, String metodo) {
        super(mensagem);

        // nível tem valor padrão
        if(nivel != null && !nivel.equals("")) {
            this.nivel = nivel;
        }

        if(this.getException() != null){
            this.classe = this.getException().getStackTrace()[0].getClassName();
            this.metodo = this.getException().getStackTrace()[0].getMethodName();
            this.mensagem = this.getException().getMessage();
        }
        else{
        	this.classe = classe;
        	this.metodo = metodo;
        }
        
        this.sistema = sistema;
        this.mensagem = classe + "." + metodo + "() - " + mensagem;
        
        // Log.error(this.getMessage(), this.getException());
    }
	
	/**
	 * Retorna o atributo classe.
	 * @return String
	 */
	public static String getClasse() {
		return classe;
	}
	
	/**
	 * Retorna o atributo metodo.
	 * @return String
	 */
	public static String getMetodo() {
		return metodo;
	}
	
	/**
	 * Retorna o atributo mensagem.
	 * @return String
	 */
	public static String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Retorna o atributo nivel.
	 * @return String
	 */
	public String getNivel() {
		return nivel;
	}
	
	/**
	 * Retorna o atributo sistema.
	 * @return String
	 */
	public String getSistema() {
		return sistema;
	}
	
	/**
	 * Retorna o atributo exception.
	 * @return Exception
	 */
	public Exception getException() {
		return exception;
	}
}