package br.com.projeto.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.projeto.beans.GrupoUsuarioBean;
import br.com.projeto.entidades.GrupoUsuario;
import br.com.projeto.enumeradores.Acao;
import br.com.projeto.enumeradores.EnumAtivoInativo;
import br.com.projeto.exception.AlterarException;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.ExcluirException;
import br.com.projeto.exception.IncluirException;
import br.com.projeto.exception.NaoEncontradoException;
import br.com.projeto.service.GrupoUsuarioService;

@ViewScoped
@ManagedBean
@SuppressWarnings("serial")
public class GrupoUsuarioControlador extends ProjetoControladorJSF implements IProjetoControladorJSF {

	@ManagedProperty("#{grupoUsuarioService}")
	protected GrupoUsuarioService grupoUsuarioService;

	protected GrupoUsuarioBean bean;

	protected List<GrupoUsuario> lista;
	
	protected List<SelectItem> status = new ArrayList<SelectItem>();

	private GrupoUsuario grupoUsuario;
	
	public GrupoUsuarioControlador(){
		bean = new GrupoUsuarioBean();
	}
	
	@PostConstruct
	public void construct(){
		try {
				lista = grupoUsuarioService.listar();
		} catch (ConsultarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void destruct(){
		System.out.println("destruct...");
		
	}
	
	public void novo() {
		bean = new GrupoUsuarioBean();
		acao = Acao.INCLUIR;
	}

	@Override
	public void consultar() {
		try {
			
			Map<String,Object> dados = new HashMap<String, Object>();

			if(bean.getIdPesquisa() == null   && bean.getDescricaoPesquisa() == null){
				lista = grupoUsuarioService.listar();
				
			}
			else{
				
				dados.put("id", bean.getIdPesquisa());
				dados.put("descricao", bean.getDescricaoPesquisa());
				
				lista = grupoUsuarioService.consultar(dados);				
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
			
			grupoUsuario = new GrupoUsuario();
			grupoUsuario.setDescricao(bean.getDescricao());
			grupoUsuario.setAtivo(bean.isAtivo());
			
			grupoUsuarioService.incluir(grupoUsuario);
			
			listar(); //atualiza item na datatable
			
			addMensagemFaces(MSG_INICIAL_INCLUIDO_SUCESSO,MSG_TIPO_INFORMACAO);
			
			acao = Acao.MOSTRAR_TELA_INICIAL;
			
		} catch (IncluirException e) {
			addMensagemFaces("msg", MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);

		}

	}
	
	@Override
	public void editar(Integer id) {
		try {
			
			grupoUsuario = grupoUsuarioService.consultar(id);

			bean.setId(grupoUsuario.getId());
			bean.setDescricao(grupoUsuario.getDescricao());
			bean.setAtivo(grupoUsuario.isAtivo());
			
			acao = Acao.MANTER;
			
		} catch (ConsultarException e) {
			addMensagemFaces("msg", MSG_INEXISTENCIA_DE_DADOS, MSG_TIPO_ERRO);
			e.printStackTrace();
		} catch (NaoEncontradoException e) {
			addMensagemFaces("msg", MSG_INEXISTENCIA_DE_DADOS, MSG_TIPO_ERRO);
			e.printStackTrace();
		}
		
	}

	@Override
	public void manter() {
		try {
			
			if(bean.getId() == null){
				/*Incluir*/
				incluir();
			}
			else{
				
				grupoUsuario = new GrupoUsuario();
				
				grupoUsuario.setId(bean.getId());
				grupoUsuario.setDescricao(bean.getDescricao());
				grupoUsuario.setAtivo(bean.isAtivo());
				
				grupoUsuarioService.alterar(grupoUsuario);
				addMensagemFaces(MSG_INICIAL_ALTERADO_SUCESSO,MSG_TIPO_INFORMACAO);
				
				listar();
				
				acao = Acao.MOSTRAR_TELA_INICIAL;
			}
			
		} catch (AlterarException e) {
			addMensagemFaces("msg", MSG_INEXISTENCIA_DE_DADOS+"/"+e.getMessage(), MSG_TIPO_ERRO);
		} 

	}
	
	@Override
	public void listar(){
		try {
			lista = grupoUsuarioService.listar();
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
		bean = new GrupoUsuarioBean();
	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(Integer id) {
		try {
			
			grupoUsuario = grupoUsuarioService.consultar(id);
			grupoUsuarioService.excluir(grupoUsuario);
			
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
	
	public void setGrupoUsuarioService(GrupoUsuarioService grupoUsuarioService) {
		this.grupoUsuarioService = grupoUsuarioService;
	}

	public GrupoUsuarioBean getBean() {
		if(bean == null){
			bean = new GrupoUsuarioBean();
		}
		return bean;
	}

	public void setBean(GrupoUsuarioBean bean) {
		this.bean = bean;
	}

	public List<GrupoUsuario> getLista() {
		return lista;
	}

	public void setLista(List<GrupoUsuario> lista) {
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
}
