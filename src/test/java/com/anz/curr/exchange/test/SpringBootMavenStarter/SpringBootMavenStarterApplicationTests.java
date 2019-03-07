package com.anz.curr.exchange.test.SpringBootMavenStarter;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class SpringBootMavenStarterApplicationTests {
	

	@Test
	public void contextLoads() {
	}

	@Test
	public final void testCalculate() {

		ByteArrayInputStream in = new ByteArrayInputStream("AUD 100.8 to DKK".getBytes());
		System.setIn(in);

		//App a = new App();
		//a.calculate();
		// do your thing

		// optionally, reset System.in to its original
		System.setIn(System.in);
	}

	@Test
	public void testInvalidCurrency() {
	//	App app = new App();
		// app.calculate();
	}

	@Test
	public void testInvalidAmount() {
	}

	@Test
	public void testValidCurrencyDirectConversion() {
	}

	@Test
	public void testValidCurrencyConversionFromMapping() {
	}
}
