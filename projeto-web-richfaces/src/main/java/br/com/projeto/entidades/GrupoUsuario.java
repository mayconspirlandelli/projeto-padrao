package br.com.projeto.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class GrupoUsuario extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514487629178461882L;
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(nullable=false)
	@NotNull(message="descrição é obrigatório")
	@NotEmpty(message="descrição é obrigratório")
	private String descricao;
	
	private boolean ativo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="grupoUsuario")
	private Set<Usuario> usuarios = new HashSet<Usuario>();

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
	
	@Override
	public SummaryEntity getSummaryEntity() {
		return new SummaryEntity(this.getId(), this.getDescricao());
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
	
	@Override
	public String toString(){
		return String.format("\nGrupo Usuario: \tId: | descrição: %s | ativo: %b", id,descricao,ativo);
		
	}

}
