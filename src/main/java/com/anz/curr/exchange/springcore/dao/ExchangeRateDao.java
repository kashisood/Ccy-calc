package com.anz.curr.exchange.springcore.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateDao {

	private static Map<String, Double> rateMap = null;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Double> getRates() {
		
		return jdbcTemplate.query("select ccy1ccy2, rate FROM RATE_MAP", (ResultSet rs) -> {
			HashMap<String, Double> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getString("ccy1ccy2"), rs.getDouble("rate"));
			}
			return results;
		});
	}

}
