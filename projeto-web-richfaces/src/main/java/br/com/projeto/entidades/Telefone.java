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
public class Telefone extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 463887703471167333L;

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	private String comercial;
	
	private String fixo;
	
	private String celular;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	public Telefone(){

	}
	
	public Telefone(String comercial, String fixo, String celular){
		this.comercial = comercial;
		this.fixo = fixo;
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}

}
