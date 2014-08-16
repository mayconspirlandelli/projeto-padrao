package br.com.projeto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class ItemPedido extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8268888128214375228L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	private String descricao;
	
	@Column(precision=10, scale=2)
	private double valor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	public ItemPedido(){
		
	}
	
	public ItemPedido(Integer id, String descricao, double valor){
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
