package br.com.projeto.service.impl;

import br.com.projeto.dao.ProjetoDao;
import br.com.projeto.negocio.NegocioGenerico;
import br.com.projeto.service.ProjetoService;

@SuppressWarnings("unchecked")
public class ProjetoServiceImpl<E, PK> extends NegocioGenerico<E, PK> implements ProjetoService<E, PK> {
	
	public void clear() {
		((ProjetoDao<E, PK>)getDAO()).clear();
	}


}
