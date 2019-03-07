package com.anz.curr.exchange.springcore.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anz.curr.exchange.springcore.Response.ResponseObject;
import com.anz.curr.exchange.springcore.cache.CurrencyExchangeMappingCache;
import com.anz.curr.exchange.springcore.cache.ExchangeRate;
import com.anz.curr.exchange.springcore.request.CurrencyRequest;

@Component("currencyProcessor")
public class CurrencyProcessor implements Processor<CurrencyRequest> {

	// currency exchange rate mapping cache
	private ExchangeRate currencyExchangeRate;
	// currency exchange rate derived from matrix cache
	private CurrencyExchangeMappingCache currencyExchangeMappingCache;

	/**
	 * @param currencyExchangeRate
	 * @param currencyExchangeMappingCache
	 */
	@Autowired
	public CurrencyProcessor(ExchangeRate currencyExchangeRate,
			CurrencyExchangeMappingCache currencyExchangeMappingCache) {
		super();
		this.currencyExchangeRate = currencyExchangeRate;
		this.currencyExchangeMappingCache = currencyExchangeMappingCache;
	}

	@Override
	public ResponseObject process(CurrencyRequest curReq) {

		ResponseObject respObj = null;
		String ccyFrom = curReq.getCcyFrom().getCurrencyCode();
		String ccyTo = curReq.getCcyTo().getCurrencyCode();
		String ccyCodeKey = ccyFrom + ccyTo;
		// check if value is available in local cache
		Double rate = null;
		if (ccyFrom.equalsIgnoreCase(ccyTo)) {
			rate = 1.0;
		} else {
			rate = currencyExchangeRate.findExchangeRate(ccyCodeKey);
		}
		if (rate == null) {
			// find if inverse exchange rate exists in local cache
			rate = currencyExchangeRate.findInverseExchangeRate(ccyTo + ccyFrom);
			// fetch a mapping via currency matrix if inverse also not found
			if (rate == null) {
				rate = currencyExchangeMappingCache.deriveExchangeRate(ccyFrom, ccyTo);
			}
			// if mapping found: update local cache for quick fetch next time
			if (rate != null) {
				currencyExchangeRate.put(ccyCodeKey, rate);
			}
		}
		respObj = calcAmount(curReq, rate);
		return respObj;
	}

	protected ResponseObject calcAmount(CurrencyRequest curReq, Double rate) {
		ResponseObject respObj = new ResponseObject();
		// if rate found from local cache or derived from matrix then rate not null
		if (rate != null) {
			Double tempResp = rate * curReq.getAmount();
			BigDecimal bigd = new BigDecimal(tempResp).setScale(curReq.getCcyTo().getDefaultFractionDigits(),
					RoundingMode.HALF_UP);
			respObj.setStatus(true);
			respObj.setRespObj(bigd.toString());
		}
		return respObj;
	}

}
