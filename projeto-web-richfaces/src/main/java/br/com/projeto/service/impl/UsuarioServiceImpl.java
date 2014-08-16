package br.com.projeto.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.dao.UsuarioDAO;
import br.com.projeto.entidades.Usuario;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoAfetadoException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.service.UsuarioService;

@Service("usuarioService")
@Transactional
@SuppressWarnings("unchecked")
public class UsuarioServiceImpl extends ProjetoServiceImpl<Usuario, Integer> implements UsuarioService {

	@Autowired
	public void setDao(UsuarioDAO dao) {
		super.setDAO(dao);
	}

	@Override
	public void alterar(Usuario entidade) throws AlterarException {
		((UsuarioDAO) getDAO()).alterar(entidade);

	}

	@Override
	public void excluir(Usuario entidade) throws ExcluirException {
		((UsuarioDAO) getDAO()).excluir(entidade);

	}

	@Override
	public Usuario consultar(Integer id) throws ConsultarException, NaoEncontradoException {
		return ((UsuarioDAO) getDAO()).consultar(id);
	}
	
	@Override
	public List<Usuario> consultar(Map<String,Object> dados) throws ConsultarException, NaoEncontradoException {
		return ((UsuarioDAO) getDAO()).consultar(dados);
	}

	@Override
	public List<Usuario> listar() throws ConsultarException, NaoEncontradoException {
		return ((UsuarioDAO) getDAO()).listar();
	}

	@Override
	public void excluir(List<Usuario> grupos) throws ExcluirException {
		for (Usuario usuario : grupos) {
			((UsuarioDAO) getDAO()).excluir(usuario);
		}

	}

	public void save(Usuario grupo) throws IncluirException {
		incluir(grupo);

	}

	@Override
	public void incluir(List<Usuario> grupos) throws IncluirException {
		for (Usuario grupo : grupos) {
			incluir(grupo);
		}

	}

	@Override
	public void alterar(List<Usuario> pessoas) throws AlterarException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	public Usuario consultar(String nome, String senha) throws ConsultarException, NaoAfetadoException {
		return ((UsuarioDAO) getDAO()).consultar(nome, senha);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Object[] imprimir(Map<String,Object> dados) throws ConsultarException, NaoEncontradoException, Exception {
        
        
        // Cria os parâmetros do relatório (propriedades)
		Map<String, String> params = new HashMap<String, String>();              
        params.put("usuarioLogado", ((Usuario)dados.get("usuario")).getNome());
        
        // Cria a lista de fields, onde será um Map para cada ItemEntradaEstoque
        List<Map<String, Comparable>> fields = new ArrayList<Map<String, Comparable>>();
        
        for (Usuario item : listar()){        
            Map<String, Comparable> map = new HashMap<String, Comparable>();    
            map.put("id", item.getId());
            map.put("nome", item.getNome());
            map.put("login",item.getLogin());
            
            fields.add(map);
        }
                        
        Collections.sort(fields, new Comparator<Map<String, Comparable>>() {
            public int compare(Map<String, Comparable> m1, Map<String, Comparable> m2) {
                //Definindo o Locale para portugues, pois o padrão é o ingles e ele não trabalha bem com acentos
                Collator cot = Collator.getInstance(new Locale("pt", "BR"));                
                return cot.compare(m1.get("nome"), m2.get("nome")); //crescente
            }
        });
        
        //Variaveis criadas para atribuir o efeito de zebra do relatorio
        Integer idPessoa = 0;
        boolean imprimeFundo = true;
        
        for (Map<String, Comparable> item : fields) {
            if(item.get("id").compareTo(idPessoa) != 0){
                imprimeFundo = !imprimeFundo;
            }
            
            item.put("imprimeFundo", imprimeFundo);
            idPessoa = Integer.parseInt(item.get("id").toString());
        }     
        
        // Cria o objeto de saida, onde conterá:
		// 0 - HashMap com os parâmetros do relatório
		// 1 - Lista de HashMap para os fields do relatório, onde
		// cada hashmap irá encapsular um itemEntradaEstoque
		Object[] relatorio = new Object[2];		
		relatorio[0] = params;
		relatorio[1] = fields;        
                
        return relatorio;
        
    }
}