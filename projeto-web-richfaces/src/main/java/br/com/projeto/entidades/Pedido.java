package br.com.projeto.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class Pedido extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8802247560737076014L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	@Column(precision=10, scale=2)
	private double valor;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true, mappedBy="pedido")
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();
	
	
	public Pedido(){
		
	}
	
	public Pedido(Integer id, Pessoa cliente, Set<ItemPedido> itens){
		this.id = id;
		this.itens = itens;
		this.valor = getValorPedido();
	}
	
	public double getValorPedido(){
		
		double valorPedido = 0.0;
		
		for (ItemPedido item : this.itens) {
			valorPedido +=item.getValor();
		}
		
		return valorPedido;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
}