package br.com.projeto.service;

import br.com.projeto.entidades.PessoaFisica;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoEncontradoException;

public interface PessoaFisicaService extends ProjetoService<PessoaFisica, Integer> {
	
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception;
}