package br.com.projeto.service;

import java.util.List;
import java.util.Map;

import br.com.projeto.entidades.GrupoUsuario;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoEncontradoException;

public interface GrupoUsuarioService extends ProjetoService<GrupoUsuario, Integer> {

	public List<GrupoUsuario> consultar(Map<String,Object> dados) throws ConsultarException,NaoEncontradoException;
	
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception;
}