package br.com.projeto.service;

import java.util.List;

import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.negocio.INegocio;

public interface ProjetoService<E, PK> extends INegocio<E, PK> {

	public void incluir(E entidade) throws IncluirException;

	public void alterar(E entidade) throws AlterarException;

	public void excluir(E entidade) throws ExcluirException;

	public E consultar(PK id) throws ConsultarException, NaoEncontradoException;

	public List<E> listar() throws ConsultarException, NaoEncontradoException;

	public void clear();

}
