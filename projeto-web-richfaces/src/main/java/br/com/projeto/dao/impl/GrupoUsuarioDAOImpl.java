package br.com.projeto.dao.impl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.projeto.dao.GrupoUsuarioDAO;
import br.com.projeto.entidades.GrupoUsuario;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
 
@Repository
public class GrupoUsuarioDAOImpl extends ProjetoDaoImpl<GrupoUsuario,Integer> implements GrupoUsuarioDAO {
  
    public GrupoUsuario getById(final Integer id) throws ConsultarException, NaoEncontradoException {
        return this.consultar(id);
    }
 
    public List<GrupoUsuario> listar() throws ConsultarException, NaoEncontradoException {
        return super.listar();
    }
 
    public void incluir(GrupoUsuario pessoa) throws IncluirException {
       super.incluir(pessoa);
    }
 
    public void alterar(GrupoUsuario pessoa) throws AlterarException {
        super.alterar(pessoa);
    }
 
    public void excluir(GrupoUsuario pessoa) throws ExcluirException {
       super.excluir(pessoa);
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> consultar(Map<String, Object> dados) throws ConsultarException, NaoEncontradoException {
		
		List<GrupoUsuario> lista = new ArrayList<GrupoUsuario>();
		
		Integer codigo = (Integer)dados.get("id");
		String descricao = dados.get("descricao").toString();
		
		if(codigo!=null){
			lista.add(this.consultar(codigo));
		}
		else if(descricao!=null){
			//coloca a string para maiúsculo pois a <> A. 
			Query query = getEntityManager().createQuery("select g from GrupoUsuario g where upper(g.descricao) LIKE upper(:descricao)");
			query.setParameter("descricao", descricao + "%");

			lista = (List<GrupoUsuario>) query.getResultList();

		}else{
			lista = null;
		}
		
		return lista;
	}

}