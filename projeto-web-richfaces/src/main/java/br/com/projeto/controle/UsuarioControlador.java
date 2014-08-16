package br.com.projeto.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.entidades.Usuario;
import br.com.projeto.enumeradores.Acao;
import br.com.projeto.enumeradores.EnumAtivoInativo;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.service.GrupoUsuarioService;
import br.com.projeto.service.UsuarioService;
import br.com.projeto.util.Relatorio;

@ViewScoped
@ManagedBean
@SuppressWarnings({"serial","static-access"})
public class UsuarioControlador extends ProjetoControladorJSF implements IProjetoControladorJSF {
	
	@ManagedProperty("#{grupoUsuarioService}")
	protected GrupoUsuarioService grupoUsuarioService;
	
	@ManagedProperty("#{usuarioService}")
	protected UsuarioService usuarioService;

	/*Representação da tela*/
	protected UsuarioBean bean;
	

	/*Lista de valores a serem exibidos na view*/
	protected List<Usuario> lista;
	
	/*Preenche a a listabox*/
	protected List<SelectItem> listaGrupos = new ArrayList<SelectItem>();
	
	/*Percorre o EnumAtivoInativo utilizando a classe SelectItem nativa da jsf-api*/
	protected List<SelectItem> status = new ArrayList<SelectItem>();

	/*Entidade*/
	private Usuario usuario;
	
	/*relatório*/
	protected byte[] bytes;
	
	
	public UsuarioControlador(){
		bean = new UsuarioBean();
	}
	

	
	@PostConstruct
	public void construct(){
		try {
				lista = usuarioService.listar();
				listaGrupos = montarComboJSF(grupoUsuarioService.listar(), true, "");
		} catch (ConsultarException e) {
			logger.getLogger("ConsultarException: "+ e.getMessage());
		} catch (NaoEncontradoException e) {
			lista = null;
			listaGrupos = new ArrayList<SelectItem>();
			logger.getLogger("NaoEncontradoException: "+ e.getMessage());
		}
	}

	@Override
	public void destruct() {
		System.out.println("destruct...");
		
	}

	@Override
	public void novo() {
		bean = new UsuarioBean();
		acao = Acao.INCLUIR;
	}

	@Override
	public void consultar() {
		try {
			
			Map<String,Object> dados = new HashMap<String, Object>();

			if(bean.getIdPesquisa() == null   && bean.getDescricaoPesquisa() == null && bean.getGrupoUsuario() == null){
				lista = usuarioService.listar();
				
			}
			else{
				
				dados.put("id", bean.getIdPesquisa());
				dados.put("grupo",bean.getGrupoUsuario());
				dados.put("descricao", bean.getDescricaoPesquisa());
				
				lista = usuarioService.consultar(dados);				
			}
				
			acao =  Acao.LISTAR;

		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}

	}

	@Override
	public void incluir() {		
		try {
			
			usuario = new Usuario();
			usuario.setLogin(bean.getLogin());
			usuario.setNome(bean.getNome());
			usuario.setSenha(bean.getSenha());
			usuario.setGrupoUsuario(grupoUsuarioService.consultar(bean.getGrupoUsuario()));
			usuario.setAtivo(bean.isAtivo());
			
			usuarioService.incluir(usuario);
			
			listar(); //atualiza item na datatable
			
			addMensagemFaces(MSG_INICIAL_INCLUIDO_SUCESSO,MSG_TIPO_INFORMACAO);
			
			acao = Acao.MOSTRAR_TELA_INICIAL;
			
		} catch (IncluirException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}
	}
	
	@Override
	public void editar(Integer id) {
		try {
			
			usuario = usuarioService.consultar(id);
			
			bean.setId(usuario.getId());
			bean.setLogin(usuario.getLogin());
			bean.setNome(usuario.getNome());
			bean.setGrupoUsuario(usuario.getGrupoUsuario().getId());
			bean.setAtivo(usuario.isAtivo());
			
			acao = Acao.MANTER;
			
		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}
		
	}

