package br.com.projeto.enumeradores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public enum EnumAtivoInativo {
	
	ATIVO(true,"Ativo"),
    INATIVO(false,"Inativo");
    
    private final String descricao;
    private final boolean codigo;

    private EnumAtivoInativo(boolean codigo, String descricao) {
		this.codigo = codigo;
        this.descricao = descricao;
    }

    public boolean getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static EnumAtivoInativo parse(boolean codigo){
        for (EnumAtivoInativo item : EnumAtivoInativo.values()) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

	@Override  
    public String toString() {  
        return this.descricao;  
    }
	
	/*
	 * Relação de status usado na página de Solicitação de Consumo
	 */
	public static List<SelectItem> getListaStatusEnumAtivoInativo() {
    	List<SelectItem> lista = new ArrayList<SelectItem>();    	  
//    	lista.add(new SelectItem("", "Selecione um valor"));
        for (EnumAtivoInativo item : EnumAtivoInativo.values()) {
            lista.add(new SelectItem(item.getCodigo(), item.getDescricao()));            
        }
    	return lista;
    }

}
