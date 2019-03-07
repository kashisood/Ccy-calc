package com.anz.curr.exchange.springcore;

import java.util.Currency;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anz.curr.exchange.springcore.Response.ResponseObject;
import com.anz.curr.exchange.springcore.processor.CurrencyProcessor;
import com.anz.curr.exchange.springcore.request.CurrencyRequest;

/**
 * Currency Exchange calculator application!
 *
 */
@Component
public class App {

	private static Logger LOG = LoggerFactory.getLogger(App.class);

	/**
	 * @param currencyProcessor
	 */
	@Autowired
	public App(CurrencyProcessor currencyProcessor) {
		super();
		this.currencyProcessor = currencyProcessor;
	}

	private CurrencyProcessor currencyProcessor;

	public void calculate() {
		Scanner scanner = new Scanner(System.in);
		String input = ""; //$NON-NLS-1$

		do {
			input = scanner.nextLine();
			String[] inputParsed = input.trim().toUpperCase().split(" "); //$NON-NLS-1$
			if (input.equalsIgnoreCase(Messages.getString("App.exit"))) { //$NON-NLS-1$
				LOG.info(Messages.getString("App.exitMessage")); //$NON-NLS-1$
				continue;
			} else if (inputParsed.length != 4) {
				LOG.info(Messages.getString("App.invalidArgs")); //$NON-NLS-1$
				continue;
			}
			// validate and process the input
			Optional<CurrencyRequest> ccyReq = validateInput(inputParsed);

			if (!ccyReq.isPresent()) {
				LOG.info(Messages.getString("App.invalidInput") + inputParsed[0] + "/" + inputParsed[3] + " - " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+ inputParsed[1]);
			} else {
				// no validation error, process the request
				String result = process(ccyReq.get());
				displayResult(ccyReq.get(), result);
			}

		} while (!input.equalsIgnoreCase(Messages.getString("App.exit"))); //$NON-NLS-1$

		scanner.close();

	}

	/**
	 * @param ccyReq
	 * @param result
	 */
	protected void displayResult(CurrencyRequest ccyReq, String result) {
		if (Messages.getString("App.Rate_mapping_validation").equalsIgnoreCase(result)) { //$NON-NLS-1$
			LOG.info(Messages.getString("App.Missing_rate") + ccyReq.getCcyFrom().getCurrencyCode() + "/" //$NON-NLS-1$ //$NON-NLS-2$
					+ ccyReq.getCcyTo().getCurrencyCode());
		} else {
			LOG.info(ccyReq.getCcyFrom().getCurrencyCode() + " " + ccyReq.getAmount() + " = " //$NON-NLS-1$ //$NON-NLS-2$
					+ ccyReq.getCcyTo().getCurrencyCode() + " " + result); //$NON-NLS-1$
		}
	}

	protected Optional<CurrencyRequest> validateInput(String[] inputParsed) {
		CurrencyRequest ccyReq = new CurrencyRequest();
		try {
			ccyReq.setCcyFrom(Currency.getInstance(inputParsed[0]));
			ccyReq.setCcyTo(Currency.getInstance(inputParsed[3]));
			ccyReq.setAmount(Float.valueOf(inputParsed[1]));
		} catch (IllegalArgumentException iarg) {
			return Optional.empty();
		}
		return Optional.of(ccyReq);
	}

	protected String process(CurrencyRequest ccyReq) {
		ResponseObject respObj = currencyProcessor.process(ccyReq);
		return respObj.toString();
	}
}
