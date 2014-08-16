package br.com.projeto.controle;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.entidades.Usuario;
import br.com.projeto.enumeradores.Acao;
import br.com.projeto.framework.jsf.AbstractControlJSF;

public abstract class ProjetoControladorJSF extends AbstractControlJSF implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3437318616632605905L;
	public static final String MSG_INICIAL_INCLUIDO_SUCESSO = "Informações incluidas com sucesso.";
	public static final String MSG_INICIAL_ALTERADO_SUCESSO = "Informações alteradas com sucesso.";
	public static final String MSG_INICIAL_EXCLUIDO_SUCESSO = "Informações excluidas com sucesso.";
	public static final String MSG_INEXISTENCIA_DE_DADOS = "Não há informações registradas para essa funcionalidade.";
	public static final String MSG_ERRO_GENERICA = "Ocorreu um erro ao executar a operação.";
	public static final String MSG_ERRO_VISUALIZAR_ARQUIVO = "Ocorreu um problema ao tentar visualizar o arquivo.";
	public String msg;
	public Acao acao;
	private Integer qtdeLinhas = 10;

	protected static Logger logger = Logger.getLogger(ProjetoControladorJSF.class.getName());  

	public String getMsg() {
		Iterator<FacesMessage> mensagem = FacesContext.getCurrentInstance().getMessages();

		if (mensagem.hasNext()) {
			FacesMessage facesMessage = (FacesMessage) mensagem.next();
			this.msg = facesMessage.getDetail();
		}
		return msg;

	}

	public void addMensagemFaces(String msg) {
		this.addMensagemFaces(msg, FacesMessage.SEVERITY_ERROR);
	}

	public void addMensagemFaces(String msg, FacesMessage.Severity tipoErro) {
		FacesMessage mensagem = new FacesMessage(msg);
		FacesContext context = FacesContext.getCurrentInstance();
		mensagem.setSeverity(tipoErro);
		context.addMessage(null, mensagem);
	}

	public void addMensagemFaces(String idComponent, String msg,
			FacesMessage.Severity tipoErro) {
		FacesMessage mensagem = new FacesMessage(msg);
		FacesContext context = FacesContext.getCurrentInstance();
		mensagem.setSeverity(tipoErro);
		context.addMessage(idComponent, mensagem);
	}

	public Acao getAcao() {
		acao = acao == null ? Acao.MOSTRAR_TELA_INICIAL : acao;
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Integer getQtdeLinhas() {
		return qtdeLinhas;
	}

	public void setQtdeLinhas(Integer qtdeLinhas) {
		this.qtdeLinhas = qtdeLinhas;
	}

	public void exibirRelatorio(String tipo, byte[] bytes,
			StringBuffer nomeArquivo) {

		try {

			if (bytes != null && bytes.length > 0) {

				FacesContext fContext = FacesContext.getCurrentInstance();
				ExternalContext externalContext = fContext.getExternalContext();

				HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

				if (tipo.equals("P")) {
					response.setContentType("application/pdf");
					nomeArquivo.append(".pdf").toString();
					response.addHeader("content-disposition","attachment;filename=" + nomeArquivo);
				} else if (tipo.equals("X")) {
					response.setContentType("application/vnd.ms-excel");
					nomeArquivo.append(".xls").toString();
					response.addHeader("content-disposition","attachment;filename=" + nomeArquivo);
				} else if (tipo.equals("D")) {
					response.setContentType("application/rtf");
					nomeArquivo.append(".rtf").toString();
					response.addHeader("content-disposition","attachment;filename=" + nomeArquivo);
				}

				response.setContentLength(bytes.length);

				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
				fContext.responseComplete();

			} else {
				addMensagemFaces("Nenhuma informação encontrada para os dados informados. ",FacesMessage.SEVERITY_WARN);
			}

		} catch (Exception e) {
			addMensagemFaces("Ocorreu um problema ao exibir o relatório.", FacesMessage.SEVERITY_ERROR);
		}

	}

	public Usuario getUsuarioLogado() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		return (Usuario) session.getAttribute("usuario");		
	}

}