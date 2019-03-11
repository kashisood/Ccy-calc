package com.anz.curr.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.anz.curr.exchange.springcore.App;

@SpringBootApplication
@EnableCaching
public class SpringBootMavenStarterApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(SpringBootMavenStarterApplication.class);

	@Autowired
	private App app;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootMavenStarterApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		LOG.info("EXECUTING : command line runner");
		app.calculate();
	}
}
