package br.com.projeto.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.projeto.framework.entidade.AbstractEntity;


@Entity
public class Cidade extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2424056631677279599L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column(nullable=false)
	@NotNull(message="nome é obrigatório")
	@NotEmpty(message="nome é obrigratório")
	private String nome;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado_id")
	private Estado estado;

	
	@OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="cidade")
	private Endereco endereco;
	
	
	public Cidade(){
		
	}
	
	public Cidade(String nome, Estado estado){
		this.nome = nome;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
	
	@Override
	public SummaryEntity getSummaryEntity() {
		return new SummaryEntity(this.getId(), this.getNome());
	}

}
