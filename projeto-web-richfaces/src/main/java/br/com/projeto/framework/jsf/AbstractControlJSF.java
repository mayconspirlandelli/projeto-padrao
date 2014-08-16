package br.com.projeto.framework.jsf;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import br.com.projeto.framework.controle.AbstractControl;
import br.com.projeto.framework.entidade.AbstractEntity;
import br.com.projeto.framework.entidade.AbstractEntity.SummaryEntity;

public abstract class AbstractControlJSF extends AbstractControl {
	
	public static final FacesMessage.Severity MSG_TIPO_AVISO = FacesMessage.SEVERITY_WARN;
	public static final FacesMessage.Severity MSG_TIPO_ERRO = FacesMessage.SEVERITY_ERROR;
	public static final FacesMessage.Severity MSG_TIPO_CONFIRMACAO = FacesMessage.SEVERITY_FATAL;
	public static final FacesMessage.Severity MSG_TIPO_INFORMACAO = FacesMessage.SEVERITY_INFO;

	/**
	 * Retorna o FacesContext atual.
	 * @return FacesContext
	 */
	public static FacesContext getContexto() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Adiciona uma mensagem de erro ao contexto jsf.
	 * @param sumario String
	 */
	public void adMsgErro(String sumario) {
		this.adMsg(null, MSG_TIPO_ERRO, sumario, null);
	}

	/**
	 * Adiciona uma mensagem de aviso ao contexto jsf.
	 * @param sumario String
	 */
	public void adMsgAviso(String sumario) {
		this.adMsg(null, MSG_TIPO_AVISO, sumario, null);
	}

	/**
	 * Adiciona uma mensagem de confirmacao ao contexto jsf.
	 * @param sumario String
	 */
	public void adMsgConfirmacao(String sumario) {
		this.adMsg(null, MSG_TIPO_CONFIRMACAO, sumario, null);
	}
	
	/**
	 * Adiciona uma mensagem de informação ao contexto jsf.
	 * @param sumario String
	 */
	public void adMsg(String sumario) {
		this.adMsg(null, null, sumario, null);
	}

	/**
	 * Adiciona uma mensagem ao contexto jsf.
	 * @param tipo FacesMessage.Severity. Padrão é SEVERITY_INFO.
	 * @param sumario String
	 */
	public void adMsg(FacesMessage.Severity tipo, String sumario) {
		this.adMsg(null, tipo, sumario, null);
	}
	
	/**
	 * Adiciona uma mensagem ao contexto jsf.
	 * @param tipo FacesMessage.Severity. Padrão é SEVERITY_INFO.
	 * @param sumario String
	 * @param detalhe String
	 */
	public void adMsg(FacesMessage.Severity tipo, String sumario, String detalhe) {
		this.adMsg(null, tipo, sumario, detalhe);
	}
	
	/**
	 * Adiciona uma mensagem ao contexto jsf.
	 * @param idCampo String
	 * @param tipo FacesMessage.Severity. Padrão é SEVERITY_INFO.
	 * @param sumario String
	 * @param detalhe String
	 */
	public void adMsg(String idCampo, FacesMessage.Severity tipo, String sumario, String detalhe) {
		FacesMessage fMsg = new FacesMessage();
		
		if(detalhe != null){
			fMsg.setDetail(detalhe);
		}
		if(tipo != null){
			fMsg.setSeverity(tipo);
		}
		if(sumario != null){
			fMsg.setSummary(sumario);
		}
		
		AbstractControlJSF.getContexto().addMessage(idCampo, fMsg);
	}
	
	/**
	 * Retorna os dados necessários as validações posteriores.
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getDados() {
		Map<String,Object> dados = new LinkedHashMap();
		
		for(Iterator itMsg = AbstractControlJSF.getContexto().getMessages(); itMsg.hasNext();) {
			FacesMessage msgJSF = (FacesMessage) itMsg.next();

			if(msgJSF.getSeverity().equals(MSG_TIPO_AVISO)) {
				dados.put("msgTipoAviso", true);
			}
			else if(msgJSF.getSeverity().equals(MSG_TIPO_ERRO)) {
				dados.put("msgTipoErro", true);
			}
			else if(msgJSF.getSeverity().equals(MSG_TIPO_CONFIRMACAO)) {
				dados.put("msgTipoConfirmacao", true);
			}
			else if(msgJSF.getSeverity().equals(MSG_TIPO_INFORMACAO)) {
				dados.put("msgTipoInfo", true);
			}
		}
		
		return dados;
	}

	/**
	 * Retorna o valor do parâmetro JSF.
	 * @param e ActionEvent
	 * @param nomeParametro String
	 * @return Object
	 */
	public static Object getParametroJSF(ActionEvent e, String nomeParametro) {
		UIParameter param = (UIParameter) e.getComponent().findComponent(nomeParametro);
		if(param != null) {
			return param.getValue();
		}
		
		return null;
	}

