package br.com.projeto.dao.impl;

import br.com.projeto.dao.ProjetoDao;
import br.com.projeto.persistencia.DAOGenericoJPA;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProjetoDaoImpl<E, PK> extends DAOGenericoJPA<E, PK> implements ProjetoDao<E, PK> {
	
	protected Class<E> classeEntidade;
	
	public ProjetoDaoImpl(){
		this.classeEntidade = ((Class)((java.lang.reflect.ParameterizedType)super.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	public void clear() {
		this.getEntityManager().flush();
		this.getEntityManager().clear();
	}	

}
