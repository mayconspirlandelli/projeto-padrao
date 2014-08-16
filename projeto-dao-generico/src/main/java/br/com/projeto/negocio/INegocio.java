package br.com.projeto.negocio;

import java.util.List;

import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.persistencia.DAOGenerico;

public abstract interface INegocio<E, PK> {
	public abstract void incluir(E paramE) throws IncluirException;

	public abstract void alterar(E paramE) throws AlterarException;

	public abstract void excluir(E paramE) throws ExcluirException;

	public abstract void incluir(List<E> paramList) throws IncluirException;

	public abstract void alterar(List<E> paramList) throws AlterarException;

	public abstract void excluir(List<E> paramList) throws ExcluirException;

	public abstract E consultar(PK paramPK) throws ConsultarException, NaoEncontradoException;

	public abstract List<E> listar() throws ConsultarException, NaoEncontradoException;

	public abstract DAOGenerico<E, PK> getDAO();
}