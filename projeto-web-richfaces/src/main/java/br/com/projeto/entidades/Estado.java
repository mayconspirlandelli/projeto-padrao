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
public class Estado extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2154622554179385523L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(nullable=false,unique=true)
	@NotNull(message="nome é obrigatório")
	@NotEmpty(message="nome é obrigratório")
	private String nome;
	
	@Column(nullable=false,unique=true)
	@NotNull(message="sigla é obrigatório")
	@NotEmpty(message="sigla é obrigratório")
	private String sigla;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="estado")
	private Set<Cidade> cidades = new HashSet<Cidade>();
	
	
	public Estado(){

	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
	
	@Override
	public SummaryEntity getSummaryEntity() {
		return new SummaryEntity(this.getId(), this.getNome());
	}


	public Set<Cidade> getCidades() {
		return cidades;
	}


	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

}
