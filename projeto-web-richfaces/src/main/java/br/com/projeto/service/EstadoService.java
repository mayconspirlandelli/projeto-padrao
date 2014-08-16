package br.com.projeto.service;

import br.com.projeto.entidades.Estado;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoEncontradoException;

public interface EstadoService extends ProjetoService<Estado, Integer> {
	
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception;
}