package br.com.projeto.negocio;

import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.persistencia.DAOGenerico;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NegocioGenerico<E, PK> implements INegocio<E, PK> {

	private DAOGenerico<E, PK> daoGenerico;

	protected void setDAO(DAOGenerico<E, PK> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}

	public DAOGenerico getDAO() {
		return this.daoGenerico;
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void incluir(E entidade) throws IncluirException {
		this.daoGenerico.incluir(entidade);
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void incluir(List<E> entidades) throws IncluirException {
		for (Iterator i$ = entidades.iterator(); i$.hasNext();) {
			Object entidade = i$.next();
			this.daoGenerico.incluir((E) entidade);
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void alterar(E entidade) throws AlterarException {
		this.daoGenerico.alterar(entidade);
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void alterar(List<E> entidades) throws AlterarException {
		for (Iterator i$ = entidades.iterator(); i$.hasNext();) {
			Object entidade = i$.next();
			this.daoGenerico.alterar((E) entidade);
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void excluir(E entidade) throws ExcluirException {
		this.daoGenerico.excluir(entidade);
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void excluir(List<E> entidades) throws ExcluirException {
		for (Iterator i$ = entidades.iterator(); i$.hasNext();) {
			Object entidade = i$.next();
			this.daoGenerico.excluir((E) entidade);
		}
	}

	@Transactional(readOnly = true)
	public E consultar(PK codigo) throws ConsultarException,
			NaoEncontradoException {
		return this.daoGenerico.consultar(codigo);
	}

	@Transactional(readOnly = true)
	public List<E> listar() throws ConsultarException, NaoEncontradoException {
		return this.daoGenerico.listar();
	}
}