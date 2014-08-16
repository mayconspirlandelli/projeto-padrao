package br.com.projeto.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class Contato extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4473933764146884274L;

	@Id
	@GeneratedValue
	private Integer id;
		
	@Column(nullable=false)
	@NotNull(message="Nome é obrigatório")
	@NotEmpty(message="Nome é obrigratório")
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name="contato_id")
	private Set<Endereco> enderecos = new HashSet<Endereco>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name="contato_id")
	private Set<Telefone> telefones = new HashSet<Telefone>();

	public Contato(){
		
	}

	@Override
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

}
