package br.com.projeto.entidades;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.projeto.enumeradores.EnumPapel;

@Entity
@DiscriminatorValue(value="PF")
public class PessoaFisica extends Pessoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2499717821431808735L;

	private String nome;

	private String cpf;

	private String rg;
	
	@Enumerated(EnumType.STRING)
	private EnumPapel papel;

	public PessoaFisica() {
		super();
	}

	public PessoaFisica(Integer id, Set<Endereco> enderecos,
			Set<Telefone> telefones, Set<Pedido> pedidos, String nome,
			String cpf, String rg, EnumPapel papel) {
		super(id, enderecos, telefones, pedidos);
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.papel = papel;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public EnumPapel getPapel() {
		return papel;
	}

	public void setPapel(EnumPapel papel) {
		this.papel = papel;
	}
}
