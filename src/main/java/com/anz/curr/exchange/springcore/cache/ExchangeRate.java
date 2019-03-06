package com.anz.curr.exchange.springcore.cache;

import java.util.Map;

public interface ExchangeRate {

	//Map<String, Double> findExchangeRates();

	Double findExchangeRate(String fromToCcy);

	void put(String key, Double value);

	Double findInverseExchangeRate(String fromToCcy);
}
