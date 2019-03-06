package com.anz.curr.exchange.springcore.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.anz.curr.exchange.springcore.dao.CurrencyMappingDAO;

@Service
public class CurrencyExchangeMappingCache implements ApplicationRunner {

	private static Logger LOG = LoggerFactory.getLogger(CurrencyExchangeMappingCache.class);
	private static final Map<String, String> ccyExchangeMatrixCache = new ConcurrentHashMap<String, String>();

	private CurrencyMappingDAO currencyMappingDAO;

	@Autowired
	ExchangeRate currencyExchangeRate; // currency default exchange rate mapping cache

	@Autowired
	public CurrencyExchangeMappingCache(CurrencyMappingDAO currencyMappingDAO) {
		this.currencyMappingDAO = currencyMappingDAO;
	}

	public void run(ApplicationArguments args) {
		loadCcyExchangeMatrixCache();
	}

	private void loadCcyExchangeMatrixCache() {
		ccyExchangeMatrixCache.clear();
		ccyExchangeMatrixCache.putAll(currencyMappingDAO.fetchCurrencyMapping());
	}

	// recursive call
	public Double deriveExchangeRate(String fromCcy, String toCcy) {
		// currencyMappingDAO.findCurrencyMappingPath(fromCcy, toCcy);
		Boolean inverse = false;
		String str = findExchangeMapping(fromCcy + toCcy);
		if (str == null) {
			// try reverse
			str = findExchangeMapping(toCcy + fromCcy);
			if (str == null) {
				return null;
			} else {
				inverse = true;
			}
		}

		if ("D".equalsIgnoreCase(str) && !inverse) {
			return currencyExchangeRate.findExchangeRate(fromCcy + toCcy);
		} else if ("D".equalsIgnoreCase(str) && inverse) {
			return currencyExchangeRate.findInverseExchangeRate(toCcy + fromCcy);
		} else if (!inverse) {
			return deriveExchangeRate(fromCcy, str) * deriveExchangeRate(str, toCcy);
		} else {
			try {
				return 1 / (deriveExchangeRate(toCcy, str) * deriveExchangeRate(str, fromCcy));
			} catch (Exception e) {
				// in case of missing entries in database..
				// the deriveexchangeRate can throw null and 1/null can throw exception
				return null;
			}

		}
	}

	public String findExchangeMapping(String fromToCcy) {
		return ccyExchangeMatrixCache.get(fromToCcy);
	}

}
