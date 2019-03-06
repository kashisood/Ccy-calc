package com.anz.curr.exchange.springcore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RateMap")
public class RateMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ccy1ccy2;
	private double rate;
	/**
	 * @return the ccy1ccy2
	 */
	public String getCcy1ccy2() {
		return ccy1ccy2;
	}
	/**
	 * @param ccy1ccy2 the ccy1ccy2 to set
	 */
	public void setCcy1ccy2(String ccy1ccy2) {
		this.ccy1ccy2 = ccy1ccy2;
	}
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
}