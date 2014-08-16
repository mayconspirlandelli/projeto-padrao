package br.com.projeto.framework.exception;

@SuppressWarnings("serial")
public class InformationException extends ErroException {

    /**
     * Construtor.
     * @param e Exception
     */
    public InformationException(Exception e) {
    	super(e, NIVEL_INFORM);
    }
    
	/**
	 * Construtor.
	 * @param msg
	 */
     public InformationException(String msg){
         this(msg, null, null, null);
     }

     /**
      * Construtor.
      * @param mensagem String
      * @param sistema String
      * @param classe String
      * @param metodo String
      */
     public InformationException(String mensagem, String sistema, String classe, String metodo) {
         super(mensagem, sistema, NIVEL_INFORM, classe, metodo);
     }
}