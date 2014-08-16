package br.com.projeto.controle;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.projeto.entidades.Usuario;
import br.com.projeto.enumeradores.Acao;
import br.com.projeto.exception.ConsultarException;
import br.com.projeto.exception.NaoAfetadoException;
import br.com.projeto.service.UsuarioService;

@SessionScoped
@ViewScoped
@ManagedBean
@SuppressWarnings("serial")
public class AutenticacaoControlador implements Serializable {
	
	@ManagedProperty("#{usuarioService}")
	protected UsuarioService usuarioService;
	
	protected UsuarioControlador usuarioControlador;
	
	protected Usuario usuario = new Usuario();
	
	protected Usuario usuarioLogado;
	
	protected boolean autorizado;
	
	protected static Logger logger = Logger.getLogger(AutenticacaoControlador.class.getName()); 
	
	protected String msg;
	
	protected HttpSession session;
	
	protected Acao acao;
	
	
	public AutenticacaoControlador(){
		
	}
	
    @PostConstruct
    public void construct() {
    	FacesContext fc = FacesContext.getCurrentInstance();
		session = (HttpSession) fc.getExternalContext().getSession(false);
    }
	
	 @PreDestroy
	    public void destroy() {
	        /* chamado quando outra view for chamada através do UIViewRoot.setViewId(String viewId) */
	    }
	
	public boolean validaDados(){
		boolean dados = true;
		if(usuario.getNome().equals("")){
			dados = false;
		}
		if(usuario.getSenha().equals("")){
			dados = false;
		}
			
		return dados;
	}
	
	public String logon(){
		
		String result = "";
		
		try {
			if(validaDados()){
				usuario = usuarioService.consultar(usuario.getNome(),usuario.getSenha());
			}
			
			if(usuario==null){
				result = "error";
				msg="Usuário/senha inválidos";
				autorizado = false;
			}
			else {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuario", usuario);
				
				result = "sucess";
				autorizado = true;
			}
			
		} catch (ConsultarException e) {
			logger.info("ConsultarException: " + e.getMessage());
		} catch (NaoAfetadoException e) {
			logger.info("NaoAfetadoException: " + e.getMessage());
		}
		return result;
	}
	
	public String logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("usuario", null);
		session.invalidate();
		
		usuario = new Usuario();
		
		return "logout";
	}
	
	public void mostrarPanel(){
		acao = Acao.MOSTRAR_TELA_INICIAL;
	}
	
	public Usuario getUsuario(){	
		return usuario;
	}
	
	public Usuario getUsuarioLogado(){
		return (Usuario) session.getAttribute("usuario");
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioControlador getUsuarioControlador() {
		return usuarioControlador;
	}

	public void setUsuarioControlador(UsuarioControlador usuarioControlador) {
		this.usuarioControlador = usuarioControlador;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
}
