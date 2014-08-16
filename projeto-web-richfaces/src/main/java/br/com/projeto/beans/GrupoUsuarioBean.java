package br.com.projeto.beans;



public class GrupoUsuarioBean {
	
	/*Campos de pesquisa*/
	protected Integer idPesquisa;

	protected String descricaoPesquisa;

	/*Campos de Inclusão/Alteração*/
	
	protected Integer id;
	
	protected String descricao;
	
	protected boolean ativo = true;

	public GrupoUsuarioBean(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(Integer idPesquisa) {
		this.idPesquisa = idPesquisa;
	}

	public String getDescricaoPesquisa() {
		return descricaoPesquisa;
	}

	public void setDescricaoPesquisa(String descricaoPesquisa) {
		this.descricaoPesquisa = descricaoPesquisa;
	}
	
	

}
