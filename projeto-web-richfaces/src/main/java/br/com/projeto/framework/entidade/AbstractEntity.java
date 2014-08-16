package br.com.projeto.framework.entidade;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable {


	/**
	 * Retorna a chave primária da entidade.
	 * @return Object
	 */
	public abstract Object getPrimaryKey();

	/**
	 * Retorna um resumo da classe.
	 * Deve ser sobrescrito nas classes que serão usadas em combos.
	 * @return String
	 */
	public SummaryEntity getSummaryEntity() {
		return null;
	}
	
	public class SummaryEntity{
		private Object identifier;
		private String description;
		
		/**
		 * Construtor padrão.
		 * @param identifier Object
		 * @param description String
		 */
		public SummaryEntity(Object identifier, String description) {
			this.setIdentifier(identifier);
			this.setDescription(description);
		}

		/**
		 * Retorna o atributo identifier.
		 * @return Object
		 */
		public Object getIdentifier() {
			return identifier;
		}
		
		/**
		 * Seta o atributo identifier.
		 * @param identifier Object
		 */
		public void setIdentifier(Object identifier) {
			this.identifier = identifier;
		}
		
		/**
		 * Retorna o atributo description.
		 * @return String
		 */
		public String getDescription() {
			return description;
		}
		
		/**
		 * Seta o atributo description.
		 * @param description String
		 */
		public void setDescription(String description) {
			this.description = description;
		}
	}
}