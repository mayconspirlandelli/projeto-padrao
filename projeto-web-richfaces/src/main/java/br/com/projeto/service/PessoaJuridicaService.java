package br.com.projeto.service;

import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoEncontradoException;

public interface PessoaJuridicaService extends ProjetoService<PessoaFisicaService, Integer> {
	
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception;
}