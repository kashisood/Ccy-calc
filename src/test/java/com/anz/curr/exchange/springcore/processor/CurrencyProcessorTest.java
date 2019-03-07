/**
 * 
 */
package com.anz.curr.exchange.springcore.processor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Currency;

import org.junit.Test;
import org.mockito.Mock;

import com.anz.curr.exchange.springcore.Response.ResponseObject;
import com.anz.curr.exchange.springcore.cache.CurrencyExchangeMappingCache;
import com.anz.curr.exchange.springcore.cache.CurrencyExchangeRate;
import com.anz.curr.exchange.springcore.cache.ExchangeRate;
import com.anz.curr.exchange.springcore.request.CurrencyRequest;

/**
 * @author Krishma
 *
 */
public class CurrencyProcessorTest {

	@Mock
	private ExchangeRate currencyExchangeRate;
	@Mock
	private CurrencyExchangeMappingCache currencyExchangeMappingCache;

	/**
	 * Test method for
	 * {@link com.anz.curr.exchange.springcore.processor.CurrencyProcessor#process(com.anz.curr.exchange.springcore.request.CurrencyRequest)}.
	 */
	@Test
	public final void testProcess() {
		CurrencyRequest ccyReqMock = new CurrencyRequest();
		ccyReqMock.setAmount(Float.valueOf("100"));
		ccyReqMock.setCcyFrom(Currency.getInstance("AUD"));
		ccyReqMock.setCcyTo(Currency.getInstance("DKK"));

		ExchangeRate currencyExchangeRate1 = mock(CurrencyExchangeRate.class);
		CurrencyExchangeMappingCache currencyExchangeMappingCache1 = mock(CurrencyExchangeMappingCache.class);
		CurrencyProcessor cp = new CurrencyProcessor(currencyExchangeRate1, currencyExchangeMappingCache1);
		when(currencyExchangeRate1.findExchangeRate("AUDDKK")).thenReturn(null);
		when(currencyExchangeRate1.findInverseExchangeRate("DKKAUD")).thenReturn(null);
		when(currencyExchangeMappingCache1.deriveExchangeRate("AUD", "DKK")).thenReturn(5.0576);

		ResponseObject resp = cp.process(ccyReqMock);
		assertEquals("505.76", resp.getRespObj());
	}

	/**
	 * Test method for
	 * {@link com.anz.curr.exchange.springcore.processor.CurrencyProcessor#calcAmount(com.anz.curr.exchange.springcore.request.CurrencyRequest, java.lang.Double)}.
	 */
	@Test
	public final void testCalcAmount() {
		CurrencyRequest ccyReqMock = new CurrencyRequest();
		ccyReqMock.setAmount(Float.valueOf("100"));
		ccyReqMock.setCcyFrom(Currency.getInstance("AUD"));
		ccyReqMock.setCcyTo(Currency.getInstance("DKK"));

		CurrencyProcessor cp = new CurrencyProcessor(currencyExchangeRate, currencyExchangeMappingCache);
		ResponseObject resp = cp.calcAmount(ccyReqMock, 1.23);
		assertEquals("123.00", resp.getRespObj());
	}

}
