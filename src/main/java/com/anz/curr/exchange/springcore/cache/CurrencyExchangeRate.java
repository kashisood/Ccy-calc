/**
 * 
 */
package com.anz.curr.exchange.springcore.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.curr.exchange.springcore.dao.ExchangeRateDao;

/**
 * @author Krishma
 *
 */

@Service
public class CurrencyExchangeRate implements ExchangeRate {

	private static Logger LOG = LoggerFactory.getLogger(CurrencyExchangeRate.class);
	private final Map<String, Double> exchangeRateCache = new ConcurrentHashMap<String, Double>();

	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public CurrencyExchangeRate() {
		super();
		// refresh cache every 10 minutes with fresh mappings pulled from DB/Service/feed whatever
		executor.scheduleAtFixedRate(() -> fetchExchangeRates(), 1000, (10 * 60 * 1000), TimeUnit.MILLISECONDS);
	}

	@Autowired
	private ExchangeRateDao exchangeRateDao;

	public void fetchExchangeRates() {
		LOG.info("Refreshing Exchange rates cache.");
		evictExchangeRatesCache();
		exchangeRateCache.putAll(exchangeRateDao.getRates());
	}

	@Override
	public Double findInverseExchangeRate(String fromToCcy) {
		Double d = null;
		d = exchangeRateCache.get(fromToCcy);
		if (d != null)
			return 1 / d;
		return d;
	}

	@Override
	public Double findExchangeRate(String fromToCcy) {
		return exchangeRateCache.get(fromToCcy);
	}

	@Override
	public void put(String key, Double value) {
		exchangeRateCache.put(key, value);
	}

	public void evictExchangeRatesCache() {
		LOG.info("Evict Exchange Rates, to refresh cache.");
		exchangeRateCache.clear();
	}


}
