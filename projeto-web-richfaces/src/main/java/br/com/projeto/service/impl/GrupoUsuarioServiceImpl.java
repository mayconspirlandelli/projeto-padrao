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

import br.com.projeto.dao.GrupoUsuarioDAO;
import br.com.projeto.entidades.GrupoUsuario;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.service.GrupoUsuarioService;

@Service("grupoUsuarioService")
@Transactional
@SuppressWarnings("unchecked")
public class GrupoUsuarioServiceImpl extends ProjetoServiceImpl<GrupoUsuario, Integer> implements GrupoUsuarioService {

	@Autowired
	public void setDao(GrupoUsuarioDAO dao) {
		super.setDAO(dao);
	}

	@Override
	public void alterar(GrupoUsuario entidade) throws AlterarException {
		((GrupoUsuarioDAO) getDAO()).alterar(entidade);

	}

	@Override
	public void excluir(GrupoUsuario entidade) throws ExcluirException {
		((GrupoUsuarioDAO) getDAO()).excluir(entidade);

	}

	@Override
	public GrupoUsuario consultar(Integer id) throws ConsultarException, NaoEncontradoException {
		return ((GrupoUsuarioDAO) getDAO()).consultar(id);
	}
	
	@Override
	public List<GrupoUsuario> consultar(Map<String,Object> dados) throws ConsultarException, NaoEncontradoException {
		return ((GrupoUsuarioDAO) getDAO()).consultar(dados);
	}

	@Override
	public List<GrupoUsuario> listar() throws ConsultarException, NaoEncontradoException {
		return ((GrupoUsuarioDAO) getDAO()).listar();
	}

	@Override
	public void excluir(List<GrupoUsuario> grupos) throws ExcluirException {
		for (GrupoUsuario grupoUsuario : grupos) {
			((GrupoUsuarioDAO) getDAO()).excluir(grupoUsuario);
		}

	}

	public void save(GrupoUsuario grupo) throws IncluirException {
		incluir(grupo);

	}

	@Override
	public void incluir(List<GrupoUsuario> grupos) throws IncluirException {
		for (GrupoUsuario grupo : grupos) {
			incluir(grupo);
		}

	}

	@Override
	public void alterar(List<GrupoUsuario> grupos) throws AlterarException {
		for (GrupoUsuario grupo : grupos) {
			alterar(grupo);
		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] imprimir() throws ConsultarException, NaoEncontradoException, Exception {
        
        
        // Cria os parâmetros do relatório (propriedades da aquisição e do fornecedor)
		Map<String, String> params = new HashMap<String, String>();              
        params.put("nomeUsuario", "Usuário Teste");
        
        // Cria a lista de fields, onde será um Map para cada ItemEntradaEstoque
        List<Map<String, Comparable>> fields = new ArrayList<Map<String, Comparable>>();
        
        for (GrupoUsuario item : listar()){        
            Map<String, Comparable> map = new HashMap<String, Comparable>();    
//            map.put("idItemEntradaEstoque", String.valueOf(item.getId()));
            map.put("idPessoa", item.getId());
//            map.put("nome",item.getNome());
//            map.put("email", item.getEmail());
//            map.put("idade", item.getIdade());
//            map.put("informacoes", item.getInformacoes());
//          
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
            if(item.get("idPessoa").compareTo(idPessoa) != 0){
                imprimeFundo = !imprimeFundo;
            }
            
            item.put("imprimeFundo", imprimeFundo);
            idPessoa = Integer.parseInt(item.get("idPessoa").toString());
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