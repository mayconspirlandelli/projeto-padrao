package br.com.projeto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class Produto extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6241708807498903670L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	private String descricao;
	
	private String ncm;
	
	@Column(precision=10, scale=2)
	private double valor;
	
	@Column(precision=10, scale=2)
	private double precoCompra;
	
	@Transient
	private double lucro;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grupo_id")
	private GrupoProduto grupo;
	
	public Produto(){
		
	}
	
	public Produto(Integer id, String descricao, double valor){
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

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}
}