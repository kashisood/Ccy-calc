package com.anz.curr.exchange.springcore;

import java.util.Currency;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anz.curr.exchange.springcore.processor.CurrencyProcessor;
import com.anz.curr.exchange.springcore.request.CurrencyRequest;

/**
 * Hello world!
 *
 */
@Component
public class App {

	private static Logger LOG = LoggerFactory.getLogger(App.class);

	@Autowired
	private CurrencyProcessor currencyProcessor;
	@Autowired
	private CurrencyRequest ccyReq;

	public void calculate() {
		Scanner scanner = new Scanner(System.in);
		String input = "";

		do {
			input = scanner.nextLine();
			String[] inputParsed = input.trim().toUpperCase().split(" ");
			if (input.equalsIgnoreCase("exit")) {
				LOG.info("Exit Currency Calculator");
				continue;
			} else if (inputParsed.length != 4) {
				LOG.info(
						"Insufficient arguments. Use Input format as : <ccy1> <amount> in <ccy2> e.g AUD 100.00 in USD");
				continue;
			}
			// validate and process the input
			if (!validateInput(inputParsed)) {
				LOG.info("Invalid Currency or Amount : " + inputParsed[0] + "/" + inputParsed[3] + " - "
						+ inputParsed[1]);
			} else {
				// no validation error, process the request
				String result = process();
				if ("RateMappingNotFound".equalsIgnoreCase(result)) {
					LOG.info("Unable to find rate for " + inputParsed[0] + "/" + inputParsed[3]);
				} else {
					LOG.info(inputParsed[0] + " " + inputParsed[1] + " = " + inputParsed[3] + " " + result);
				}
			}

		} while (!input.equalsIgnoreCase("exit"));

		scanner.close();

	}

	private Boolean validateInput(String[] inputParsed) {
		try {
			ccyReq.setCcyFrom(Currency.getInstance(inputParsed[0]));
			ccyReq.setCcyTo(Currency.getInstance(inputParsed[3]));
			ccyReq.setAmount(Float.valueOf(inputParsed[1]));
		} catch (IllegalArgumentException iarg) {
			return false;
		}
		return true;
	}

	private String process() {
		return currencyProcessor.process(ccyReq).toString();
	}
}
