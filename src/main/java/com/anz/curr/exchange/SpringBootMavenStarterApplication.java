package com.anz.curr.exchange;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
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

	//private static Logger LOG = LoggerFactory.getLogger(SpringBootMavenStarterApplication.class);
	private static final Logger logger = LogManager.getLogger(SpringBootMavenStarterApplication.class);

	
	@Autowired
	private App app;

	public static void main(String[] args) {
		logger.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootMavenStarterApplication.class, args);
		logger.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {
		  logger.debug("Debugging log");
	        logger.info("Info log");
	        logger.warn("Hey, This is a warning!");
	        logger.error("Oops! We have an Error. OK");
	        logger.fatal("Damn! Fatal error. Please fix me.");
	        logger.info("EXECUTING : command line runner");
		app.calculate();
	}
}
