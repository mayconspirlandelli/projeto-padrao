package br.com.projeto.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.entidades.Usuario;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoAfetadoException;
import br.com.projeto.exception.NaoEncontradoException;

@Repository
public class UsuarioDAOImpl extends ProjetoDaoImpl<Usuario, Integer> implements UsuarioDAO {

	public Usuario getById(final Integer id) throws ConsultarException, NaoEncontradoException {
		return this.consultar(id);
	}

	public List<Usuario> listar() throws ConsultarException, NaoEncontradoException {
		return super.listar();
	}

	public void incluir(Usuario pessoa) throws IncluirException {
		super.incluir(pessoa);
	}

	public void alterar(Usuario pessoa) throws AlterarException {
		super.alterar(pessoa);
	}

	public void excluir(Usuario pessoa) throws ExcluirException {
		super.excluir(pessoa);
	}

	public Usuario consultar(String nome, String senha) throws ConsultarException, NaoAfetadoException {

		try {
			
			/*Consulta um usuário no banco de dados utilizando jpql*/
			StringBuffer jpql = new StringBuffer()
					.append("SELECT DISTINCT usuario ")
					.append("FROM ").append(Usuario.class.getName()).append(" usuario ")
					.append("WHERE usuario.login=:login and usuario.senha = :senha ");

			/*Chama o EntityManager(via DAOGenerico) para executar a consulta*/
			Query query = getEntityManager().createQuery(jpql.toString());
			
			query.setParameter("login", nome);
			query.setParameter("senha", senha);
			
			/*getSingleResult retorna apenas um registro*/
			return (Usuario) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ConsultarException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> consultar(Map<String, Object> dados) throws ConsultarException, NaoEncontradoException {
		
		try {
			List<Usuario> lista = new ArrayList<Usuario>();

			Integer codigo = (Integer) dados.get("id");
			String descricao = (String)dados.get("descricao");
			Integer grupo = (Integer) dados.get("grupo");

			if (codigo != null) {
				lista.add(this.consultar(codigo));
				
			} else if (descricao != null || grupo != null) {
				// coloca a string para maiúsculo pois a <> A.
				StringBuffer jpql = new StringBuffer()
						.append("select u from Usuario u where upper(u.nome) LIKE upper(:descricao) ")
						.append("or upper(u.login) LIKE upper(:descricao) ")
						.append("or u.grupoUsuario.id = :grupo");

				Query query = getEntityManager().createQuery(jpql.toString())
						.setParameter("descricao", descricao + "%")
						.setParameter("grupo", grupo);

				lista = (List<Usuario>) query.getResultList();

			} else {
				lista = null;
			}

			return lista;

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ConsultarException();
		}
		
	}
}