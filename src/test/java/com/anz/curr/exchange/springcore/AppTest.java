package com.anz.curr.exchange.springcore;

import java.io.ByteArrayInputStream;
import java.util.Currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class AppTest {

	@Autowired
	private App app;

	@Test
	public final void testCalculate() {

		ByteArrayInputStream in = new ByteArrayInputStream("AUD 100.8 to DKK".getBytes());
		System.setIn(in);

		app.calculate();

		// optionally, reset System.in to its original
		System.setIn(System.in);
	}

}
