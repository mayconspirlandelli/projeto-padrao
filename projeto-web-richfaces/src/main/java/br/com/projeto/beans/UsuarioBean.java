package br.com.projeto.beans;




public class UsuarioBean {
	
	/*Campos de pesquisa*/
	protected Integer idPesquisa;

	protected String descricaoPesquisa;

	/*Campos de Inclusão/Alteração*/
	
	protected Integer id;
	
	protected String login;
	
	protected String nome;
	
	protected String senha;
	
	protected String confirmaSenha;
	
	protected boolean ativo = true;
	
	protected Integer grupoUsuario;
	
	public UsuarioBean(){
		
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(Integer grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	

}
