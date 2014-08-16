package br.com.projeto.controle;


public interface IProjetoControladorJSF {	
	
	
	public void construct();
	
	public void destruct();
	
	public void novo();
	
	public void consultar();
	
	public void incluir();
	
	public void editar(Integer id);
	
	public void manter();
	
	public void listar();
	
	public void mostrar();
	
	public void excluir(Integer id);

}
