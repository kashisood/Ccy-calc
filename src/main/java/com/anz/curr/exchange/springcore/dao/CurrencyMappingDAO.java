package com.anz.curr.exchange.springcore.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMappingDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, String> fetchCurrencyMapping() {

		return jdbcTemplate.query("SELECT BASE_ID, TERM_ID, MAPPING_VALUE FROM CURRENCY_MATRIX", (ResultSet rs) -> {
			HashMap<String, String> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getString("BASE_ID") + rs.getString("TERM_ID"), rs.getString("MAPPING_VALUE"));
			}
			return results;
		});
	}

}
