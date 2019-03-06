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

	// @Autowired
	ResponseObject respObj;

	@Autowired
	ExchangeRate currencyExchangeRate; // currency exchange rate mapping cache

	@Autowired
	CurrencyExchangeMappingCache currencyExchangeMappingCache; // currency exchange rate derived from matrix cache

	@Override
	public ResponseObject process(CurrencyRequest t1) {

		String ccyFrom = t1.getCcyFrom().getCurrencyCode();
		String ccyTo = t1.getCcyTo().getCurrencyCode();
		String ccyCodeKey = ccyFrom + ccyTo;
		respObj = new ResponseObject();
		// check if value is available in local cache
		Double rate = null;
		if (ccyFrom.equalsIgnoreCase(ccyTo)) {
			rate = 1.0;
		} else {
			currencyExchangeRate.findExchangeRate(ccyCodeKey);
		}
		Double tempResp;
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
		// if rate found from either local cache or derived from matrix then rate is not
		// null
		if (rate != null) {
			tempResp = rate * t1.getAmount();
			BigDecimal bigd = new BigDecimal(tempResp).setScale(t1.getCcyTo().getDefaultFractionDigits(),
					RoundingMode.HALF_UP);
			respObj.setStatus(true);
			respObj.setRespObj(bigd.toString());
		}
		return respObj;
	}
}
