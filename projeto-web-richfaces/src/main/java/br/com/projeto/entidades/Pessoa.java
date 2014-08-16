package br.com.projeto.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="pessoa")
public class Pessoa extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6330954873841742738L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private boolean ativo = true;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="pessoa")
	private Set<Endereco> enderecos = new HashSet<Endereco>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="pessoa")
	private Set<Telefone> telefones = new HashSet<Telefone>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="pessoa")
	private Set<Pedido> pedidos = new HashSet<Pedido>();
	
	
	public Pessoa(){
		
	}
	
	public Pessoa(Integer id,Set<Endereco> enderecos, Set<Telefone> telefones, Set<Pedido> pedidos){
		this.id = id;
		this.enderecos = enderecos;
		this.telefones = telefones;
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
}