	/**
	 * Retorna o objeto no request.
	 * @param nomeParametro String
	 * @return Object
	 */
	public static Object getParametroRequest(String nomeParametro) {
		Object obj = AbstractControlJSF.getContexto().getExternalContext().getRequestMap().get(nomeParametro);
		if(obj==null){
			FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(nomeParametro);
		}
		return obj; 
	}
	
	/**
	 * Retorna o objeto no request.
	 * @param nomeParametro String
	 * @param obj Object
	 */
	public static void setParametroRequest(String nomeParametro, Object obj) {
		AbstractControlJSF.getContexto().getExternalContext().getRequestMap().put(nomeParametro, obj);
	}
	
	/**
	 * Remove o parâmetro no request.
	 * @param nomeParametro String
	 */
	public static void removeParametroRequest(String nomeParametro) {
		AbstractControlJSF.getContexto().getExternalContext().getRequestMap().remove(nomeParametro);
	}
	
	/**
	 * Coloca o parâmetro na sessão.
	 * @param nomeParametro String
	 * @param obj Object
	 */
	public static void setParametroSessao(String nomeParametro, Object obj) {
		AbstractControlJSF.getContexto().getExternalContext().getSessionMap().put(nomeParametro, obj);
	}
	
	/**
	 * Retorna o parâmetro na sessão.
	 * @param nomeParametro String
	 */
	public static Object getParametroSessao(String nomeParametro) {
		return AbstractControlJSF.getContexto().getExternalContext().getSessionMap().get(nomeParametro);
	}

	/**
	 * Remove o parâmetro na sessão.
	 * @param nomeParametro String
	 */
	public static void removeParametroSessao(String nomeParametro) {
		AbstractControlJSF.getContexto().getExternalContext().getSessionMap().remove(nomeParametro);
	}
	
	/**
	 * Converte uma lista AbstractEntity para uma lista de itens da combo jsf.
	 * @param listaDominio List<? extends AbstractEntity>
	 * @return List<SelectItem>
	 */
	public static List<SelectItem> montarComboJSF(List<? extends AbstractEntity> listaEntidade) {
		return AbstractControlJSF.montarComboJSF(listaEntidade, false, null);
	}
	
	/**
	 * Converte uma lista AbstractEntity para uma lista de itens da combo jsf.
	 * @param listaDominio List<? extends AbstractEntity>
	 * @param itemVazioInicio boolean
	 * @param valorItem Object
	 * @return List<SelectItem>
	 */
	public static List<SelectItem> montarComboJSF(List<? extends AbstractEntity> listaEntidade, boolean itemVazioInicio, Object valorItem) {		
		return preencherListaCombo(listaEntidade, itemVazioInicio, valorItem, Boolean.FALSE);
	}
	
	/**
	 * Converte uma lista AbstractEntity para uma lista de itens da combo jsf.
	 * @param listaDominio List<? extends AbstractEntity>
	 * @param itemVazioInicio boolean
	 * @param valorItem Object
	 * @return List<SelectItem>
	 */
	public static List<SelectItem> montarComboJSF(List<? extends AbstractEntity> listaEntidade, boolean itemVazioInicio, Object valorItem, boolean selecionarQdoApenasUm) {		
		return preencherListaCombo(listaEntidade, itemVazioInicio, valorItem, selecionarQdoApenasUm);
	}
	
	private static List<SelectItem> preencherListaCombo(List<? extends AbstractEntity> listaEntidade, boolean itemVazioInicio, Object valorItem, boolean selecionarQdoApenasUm) {
		List<SelectItem> lista = new LinkedList<SelectItem>();
		SummaryEntity summaryEntity;
		
		if(itemVazioInicio && (listaEntidade.size() > 1)) {
			lista.add(new SelectItem(null, (String) valorItem));
		}
		
		for(AbstractEntity entidade : listaEntidade) {
			summaryEntity = entidade.getSummaryEntity();
			if(summaryEntity != null) {
				lista.add(new SelectItem(summaryEntity.getIdentifier(), summaryEntity.getDescription()));
			}
		}
		
		return lista;
	}
	
	/**
	 * Retorna a descrição do item da combo.
	 * @param lista List<SelectItem>
	 * @param valor Object
	 * @return String
	 */
	public static String getDescricaoCombo(List<SelectItem> lista, Object valor) {
		if(lista != null && valor != null) {
			for(SelectItem selectItem : lista) {
				if(selectItem.getValue().equals(valor)) {
					return selectItem.getLabel();
				}
			}
		}
		return null;
	}
	
	/**
	 * Retorna o endereço raiz do contexto.
	 * @return String
	 */
	public static String getEnderecoRaizContexto() {
		return ((ServletContext) AbstractControlJSF.getContexto().getExternalContext().getContext()).getRealPath("");
	}
}