package br.com.projeto.util;

import org.springframework.stereotype.Component;

@Component
public class MenuControlador {
	

	
	public MenuControlador(){
		
	}
	
	public String pessoaFisica(){
		return "/view/pessoaFisica.xhtml?faces-redirect=true";
	}
	
	public String pessoaJuridica(){
		return "/view/pessoaJuridica.xhtml?faces-redirect=true";
	}
	
	public String grupoUsuarios(){
		return "/view/grupoUsuarios.xhtml?faces-redirect=true";
	}
	
	public String usuarios(){
		return "/view/usuarios.xhtml?faces-redirect=true";
	}
	
	public String grupoProduto(){
		return "/view/grupoProdutos.xhtml?faces-redirect=true";
	}
	
	public String produto(){
		return "/view/produto.xhtml?faces-redirect=true";
	}
	
	public String pedido(){
		return "/view/pedido.xhtml?faces-redirect=true";
	}
	
	public String estado(){
		return "/view/estado.xhtml?faces-redirect=true";
	}
	
	public String cidade(){
		return "/view/cidade.xhtml?faces-redirect=true";
	}
}
