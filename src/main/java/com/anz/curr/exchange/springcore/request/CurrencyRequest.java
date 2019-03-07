package com.anz.curr.exchange.springcore.request;

import java.util.Currency;

/**
 * @author Krishma
 *
 */
public class CurrencyRequest {

	private Currency ccyFrom;
	private Currency ccyTo;
	private Float amount;

	/**
	 * @param ccyFrom
	 * @param ccyTo
	 * @param amount
	 */
	// @Autowired
	public CurrencyRequest(Currency ccyFrom, Currency ccyTo, Float amount) {
		super();
		this.ccyFrom = ccyFrom;
		this.ccyTo = ccyTo;
		this.amount = amount;
	}

	public CurrencyRequest() {
		super();
	}

	/**
	 * @return the ccyFrom
	 */
	public Currency getCcyFrom() {
		return ccyFrom;
	}

	/**
	 * @param ccyFrom The currency to convert from
	 */
	public void setCcyFrom(Currency ccyFrom) {
		this.ccyFrom = ccyFrom;
	}

	/**
	 * @return the ccyTo
	 */
	public Currency getCcyTo() {
		return ccyTo;
	}

	/**
	 * @param ccyTo The currency to be converted to
	 */
	public void setCcyTo(Currency ccyTo) {
		this.ccyTo = ccyTo;
	}

	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to be converted
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
}
