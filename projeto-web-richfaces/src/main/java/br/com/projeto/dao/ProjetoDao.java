package br.com.projeto.dao;

import br.com.projeto.persistencia.DAOGenerico;

public interface ProjetoDao<E, PK> extends DAOGenerico<E, PK> {

	public void clear();

}
