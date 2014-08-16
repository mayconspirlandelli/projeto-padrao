package br.com.projeto.service;

import java.util.List;
import java.util.Map;

import br.com.projeto.entidades.Usuario;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoAfetadoException;
import br.com.projeto.exception.NaoEncontradoException;

public interface UsuarioService extends ProjetoService<Usuario, Integer> {

	public Usuario consultar(String nome, String senha) throws ConsultarException, NaoAfetadoException;
	
	public Object[] imprimir(Map<String,Object> dados) throws ConsultarException,
			NaoEncontradoException, Exception;

	public List<Usuario> consultar(Map<String,Object> dados) throws ConsultarException,NaoEncontradoException;
}