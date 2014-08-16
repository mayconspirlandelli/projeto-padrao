package br.com.projeto.exception;

public class AlterarException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlterarException(){
		super("Erro ao realizar altera��o");
	}
	
	public AlterarException(String msg){
		super(msg);
	}
	
	public AlterarException(Exception e){
		super("Erro ao realizar altera��o: " + e.getMessage());
	}
	
}
