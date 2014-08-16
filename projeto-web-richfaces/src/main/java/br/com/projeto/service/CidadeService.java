package br.com.projeto.service;

import br.com.projeto.entidades.Cidade;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoEncontradoException;

public interface CidadeService extends ProjetoService<Cidade, Integer> {
	
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception;
}