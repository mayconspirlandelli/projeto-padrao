package br.com.projeto.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DAOGenericoJPA<E, PK> implements DAOGenerico<E, PK> {

	@PersistenceContext
	private EntityManager entityManager;
	private Class<E> classeEntidade;

	protected void setEntityManger(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public DAOGenericoJPA() {
		this.classeEntidade = ((Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public void incluir(E entidade) throws IncluirException {
		try {
			this.entityManager.persist(entidade);
		} catch (Exception e) {
			throw new IncluirException(e);
		}
	}

	public void excluir(E entidade) throws ExcluirException {
		try {
			entidade = this.entityManager.merge(entidade);
			this.entityManager.remove(entidade);
		} catch (Exception e) {
			throw new ExcluirException(e);
		}
	}

	public void alterar(E entidade) throws AlterarException {
		try {
			this.entityManager.merge(entidade);
		} catch (Exception e) {
			throw new AlterarException(e);
		}
	}

	public E consultar(PK codigo) throws ConsultarException,
			NaoEncontradoException {
		Object entidade;
		try {
			entidade = this.entityManager.find(this.classeEntidade, codigo);

			if (entidade == null)
				throw new NaoEncontradoException("msgNaoEncontrado");
		} catch (Exception e) {
			throw new ConsultarException(e);
		}

		return (E) entidade;
	}

	public List<E> listar() throws ConsultarException, NaoEncontradoException {
		List listaEntidades = null;
		String nomeEntidade = this.classeEntidade.getSimpleName();
		try {
			Query query = this.entityManager.createQuery("select e from "+ nomeEntidade + " e");
			query.setMaxResults(50);

			listaEntidades = query.getResultList();

			if (listaEntidades.isEmpty())
				throw new NaoEncontradoException("msgNaoEncontrado");
		} catch (IllegalStateException e) {
			throw new ConsultarException(e);
		}

		return listaEntidades;
	}
}