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
public class GrupoProduto extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(nullable=false,unique=true)
	@NotNull(message="descrição é obrigatório")
	@NotEmpty(message="descrição é obrigratório")
	private String descricao;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="grupo")
	private Set<Produto> produto = new HashSet<Produto>();
	

	public GrupoProduto(){

	}


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


	public Set<Produto> getProduto() {
		return produto;
	}


	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}


}
