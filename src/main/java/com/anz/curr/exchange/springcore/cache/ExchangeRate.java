package com.anz.curr.exchange.springcore.cache;

public interface ExchangeRate {

	Double findExchangeRate(String fromToCcy);

	void put(String key, Double value);

	Double findInverseExchangeRate(String fromToCcy);
}
