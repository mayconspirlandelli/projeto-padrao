package br.com.projeto.dao;
 
import java.util.List;
import java.util.Map;

import br.com.projeto.entidades.Usuario;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoAfetadoException;
import br.com.projeto.exception.NaoEncontradoException;
 
public interface UsuarioDAO extends ProjetoDao<Usuario, Integer>{
	
	public Usuario consultar(String nome, String senha) throws ConsultarException, NaoAfetadoException;

	public List<Usuario> consultar(Map<String,Object> dados) throws ConsultarException, NaoEncontradoException;
	
}