	@Override
	public void manter() {
		try {
			
			if (validaDados()) {
				if (bean.getId() == null) {
					/* Incluir */
					incluir();
				} else {
					
					usuario = new Usuario();
					
					usuario.setId(bean.getId());
					usuario.setLogin(bean.getLogin());
					usuario.setSenha(bean.getSenha());
					usuario.setNome(bean.getNome());
					usuario.setGrupoUsuario(grupoUsuarioService.consultar(bean.getGrupoUsuario()));
					usuario.setAtivo(bean.isAtivo());

					usuarioService.alterar(usuario);

					addMensagemFaces(MSG_INICIAL_ALTERADO_SUCESSO, MSG_TIPO_INFORMACAO);

					listar();

					acao = Acao.MOSTRAR_TELA_INICIAL;
				}
			}
		} catch (AlterarException e) {
			addMensagemFaces("msg", MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}
	}
	
	public void manterLogin(){
		
		try {
			
			usuario = new Usuario();
			usuario.setLogin(bean.getLogin());
			usuario.setNome(bean.getNome());
			usuario.setSenha(bean.getSenha());
			usuario.setGrupoUsuario(grupoUsuarioService.consultar(bean.getGrupoUsuario()));
			usuario.setAtivo(bean.isAtivo());
			
			usuarioService.incluir(usuario);
			addMensagemFaces(MSG_INICIAL_INCLUIDO_SUCESSO,MSG_TIPO_INFORMACAO);
		
		} catch (IncluirException e) {
			logger.info(MSG_INEXISTENCIA_DE_DADOS);
		}catch (ConsultarException e) {
			logger.info(MSG_INEXISTENCIA_DE_DADOS);
		} catch (NaoEncontradoException e) {
			logger.info(MSG_INEXISTENCIA_DE_DADOS);
		}
	}
	
	public boolean validaDados(){
		boolean dados = true;
		
		if(bean.getLogin() == null){
			dados = false;
		}
		
		if(bean.getNome() == null){
			dados = false;
		}
		
		if(bean.getSenha() == null || bean.getConfirmaSenha() == null){
			dados = false;
		}
		
		if(!bean.getSenha().equals(bean.getConfirmaSenha())){
			dados = false;
		}
		
		return dados;
	}

	@Override
	public void listar(){
		try {
			lista = usuarioService.listar();
		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			lista = null;
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}
	}
	
	public void voltar(){
		listar();
		acao = Acao.MOSTRAR_TELA_INICIAL;
		bean = new UsuarioBean();
	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer id) {
		try {
			
			usuario = usuarioService.consultar(id);
			usuarioService.excluir(usuario);
			
			addMensagemFaces(MSG_INICIAL_EXCLUIDO_SUCESSO,MSG_TIPO_INFORMACAO);
			
			listar();
			
			acao = Acao.MOSTRAR_TELA_INICIAL;
			
		} catch (ConsultarException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} catch (NaoEncontradoException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}  catch (ExcluirException e) {
			addMensagemFaces(MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void imprimir() {
        try {
        	
        	Map<String,Object> map = new HashMap<String, Object>();
        	map.put("usuario",getUsuarioLogado());
        	
            Object[] dados = usuarioService.imprimir(map);
            
			Map<String, String> params = (Map<String, String>) dados[0];
			List<Map<String, String>> fields = (List<Map<String, String>>) dados[1];
			
            Relatorio relatorio = new Relatorio();
           bytes = relatorio.gerarRelatorioJasper("contatoLista.jasper", fields, params, "P");
            
            
            
		} catch (JRException e) {
			addMensagemFaces(e.getMessage(), MSG_TIPO_ERRO);			
		} catch (Exception e) {
			addMensagemFaces(e.getMessage(), MSG_TIPO_ERRO);
		}
    }
	
	
	/**
	 * Método que exibe o relatório na tela.
	 * Faz o download no client-side.
	 */
    public void exibirRelatorio() {
    	super.exibirRelatorio("P", bytes, new StringBuffer("contatoLista"));
    }


	public UsuarioBean getBean() {
		return bean;
	}



	public void setBean(UsuarioBean bean) {
		this.bean = bean;
	}



	public List<Usuario> getLista() {
		return lista;
	}



	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}



	public List<SelectItem> getStatus() {
		try {
			return EnumAtivoInativo.getListaStatusEnumAtivoInativo();
		} catch (Exception e) {
		}
		return status != null ? status : new ArrayList<SelectItem>();
	}



	public void setStatus(List<SelectItem> status) {
		this.status = status;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public void setGrupoUsuarioService(GrupoUsuarioService grupoUsuarioService) {
		this.grupoUsuarioService = grupoUsuarioService;
	}



	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<SelectItem> getListaGrupos() {

		try {

			if (listaGrupos == null || listaGrupos.isEmpty()) {
				listaGrupos = montarComboJSF(grupoUsuarioService.listar(), true, "");
			}
		} catch (ConsultarException e) {
			listaGrupos = new ArrayList<SelectItem>();
		} catch (NaoEncontradoException e) {
			listaGrupos = new ArrayList<SelectItem>();
		}
		return listaGrupos != null ? listaGrupos : new ArrayList<SelectItem>();
	}

	public void setListaGrupos(List<SelectItem> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


}
