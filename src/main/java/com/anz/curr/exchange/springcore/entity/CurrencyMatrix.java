package com.anz.curr.exchange.springcore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currency_Matrix")
public class CurrencyMatrix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String baseId;
	private String termId;
	private String mappingValue;
	/**
	 * @return the baseId
	 */
	public String getBaseId() {
		return baseId;
	}
	/**
	 * @param baseId the baseId to set
	 */
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	/**
	 * @return the termId
	 */
	public String getTermId() {
		return termId;
	}
	/**
	 * @param termId the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}
	/**
	 * @return the mappingValue
	 */
	public String getMappingValue() {
		return mappingValue;
	}
	/**
	 * @param mappingValue the mappingValue to set
	 */
	public void setMappingValue(String mappingValue) {
		this.mappingValue = mappingValue;
	}

}