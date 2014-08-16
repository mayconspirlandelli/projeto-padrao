package br.com.projeto.entidades;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="PJ")
public class PessoaJuridica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6469575714525040442L;

	private String nomeFantasia;

	private String razaoSocial;

	private String cnpj;

	private String inscricaoEstadual;

	public PessoaJuridica() {
		super();
	}

	public PessoaJuridica(Integer id, Set<Endereco> enderecos,
			Set<Telefone> telefones, Set<Pedido> pedidos, String nomeFantasia,
			String razaoSocial, String cnpj, String inscricaoEstadual) {
		super(id, enderecos, telefones, pedidos);
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

}
