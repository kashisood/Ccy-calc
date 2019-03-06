package com.anz.curr.exchange.springcore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Currency")
public class CurrencyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ccyCode;
	/**
	 * @return the ccyCode
	 */
	public String getCcyCode() {
		return ccyCode;
	}
	/**
	 * @param ccyCode the ccyCode to set
	 */
	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}