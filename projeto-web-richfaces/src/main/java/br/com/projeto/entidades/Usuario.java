package br.com.projeto.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class Usuario extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -344165210052936760L;
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(nullable=false)
	@NotNull(message="login é obrigatório")
	@NotEmpty(message="login é obrigratório")
	private String login;
	
	@Column(nullable=false)
	@NotNull(message="senha é obrigatório")
	@NotEmpty(message="senha é obrigratório")
	private String senha;
	
	@Column(nullable=false)
	@NotNull(message="nome é obrigatório")
	@NotEmpty(message="nome é obrigratório")
	private String nome;
	
	private boolean ativo;
	
	@Temporal(TemporalType.DATE)
	private Date ultimoLogin;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grupo_id")
	private GrupoUsuario grupoUsuario;
	
	public Usuario(){
		
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
	
	@Override
	public SummaryEntity getSummaryEntity() {
		return new SummaryEntity(this.getId(), this.getNome());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}
	
	@Override
	public String toString(){
		return String.format("\nUsuario: \tGrupo: %s | Id: | login: %s | nome: %s | senha: %s | ativo: %b | ultimoLogin: %s",
				grupoUsuario.getDescricao(),id,login,nome,senha,ativo,ultimoLogin);
	}
}