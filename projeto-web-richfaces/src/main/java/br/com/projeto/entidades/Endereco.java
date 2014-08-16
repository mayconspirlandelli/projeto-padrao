package br.com.projeto.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.projeto.enumeradores.EnumTipoEndereco;
import br.com.projeto.framework.entidade.AbstractEntity;

@Entity
public class Endereco extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3682205633499682467L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String cep;
	
	@OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoEndereco tipoEndereco;	
	
	 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;	
	
	public Endereco(){
		
	}
	
	public Endereco(String cep, Cidade cidade, EnumTipoEndereco tipoEndereco){
		this.cep = cep;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public EnumTipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getId();
	}
}
