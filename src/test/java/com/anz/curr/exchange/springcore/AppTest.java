/**
 * 
 */
package com.anz.curr.exchange.springcore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.Currency;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anz.curr.exchange.springcore.Response.ResponseObject;
import com.anz.curr.exchange.springcore.processor.CurrencyProcessor;
import com.anz.curr.exchange.springcore.request.CurrencyRequest;

/**
 * @author Krishma
 *
 */
public class AppTest {

	@Mock
	private CurrencyProcessor currencyProcessorMock;
	@Mock
	private App appMock;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link com.anz.curr.exchange.springcore.App#calculate()}.
	 */
	@Test
	public final void testCalculate() {
		ByteArrayInputStream in = new ByteArrayInputStream("AUD 100 to DKK".getBytes());
		System.setIn(in);

		/*
		 * CurrencyRequest ccyReqMock = mock(CurrencyRequest.class);
		 * 
		 * ResponseObject respObj = new ResponseObject(); respObj.setStatus(true);
		 * respObj.setRespObj("505.76");
		 * 
		 * when(currencyProcessorMock.process(ccyReqMock)).thenReturn(respObj);
		 * 
		 * App a = new App(currencyProcessorMock); String resp = a.process(ccyReqMock);
		 * assertEquals(resp, "505.76");
		 * 
		 * CurrencyRequest ccyReqMock = mock(CurrencyRequest.class);
		 * 
		 * Optional<CurrencyRequest> optional = Optional.of(ccyReqMock); // cannot mock
		 * String[] to any / anything as String is final class //
		 * when(appMock.validateInput(null)).thenReturn(optional);
		 * 
		 * App a = new App(currencyProcessorMock);
		 * 
		 * when(appMock.process((CurrencyRequest)
		 * Matchers.any(CurrencyRequest.class))).thenReturn("505.76");
		 * appMock.calculate();
		 */
		// do your thing

		// optionally, reset System.in to its original
		System.setIn(System.in);
	}

	@Test
	public final void testdisplayResult() {
		App app = new App(null);
		CurrencyRequest ccyReqMock = new CurrencyRequest();
		ccyReqMock.setAmount(Float.valueOf("100"));
		ccyReqMock.setCcyFrom(Currency.getInstance("AUD"));
		ccyReqMock.setCcyTo(Currency.getInstance("DKK"));

		app.displayResult(ccyReqMock, "RateMappingNotFound");

	}

	/**
	 * Test method for
	 * {@link com.anz.curr.exchange.springcore.App#validateInput(java.lang.String[])}.
	 */

	@Test
	public final void testValidateInput() {
		App app = new App(null);
		String[] input = { "AUD", "100", "anything", "USD" };
		Optional<CurrencyRequest> validateInput = app.validateInput(input);
		assertNotNull(validateInput.get());
		assertEquals(validateInput.get().getCcyFrom(), Currency.getInstance("AUD"));
		assertEquals(validateInput.get().getCcyTo(), Currency.getInstance("USD"));
		assertEquals(validateInput.get().getAmount(), Float.valueOf("100"));
	}

	/**
	 * Test method for
	 * {@link com.anz.curr.exchange.springcore.App#process(com.anz.curr.exchange.springcore.request.CurrencyRequest)}.
	 */
	@Test
	public final void testProcess() {
		CurrencyRequest ccyReqMock = mock(CurrencyRequest.class);

		ResponseObject respObj = new ResponseObject();
		respObj.setStatus(true);
		respObj.setRespObj("505.76");

		when(currencyProcessorMock.process(ccyReqMock)).thenReturn(respObj);

		App a = new App(currencyProcessorMock);
		String resp = a.process(ccyReqMock);
		assertEquals(resp, "505.76");
	}

}
